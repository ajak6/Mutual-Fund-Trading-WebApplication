package employeeController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.FundDAO;
import model.Fund_Price_HistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Fund;
import databeans.FundInfo;
import databeans.Fund_Price_History;
import databeans.Position;
import databeans.Transaction;
import formbeans.TransitionForm;

public class TransitionDayAction extends Action {
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory
			.getInstance(TransitionForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private Fund_Price_HistoryDAO fundPriceHistoryDAO;
	private FundDAO fundDAO;

	public TransitionDayAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		fundPriceHistoryDAO = model.getFund_Price_HistoryDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "transitionDay.doe";
	}

	public String perform(HttpServletRequest request) {
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		// need to check if the logged in is customer or employee

		try {
			org.genericdao.Transaction.begin();

			TransitionForm form = formBeanFactory.create(request);

			Fund[] allFunds = fundDAO.getFundList();
			FundInfo[] allFundInfo = new FundInfo[allFunds.length];

			for (int i = 0; i < allFunds.length; i++) {
				FundInfo item = new FundInfo();
				item.setFund_id(allFunds[i].getFund_id());
				item.setName(allFunds[i].getName());
				item.setSymbol(allFunds[i].getSymbol());

				Fund_Price_History[] historyItemList = fundPriceHistoryDAO
						.match(MatchArg.equals("fund_id",
								allFunds[i].getFund_id()));
				Fund_Price_History historyItem = null;
				if (historyItemList.length > 0) {
					historyItem = historyItemList[historyItemList.length - 1];
				}

				if (historyItem != null) {
					item.setPrice(((double) historyItem.getPrice()) / 100.0);
					item.setPrice_date(historyItem.getPrice_date());
				}

				allFundInfo[i] = item;
				// System.out.println("historyItem: Execute Date " +
				// allFundInfo[i].getPrice_date());

			}

			request.setAttribute("allFundInfo", allFundInfo);

			// only need to get the date, can be improved?
			Fund_Price_History[] allHistory = fundPriceHistoryDAO
					.getAllHistory();

			Date[] dateList = new Date[allHistory.length];
			for (int i = 0; i < allHistory.length; i++) {
				dateList[i] = allHistory[i].getPrice_date();
			}
			Date lastDay = null;
			if (dateList.length != 0) {
				Arrays.sort(dateList);
				lastDay = dateList[dateList.length - 1];
			}
			request.setAttribute("lastDay", lastDay);

			if (!form.isPresent()) {
				// not sure
				return "transitionDay.jsp";
			}

			// if validation errors exists
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "transitionDay.jsp";
			}

			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Date today = formatter.parse(form.getTransitDay());

			if (lastDay != null && !today.after(lastDay)) {
				errors.add("Execution day must be larger than last trading day.");
				return "transitionDay.jsp";
			}

			String[] allFundId = form.getFundIdList();
			String[] allClosingPrices = form.getPriceList();
			HashMap<Integer, Long> hm = new HashMap<Integer, Long>();

			for (int i = 0; i < allClosingPrices.length; i++) {
				Fund_Price_History newHistory = new Fund_Price_History();
				newHistory.setFund_id(Integer.parseInt(allFundId[i]));
				newHistory.setPrice((long) (Double
						.parseDouble(allClosingPrices[i]) * 100));
				newHistory.setPrice_date(today);
				fundPriceHistoryDAO.create(newHistory);
				hm.put(Integer.parseInt(allFundId[i]),
						(long) Double.parseDouble(allClosingPrices[i]) * 100);
			}

			Transaction pendingList[] = transactionDAO.match(MatchArg.equals(
					"execute_date", null));
			int length = pendingList.length;

			for (int i = 0; i < length; i++) {

				String type = pendingList[i].getTransaction_type();

				if (type.equalsIgnoreCase("buy")) {
					int fundId = pendingList[i].getFund_id();
					long price = hm.get(fundId);
					long amount = pendingList[i].getAmount();
					long buyAmount = (long) (((double) amount) / ((double) price)) * 10;
					/*
					 * if (buyAmount < 0.1) { errors.add(
					 * "The share can buy is too small. This transaction will be cancelled."
					 * ); return "transitionDay.jsp"; }
					 */
					int customerId = pendingList[i].getCustomer_id();
					Position position = positionDAO.read(customerId, fundId);

					if (position == null) {
						Position newPosition = new Position();
						newPosition.setCustomer_id(customerId);
						newPosition.setFund_id(fundId);
						newPosition.setShares(buyAmount);
						positionDAO.create(newPosition);
					} else {
						long oldShares = position.getShares();
						position.setShares(oldShares + buyAmount);
						positionDAO.update(position);
					}

					// update Transactions
					pendingList[i].setExecute_date(today);
					pendingList[i].setShares(buyAmount);
					transactionDAO.update(pendingList[i]);

					// update Customers
					Customer customer = customerDAO.read(customerId);
					if (customer != null) {
						long oldCash = customer.getCash();
						customer.setCash(oldCash - amount);
						customerDAO.update(customer);
					}

				} else if (type.equalsIgnoreCase("sell")) {
					int fundId = pendingList[i].getFund_id();
					long price = hm.get(fundId);
					long shares = pendingList[i].getShares();

					int customerId = pendingList[i].getCustomer_id();
					Position position = positionDAO.read(customerId, fundId);

					if (position != null) {
						long oldShares = position.getShares();
						if (oldShares >= shares) {
							long amount = (long) ((double) price / 100
									* (double) shares / 1000) * 100;

							// update positions
							position.setShares(oldShares - shares);
							positionDAO.update(position);

							// update transactions
							pendingList[i].setExecute_date(today);
							pendingList[i].setAmount(amount);
							transactionDAO.update(pendingList[i]);

							// update customers
							Customer customer = customerDAO.read(customerId);
							if (customer != null) {
								long oldCash = customer.getCash();
								customer.setCash(oldCash + amount);
								customerDAO.update(customer);
							}
						}
					}
				} else if (type.equalsIgnoreCase("request")) {
					int customerId = pendingList[i].getCustomer_id();
					long amount = pendingList[i].getAmount();

					// update transactions
					pendingList[i].setExecute_date(today);
					transactionDAO.update(pendingList[i]);

					// update customers
					Customer customer = customerDAO.read(customerId);
					if (customer != null) {
						long oldCash = customer.getCash();
						customer.setCash(oldCash - amount);
						customerDAO.update(customer);
					}
				} else if (type.equalsIgnoreCase("deposit")) {
					int customerId = pendingList[i].getCustomer_id();
					long amount = pendingList[i].getAmount();

					// update transactions
					pendingList[i].setExecute_date(today);
					transactionDAO.update(pendingList[i]);

					// update customers
					Customer customer = customerDAO.read(customerId);
					if (customer != null) {
						long oldCash = customer.getCash();
						customer.setCash(oldCash + amount);
						customerDAO.update(customer);
					}
				} else {
					errors.add("invalid transaction type.");
					return "transitionDay.jsp";

				}
			}

			// update availableCash
			Customer[] allCustomer = customerDAO.getUsers();
			for (int i = 0; i < allCustomer.length; i++) {
				allCustomer[i].setAvailableCash(allCustomer[i].getCash());
				customerDAO.update(allCustomer[i]);
			}

			// update availableShares
			Position[] allPosition = positionDAO.match();
			for (int i = 0; i < allPosition.length; i++) {
				allPosition[i].setAvailableShares(allPosition[i].getShares());
				positionDAO.update(allPosition[i]);
			}

			org.genericdao.Transaction.commit();
			return "transitionSuccess.jsp";

		} catch (RollbackException e) {
			errors.add(e.toString());
			return "transitionDay.jsp";
		} catch (FormBeanException e) {
			e.printStackTrace();
			return "transitionDay.jsp";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "transitionDay.jsp";

		} finally {
			if (org.genericdao.Transaction.isActive()) {
				org.genericdao.Transaction.rollback();
			}
		}

	}

}

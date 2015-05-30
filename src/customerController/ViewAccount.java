package customerController;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.Fund;
import databeans.FundInfo;
import databeans.Position;
import databeans.Transaction;
import model.CustomerDAO;
import model.FundDAO;
import model.Fund_Price_HistoryDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class ViewAccount extends Action {
	private CustomerDAO customerDAO;
	private PositionDAO positionDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private Fund_Price_HistoryDAO fundPriceDAO;
	DecimalFormat priceDF = new DecimalFormat("#,##0.00");//changed
	DecimalFormat shareNumberDF = new DecimalFormat("#,##0.000");//changed

	public ViewAccount(Model model) {
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceDAO = model.getFund_Price_HistoryDAO();
	}

	public String getName() {
		return "view_account_c.doc";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		try {
			Customer customer = (Customer) request.getSession(false)
					.getAttribute("user");
			double cash = ((double)customerDAO.read(customer.getCustomer_id()).getCash())/100;
			double availableCash = ((double)customerDAO.read(customer.getCustomer_id()).getAvailableCash())/100;
			request.setAttribute("target", customer);
			request.setAttribute("cash", priceDF.format(cash));
			request.setAttribute("availableCash", priceDF.format(availableCash));
			Transaction[] tran = transactionDAO.getTransaction(customer
					.getCustomer_id());
			if (tran.length > 0) {
				Date date = tran[tran.length - 1].getExecute_date();
				for (int i = tran.length - 1; i > 0; i--) {
					if (tran[i].getExecute_date() == null) {
						date = tran[i - 1].getExecute_date();
					}
				}
				request.setAttribute("Tradingdate", date);
			}
			
			Position[] positionList = positionDAO.getPositions(customer
					.getCustomer_id());
			FundInfo[] fundInfoList = new FundInfo[positionList.length];

			for (int i = 0; i < fundInfoList.length; i++) {
				Fund target = fundDAO.read(positionList[i].getFund_id());
				fundInfoList[i] = new FundInfo();
				fundInfoList[i].setName(target.getName());
				fundInfoList[i]
						.setShares(((double) positionList[i].getShares()) / 1000.0);
				fundInfoList[i].setFund_id(positionList[i].getFund_id());
				fundInfoList[i].setPrice(((double) fundPriceDAO
						.getLastPrice(positionList[i].getFund_id())) / 100);
				// add available shares
				fundInfoList[i].setAvailableShares(((double) positionList[i]
						.getAvailableShares()) / 1000);
				fundInfoList[i].setValue((((double) positionList[i]
						.getAvailableShares()) / 1000)
						* (((double) fundPriceDAO.getLastPrice(positionList[i]
								.getFund_id())) / 100));
			}
			request.setAttribute("fundInfoList", fundInfoList);
			return "ViewAccount_c.jsp";
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ViewAccount_c.jsp";
		}

	}

}

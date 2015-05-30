package customerController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.CustomerDAO;
import model.TransactionDAO;
import databeans.Customer;
import databeans.Fund;
import databeans.Position;
import databeans.Transaction;
import formbeans.BuyFundForm;
import formbeans.RequestCheckForm;

public class BuyFundAction extends Action {

	private FormBeanFactory<BuyFundForm> formBeanFactory = FormBeanFactory
			.getInstance(BuyFundForm.class);

	private CustomerDAO customerDAO;
	
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	DecimalFormat balanceDF = new DecimalFormat("#,##0.00");//changed

			

	public BuyFundAction(Model model) {
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() {
		return "buyFund.doc";
	}

	public String perform(HttpServletRequest request) {
		// Set up the errors list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			Fund[] list = fundDAO.getFundList();

			if (list.length == 0) {
				errors.add("No fund exists now!");
				return "buy-fund.jsp";
			}

			List<Fund> fundList = new ArrayList<Fund>();
			for (int i = 0; i < list.length; i++) {
				Fund bean = new Fund();
				bean.setFund_id(list[i].getFund_id());
				bean.setName(list[i].getName());
				bean.setSymbol(list[i].getSymbol());


				fundList.add(bean);
			}

			request.setAttribute("fundlist", fundList);

			Customer customer = (Customer) request.getSession().getAttribute(
					"user");
			long balance = customerDAO.getBalance(customer.getCustomer_id());
			double ba = ((double)balance)/100.00;
	
			String b = balanceDF.format(balance/100.00);//changed

			request.setAttribute("balance", b);//changed
			
			BuyFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);
			if(!form.isPresent()){
				return "buy-fund.jsp";
			}
			errors.addAll(form.getValidationErrors());
			if(errors.size() > 0 ){
				return "buy-fund.jsp";
			}

			String fundName = form.getFundName();
	

			int cId = customer.getCustomer_id();

			
		
			String a = form.getAmount();//changed
			double amount = Double.parseDouble(a);//changed
			if(((amount*1000)/10) % 1 != 0 && amount!=0.07){
				System.out.println("神奇！"+a);
				System.out.println(((amount*1000)/10) % 1);
				errors.add("Accuracy could not go over two digits decimal");
				return "buy-fund.jsp";
			}
			if(amount < 1){
				errors.add("Amount is too small for transaction");
				return "buy-fund.jsp";
			}
	
			if (amount <= 0) {
				errors.add("Amount must be positive");
				return "buy-fund.jsp";
			}
//			if(positionDAO.getAvailableShares(customer.getCustomer_id()) != 0){
//			if((amount + positionDAO.getAvailableShares(customer.getCustomer_id()))>1000000000l){
//				System.out.println( "@@@@@@@@@@" + positionDAO.getAvailableShares(customer.getCustomer_id()));
//				errors.add("The transaction amount is too large, please contact us!");
//				return "SellFund.jsp";
//			}
//			}

			if (ba< amount) {
				errors.add("Amount invalid! You could not spend more than your current balance.");
				return "buy-fund.jsp";
			}
			Position position = new Position();
			position.setCustomer_id(cId);
			//this line will cause exception when fund name is not in  the database
			
//			position.setFund_id(fundDAO.getFundId(fundName));
			Fund[] fund = fundDAO.match(MatchArg.equals("name", fundName));
			if(fund.length==0){
				errors.add("No such fund present");
				return "buy-fund.jsp";
			}
			int fundId = fund[0].getFund_id();
			position.setFund_id(fundId);
			position.setShares(0);

			Transaction transaction = new Transaction();
		
			transaction.setCustomer_id(customer.getCustomer_id());
			transaction.setFund_id(fundId);
			transaction.setExecute_date(null);
			transaction.setShares(0);
			transaction.setTransaction_type("buy");
			long am = Math.round(amount*100);//changed
			transaction.setAmount(am);
			transactionDAO.createAutoIncrement(transaction);

			customerDAO.changeBalance(customer.getCustomer_id(), am);//changed
			System.out.println("change balance" + "balance"+ balance + "am"+  am + "amount"+ amount);
			balance=customerDAO.getBalance(customer.getCustomer_id());
			System.out.println("New balance" + balance);


			request.setAttribute("message",
					"Buy Fund request has been put in queue");


			return "success.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		return null;
	}

}
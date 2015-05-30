package employeeController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;
import formbeans.DepositCheckForm;

public class DepositCheckAction extends Action {
	private FormBeanFactory<DepositCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(DepositCheckForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public DepositCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();

	}

	public String getName() {
		return "depositCheck.doe";
	}

	public String perform(HttpServletRequest request) {
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		DepositCheckForm form = null;
		request.setAttribute("form", form);

		try {
			form = formBeanFactory.create(request);

			if (!form.isPresent()) {
				return "depositCheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "depositCheck.jsp";
			}

			org.genericdao.Transaction.begin();
//Amey : add this check it was accepting check for customer which were not present. Checked
			//now accepts the check only if customer is present
			 Customer cusList[] = customerDAO.match(MatchArg.equals("username", form.getUsername())); 
			 
			 if(cusList.length==0){
				 errors.add("No such customer present");
				 return "depositCheck.jsp";
			 }
			 Customer customer = cusList[0];
			
			 // deposit check
			String sAmount =  form.getAmount();
			if (sAmount.length() > 11) {
				errors.add("The input amount is too large, please input amount less than 1 billion.");
				return "depositCheck.jsp";

			}
			Double amount = Double.parseDouble(sAmount);
			if(((amount*1000)/10)%1 != 0){
				errors.add("Accuracy could not exceed two-digit decimal");
				return "depositCheck.jsp";

			}
			if(amount>1000000){
				errors.add("The maximum amount of deposit check is 1 million.");
				return "depositCheck.jsp";
			}
			long am = (long)(amount*100);
			
			long currentCash = customer.getAvailableCash();
			System.out.println(" 尼玛"+(currentCash/100));
			if ((currentCash/100 + amount) > 1000000000l) {
				errors.add("Your balance cannot exceed 1 billion.");
				return "depositCheck.jsp";
			}
			
			Transaction singleTransaction = new Transaction();
			singleTransaction.setCustomer_id(customer.getCustomer_id());
			singleTransaction.setTransaction_type("deposit");
			singleTransaction.setAmount(am);
			singleTransaction.setFund_id(0);
			singleTransaction.setExecute_date(null);
			singleTransaction.setShares(0);
			transactionDAO.createAutoIncrement(singleTransaction);
			
		/*	//update customer's available balance
			customer.setAvailableCash(customer.getCash() + am);
			customerDAO.update(customer); */
			
			org.genericdao.Transaction.commit();
			request.setAttribute("message",
					"It (Deposit Check) has been successfully added in pending transactions");
			return "depositCheckSuccess.jsp";

		}catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "depositCheck.jsp";
		}
		catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} 
		  finally { if ( org.genericdao.Transaction.isActive()) {  org.genericdao.Transaction.rollback(); } }
		 
	}
}

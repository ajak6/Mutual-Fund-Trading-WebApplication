package customerController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Transaction;
import formbeans.RequestCheckForm;
import model.CustomerDAO;
import model.Model;
import model.TransactionDAO;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public RequestCheckAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() {
		return "requestCheck.doc";
	}

	public String perform(HttpServletRequest request) {
		// Set up error list
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		RequestCheckForm form = null;
		request.setAttribute("form", form);

		try {
			Customer customer = (Customer) request.getSession(false).getAttribute(
					"user");

			// for concurrency getting value from session is not a good idea. If
			// user logs in from two windows there willl be problems
			// long availableBalance = customer.getAvailab
			Customer custData=customerDAO.read(customer.getCustomer_id());
			// System.out.print("customer ID=" + customer.getCustomer_id() +
			// "balance =" + availableBalance + "end");
			// request.setAttribute("availableBalance", availableBalance);

			form = formBeanFactory.create(request);
			request.setAttribute("availableBalance", custData.getAvailableCash());
			System.out.println(customer.getAvailableCash());
			request.setAttribute("mainBalance", custData.getCash());
			if (!form.isPresent()) {
				return "requestCheck.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "requestCheck.jsp";
			}

			org.genericdao.Transaction.begin();
			 custData=customerDAO.read(customer.getCustomer_id());
			long availableBalance =custData
					.getAvailableCash();
			long mainBalance=custData.getCash();
			// balance > 0 or 0.00001? cos it's a long type
			if (availableBalance > 0) {
				//convert it to double first so that decimal value is not lost then multiple it 100 and convert it to long
				double doubleAmount = Double.parseDouble(form.getAmount());
				if(((doubleAmount*1000)/10)%1 > 0){
					errors.add("Accuracy of more than two digits after decimal is not allow");
					return "requestCheck.jsp";
				}
				long amount=(long) (doubleAmount*100);
				if (availableBalance - amount < 0.00) {
					errors.add("No sufficient Cash.");
					return "requestCheck.jsp";
				}

				Transaction singleTransaction = new Transaction();
				singleTransaction.setCustomer_id(customer.getCustomer_id());
				singleTransaction.setTransaction_type("request");
				singleTransaction.setAmount(amount);
				singleTransaction.setFund_id(0); // should we set it as 0 or
													// leave it as empty?
				singleTransaction.setExecute_date(null);
				singleTransaction.setShares(0); // should we set it as 0 or
												// leave it as empty?
				transactionDAO.createAutoIncrement(singleTransaction);
				
// after the transaction is added to the queue reduce the available cash by that value
				System.out.println("available balance is  " +availableBalance);
				System.out.println("amount converted is " + amount);
				custData.setAvailableCash(availableBalance-amount);
				customerDAO.update(custData);
				customer=customerDAO.read(custData.getCustomer_id());
				org.genericdao.Transaction.commit();
				//send the data in the long form divide it 100 on the jsp
				request.setAttribute("availableBalance", availableBalance-amount);
				request.setAttribute("mainBalance", mainBalance);
				request.setAttribute("message",
						"It (Request Check) has been successfully added in pending transactions");
				//change the values in the session variables so that they display correctly on the webpage
				HttpSession session=request.getSession(false);
				session.setAttribute("user", customer);
				
				
				return "requestCheckSuccess.jsp";
			}

			errors.add("You don't have sufficient available cash to request check");
			return "requestCheck.jsp";

		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "requestCheck.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} finally {// this is necessary as we are returning from various if condition closing the connection is neccessary
			if (org.genericdao.Transaction.isActive()) {
				org.genericdao.Transaction.rollback();
			}
		}
	}
}

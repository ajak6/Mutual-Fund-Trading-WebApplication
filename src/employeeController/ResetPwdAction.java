package employeeController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.EmployeeRegisterForm;
import formbeans.ResetPwdForm;
import model.CustomerDAO;
import model.Model;

public class ResetPwdAction extends Action {
	
	private CustomerDAO customerDAO;
	private FormBeanFactory<ResetPwdForm> formBeanFactory = FormBeanFactory
			.getInstance(ResetPwdForm.class);
	
	public ResetPwdAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "resetpwd.doe";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			errors.add("Session expired Please login again");
			return "login.doe";
		}
		
		try {
			ResetPwdForm form = null;
			form = formBeanFactory.create(request);
			
			if (!form.isPresent()) {
				return "resetPwd.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "resetPwd.jsp";
			}
			String newPassword = form.getNewPassword();
			String confirmPassword = form.getConfirmPassword();
			if (newPassword == null || newPassword.length() == 0) {
				errors.add("New Password is required");
				return "resetPwd.jsp";
			}

			if (confirmPassword == null || confirmPassword.length() == 0) {
				errors.add("Confirm Pwd is required");
				return "resetPwd.jsp";
			}


			if (!newPassword.equals(confirmPassword)) {
				errors.add("Passwords do not match");
				return "resetPwd.jsp";
			}

			org.genericdao.Transaction.begin();
			String userName = form.getUserName();
			Customer customer = customerDAO.match(MatchArg.equals("username", userName))[0];
			customer.setPassword(newPassword);  //should we set another default password? 
			customerDAO.update(customer);

			org.genericdao.Transaction.commit();

			request.setAttribute("message",
					"Customer's password has been reset successfully.");
			return "resetSuccess.jsp";

			
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "resetPwd.jsp";
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

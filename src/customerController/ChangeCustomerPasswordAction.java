package customerController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;

import formbeans.ChangePwdForm;

public class ChangeCustomerPasswordAction extends Action {
	CustomerDAO customerDAO;

	public ChangeCustomerPasswordAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	FormBeanFactory<ChangePwdForm> changeFact = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	@Override
	public String getName() {
		return "changpwd.doc";
	}

	@Override
	public String perform(HttpServletRequest request) {
System.out.println("changing password");
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession(false);
		String message = "";

		if (session == null) {
			errors.add("Session expired Please login again");
			return "login.jsp";
		}
	
		ChangePwdForm changeForm = null;
		try {
			changeForm = changeFact.create(request);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}

		if (!changeForm.isPresent()) {
			return "changePass.jsp";
		}
		errors.addAll(changeForm.getValidationErrors());
		if (errors.size() != 0) {
			return "changePass.jsp";
		}
//		System.out.println("passed all problems ");
		Customer c = (Customer) session.getAttribute("user");
		
		if (!c.getPassword().equals(changeForm.getOldPassword())) {
			errors.add("Current password is wrong Please try again");
			return "changePass.jsp";
		}
		else{
			//change the password and send a success message
			try {
				customerDAO.changePassword(c.getCustomer_id(),
						changeForm.getNewPassword());
				return "passChanged.jsp";
			} catch (RollbackException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("message", message);
		return null;
	}
}

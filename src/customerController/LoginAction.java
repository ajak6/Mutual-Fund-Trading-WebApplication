package customerController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import formbeans.LoginForm;

public class LoginAction extends Action {
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);
	private CustomerDAO customerDAO;

	public LoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "login.doc";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {
			LoginForm loginForm = formBeanFactory.create(request);
			request.setAttribute("form", loginForm);
			if (!loginForm.isPresent()) {
				return "login.jsp";
			}
			errors.addAll(loginForm.getValidationErrors());
			if (errors.size() != 0) {
				return "login.jsp";
			}

			Customer[] customer = customerDAO.match(MatchArg.and(
					MatchArg.equals("username", loginForm.getcEmail()),
					MatchArg.equals("password", loginForm.getcPassword())));
			if (customer == null || customer.length == 0) {
				errors.add("User Name  or password is wrong ");
				return "login.jsp";
			}
			// login successful attach this user in the session. customer is an
			// array which will be having only one element so passing
			// customer[0]
			HttpSession session = request.getSession();
			session.setAttribute("user", customer[0]);
			session.setMaxInactiveInterval(60*10);
			return "view_account_c.doc";
		} catch (RollbackException e) {
			errors.add(e.getMessage());
			return "login.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "login.jsp";
		}
	}
}
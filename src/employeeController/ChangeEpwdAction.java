package employeeController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.ChangePwdForm;

public class ChangeEpwdAction extends Action {
	String EMPLOYEE = "00xxemployeexx";
	EmployeeDAO employeeDAO;

	public ChangeEpwdAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	FormBeanFactory<ChangePwdForm> changeFact = FormBeanFactory
			.getInstance(ChangePwdForm.class);

	@Override
	public String getName() {
		return "changpwd.doe";
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
			return "login.doe";
		}
		
		ChangePwdForm changeForm = null;
		try {
			changeForm = changeFact.create(request);
		} catch (FormBeanException e) {
			e.printStackTrace();
		}

		if (!changeForm.isPresent()) {
			return "EchangePass.jsp";
		}
		errors.addAll(changeForm.getValidationErrors());
		if (errors.size() != 0) {
			return "EchangePass.jsp";
		}
		// System.out.println("passed all problems ");
		Employee c = (Employee) session.getAttribute("user");

		if (!c.getPassword().equals(changeForm.getOldPassword())) {
			errors.add("Current password is wrong Please try again");
			return "EchangePass.jsp";
		} else {
			// change the password and send a success message
			try {
				Transaction.begin();
				Employee e = employeeDAO.read(c.getUsername());
				e.setPassword(changeForm.getNewPassword());
				employeeDAO.update(e);
				Transaction.commit();
				return "EpassChanged.jsp";
			} catch (RollbackException e) {
				e.printStackTrace();
			}finally{
				if(Transaction.isActive()){
					Transaction.rollback();
				}
			}

			request.setAttribute("message", message);
			return null;
		}
	}
}
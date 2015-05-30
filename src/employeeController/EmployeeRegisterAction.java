package employeeController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.EmployeeDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.EmployeeRegisterForm;

public class EmployeeRegisterAction extends Action {
	EmployeeDAO employeeDAO;
	FormBeanFactory<EmployeeRegisterForm> empFormFact = FormBeanFactory
			.getInstance(EmployeeRegisterForm.class);
	

	public EmployeeRegisterAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	@Override
	public String getName() {
		return "employeeRegister.doe";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		EmployeeRegisterForm empForm = null;
		
		request.setAttribute("errors", errors);
		try {
			empForm = empFormFact.create(request);
			request.setAttribute("form", empForm);
			if (!empForm.isPresent()) {
				return "employeeRegister.jsp";
			}
			errors.addAll(empForm.getValidationErrors());
			System.out.println(errors.toString());
			if (errors.size() != 0) {
				return "employeeRegister.jsp";
			}
		} catch (FormBeanException e) {
			e.printStackTrace();
		}
		try {
			Transaction.begin();
			if (employeeDAO.match(MatchArg.equalsIgnoreCase("username",
					empForm.getEmpUsername())).length == 0) {
				// if(true){
				Employee employeeRecord = new Employee();
				employeeRecord .setFirstName(empForm.getEmpFirstName());
				employeeRecord .setLastName(empForm.getEmpLastName());

				employeeRecord .setUsername(empForm.getEmpUsername());
				employeeRecord .setPassword(empForm.getEmpPassword());
				employeeDAO.create(employeeRecord );

				Transaction.commit();
				request.setAttribute("message", "Employee Added Successfully");
				return "empRegisterSuccess.jsp";
			} else {
				System.out.println("error");
				errors.add("Employee with this username already exist");
				return "employeeRegister.jsp";
			}

		} catch (RollbackException e) {
			e.printStackTrace();
		} finally {
			if (Transaction.isActive()) {
				Transaction.rollback();
			}
		}

		return null;

	}

}

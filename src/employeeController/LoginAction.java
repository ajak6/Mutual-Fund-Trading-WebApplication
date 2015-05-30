package employeeController;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Employee;
import formbeans.LoginFormEmp;

public class LoginAction extends Action{
	private FormBeanFactory<LoginFormEmp> formBeanFactory = FormBeanFactory.getInstance(LoginFormEmp.class);
	private EmployeeDAO employeeDAO;

	public LoginAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() { return "login.doe"; }
    
    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	LoginFormEmp form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        if (!form.isPresent()) {
	            return "elogin.jsp";
	        }
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "elogin.jsp";
	        }
	        Employee employee = employeeDAO.read(form.geteEmail());
	        
	        if (employee == null) {
	            errors.add("User Name not found");
	            return "elogin.jsp";
	        }

	        // Check the password
	        if (!employee.getPassword().equals(form.getePassword())) {
	            errors.add("Incorrect password");
	            return "elogin.jsp";
	        }
	System.out.println("successful login of employee ");
	        // Attach (this copy of) the user bean to the session
	        HttpSession session = request.getSession();
	        session.setAttribute("user",employee);
			session.setMaxInactiveInterval(60*10);
	        return "ViewAccount_e.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "elogin.jsp";
        }
    }
}
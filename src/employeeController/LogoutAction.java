package employeeController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

public class LogoutAction extends Action {

	public LogoutAction(Model model) { }

	public String getName() { return "empLogout.doe"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.setAttribute("user",null);
        session.invalidate();
		request.setAttribute("message","You are now logged out");
        return "index.jsp";
    }
}

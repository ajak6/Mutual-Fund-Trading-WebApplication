package employeeController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;

import databeans.Employee;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	String EMPLOYEE = "00xxemployeexx";

	public void init() throws ServletException {
		Model model = new Model(getServletConfig());

		Action.add(new LoginAction(model));
		Action.add(new CustRegisterAction(model));
		Action.add(new EmployeeRegisterAction(model));
		Action.add(new DepositCheckAction(model));
		Action.add(new LogoutAction(model));
		Action.add(new ChangeEpwdAction(model));
		Action.add(new TransitionDayAction(model));
		Action.add(new EmpViewTransactionHistoryAction(model));
		Action.add(new ManageFund(model));
		Action.add(new ViewAccount(model));
		Action.add(new CreateFund(model));
		Action.add(new ResetPwdAction(model));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		// changing code to handle both
		Object employee = session.getAttribute("user");
		if (employee == null) {
			return Action.perform("login.doe", request);
		}
		if (!(employee instanceof Employee)) {
			System.out.println("not instance of employee");
			return Action.perform("login.doe", request);
		}
		String action = getActionName(servletPath);
		System.out.println("employee from seesion " + employee);

		System.out.println("action is " + action);
		return Action.perform(action, request);

	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}

		if (nextPage.endsWith(".doe")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			// all files are in pages folder rather than web-inf
			RequestDispatcher d = request.getRequestDispatcher(nextPage);
			d.forward(request, response);
			return;
		}

		response.sendRedirect(nextPage);
		return;
		// throw new
		// ServletException(Controller.class.getName()+".sendToNextPage(\"" +
		// nextPage + "\"): invalid extension.");
	}

	private String getActionName(String path) {
		// We're guaranteed that the path will start with a slash
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}

}

package employeeController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.Fund;
import databeans.FundInfo;
import databeans.Position;
import model.FundDAO;
import model.Model;
import model.PositionDAO;

public class ManageFund extends Action{
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	public ManageFund(Model model) {
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "managefund.doe";
	}

	@Override
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
        	Fund[] fundList = fundDAO.match();
        	request.setAttribute("fundList", fundList);
	        return "create_fund.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "create_fund.jsp";
        }
	}

}

package customerController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.Fund;
import databeans.FundInfo;
import databeans.Position;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;

public class ManageFund extends Action {
	private PositionDAO positionDAO;
	private CustomerDAO customerDAO;
	private FundDAO fundDAO;
	
	public ManageFund(Model model){
		positionDAO = model.getPositionDAO();
		customerDAO = model.getCustomerDAO();
		fundDAO = model.getFundDAO();
	}
	public String getName() {
		return "managefund.doc";
	}
	
	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        try {
			Customer customer  = (Customer) request.getSession(false).getAttribute("user");
			if(positionDAO.getPositions(customer.getCustomer_id())==null){
				 return "SellFund.jsp";
			}
			Position[] positionList = positionDAO.getPositions(customer.getCustomer_id());
			FundInfo[] fundInfoList = new FundInfo[positionList.length];
			for (int i = 0; i < fundInfoList.length; i++) {
				fundInfoList[i] = new FundInfo();
				fundInfoList[i].setName(fundDAO.read(
						positionList[i].getFund_id()).getName());
				fundInfoList[i].setShares(((double)positionList[i].getShares())/1000.0);
				fundInfoList[i].setFund_id(positionList[i].getFund_id());
				fundInfoList[i].setAvailableShares(((double)positionList[i]
						.getAvailableShares())/1000.0);// by Stella
			}
			request.setAttribute("fundInfoList", fundInfoList);
	        return "SellFund.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
	}

}

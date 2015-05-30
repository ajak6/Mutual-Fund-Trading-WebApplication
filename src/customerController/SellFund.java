package customerController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import databeans.Customer;
import databeans.Fund;
import databeans.FundInfo;
import databeans.Position;
import databeans.Transaction;
import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.PositionDAO;
import model.TransactionDAO;

public class SellFund extends Action {
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;

	public SellFund(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "sellfund.doc";
	}

	public String perform(HttpServletRequest request) {
		try {
			List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			Customer customer = (Customer) request.getSession(false)
					.getAttribute("user");
			Position[] positionList = positionDAO.getPositions(customer
					.getCustomer_id());
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
			if (positionDAO.getPositions(customer.getCustomer_id()) == null) {
				return "SellFund.jsp";
			}
			System.out.println("this out "
					+ request.getSession(false).getAttribute("sellshare"));
			Double sharesell = 0.0;
			try {
				sharesell = Double.parseDouble(request
						.getParameter("sellshare"));
			} catch (NumberFormatException e) {
				errors.add("Shares should be number!");
				// I change it into message to show in the jsp - Stella
				return "SellFund.jsp";
			}
			sharesell = sharesell * 1000;
			if (sharesell % 1 > 0) {
				errors.add("Shares only allow 3 decimal!");
				return "SellFund.jsp";
			}

			long sellShare = sharesell.longValue();
			if (sellShare <= 0) {
				errors.add("Shares should be greater than 0!");
				// same as above - Stella
				return "SellFund.jsp";
			}
//			if(sharesell*1000+(customer.getAvailableCash()/100)>1000000000l){
//				errors.add("You have too much cash in your account, please contact us!");
//				return "SellFund.jsp";
//			}
	
		
			int sellfundID = Integer.parseInt(request
					.getParameter("fundToSell"));
			Fund fundToSell = fundDAO.read(sellfundID);
			if(fundToSell==null){
				errors.add("Fund does not exist!");
				return "SellFund.jsp";
			}
			long available = positionDAO.read(customer.getCustomer_id(),
					fundToSell.getFund_id()).getAvailableShares();
			if (sellShare <= available) {
				Transaction trans = new Transaction();
				trans.setShares(sellShare);
				trans.setCustomer_id(customer.getCustomer_id());
				trans.setFund_id(fundToSell.getFund_id());
				trans.setTransaction_type("Sell");
				transactionDAO.create(trans);
				positionDAO.setAvailableShares(customer.getCustomer_id(),
						fundToSell.getFund_id(), available - sellShare);
				return "SellFund_success.jsp";
			} else {
				errors.add("Not enough fund to sell!");
				return "SellFund.jsp";
			}

		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "SellFund.jsp";
		}

	}

}

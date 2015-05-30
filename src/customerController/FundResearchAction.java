package customerController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.FundDAO;
import model.Fund_Price_HistoryDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Fund;
import databeans.Fund_Price_History;

public class FundResearchAction extends Action {
	FundDAO fundDAO;
	Fund_Price_HistoryDAO fundPriceHistoryDAO;

	public FundResearchAction(Model model) {
		fundPriceHistoryDAO = model.getFund_Price_HistoryDAO();
		fundDAO = model.getFundDAO();
	}

	@Override
	public String getName() {
		return "fundResearch.doc";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String fundId = request.getParameter("fundid");
		List<String> errors = new ArrayList<String>();
		int fundIdInt = 1;
		try {
			// prepare the list of funds and send it to jsp to display the list
			// of available funds
			Fund[] funds = fundDAO.match();
			request.setAttribute("fundlist", funds);
		} catch (RollbackException e1) {
			e1.printStackTrace();
		}
		if (fundId == null) {
			return "Research Fund.jsp";
		}
		try {
			fundIdInt = Integer.parseInt(fundId);
		} catch (Exception e) {
			errors.add("Fund id is changed. Please reload and try again");
		}
		System.out.println("fundid selected is  " + fundId);

		request.setAttribute("errors", errors);

		if (fundId == null || fundId.equals("")) {
			errors.add("Please select a fund from ");
			return "Research Fund.jsp";

		}
		try {
			// Fund fund[] = fundDAO.match(MatchArg.equals("fund_id",
			// fundIdInt));

			/*
			 * if (fund.length == 0) { System.out.println("error");
			 * errors.add(" No such fund present "); return "Research Fund.jsp";
			 * }
			 */// no using a array for funds as we have the primary key we will
				// directly get the record
				// Integer fundID = fund[0].getFund_id();
			// System.out.println("fund if found " + fundID);

			Fund_Price_History fundHistory[] = fundPriceHistoryDAO
					.match(MatchArg.equals("fund_id", fundIdInt));
			// change the long amount to two decimal places
/*			for (int i = 0; i < fundHistory.length; i++) {
			}*/
			// change the date to required format
			SimpleDateFormat sf = new SimpleDateFormat();
			sf.applyPattern("yyyy-MM-dd");
			// System.out.println(sf.format(fundHistory[0].getPrice_date()));
			Fund forFundName = fundDAO.read(fundIdInt);
			request.setAttribute("name", forFundName.getName());
			request.setAttribute("history", fundHistory);
			return "Research Fund.jsp";
		} catch (RollbackException e) {
			e.printStackTrace();
		}

		return null;
	}

}

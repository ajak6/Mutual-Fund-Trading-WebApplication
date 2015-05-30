package employeeController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;
import model.FundDAO;
import model.Model;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Fund;
import formbeans.CreateF;

public class CreateFund extends Action {
	private FormBeanFactory<CreateF> CreateFundFactory = FormBeanFactory.getInstance(CreateF.class);
	private EmployeeDAO employeeDAO;
	private FundDAO fundDAO;
	
	public CreateFund(Model model) {
		employeeDAO = model.getEmployeeDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "creatfund.doe"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        Fund[] fundList = null;
		try {
			fundList = fundDAO.match();
		} catch (RollbackException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	request.setAttribute("fundList", fundList);
        try {
	        CreateF form = CreateFundFactory.create(request);
	        request.setAttribute("form",form);
	        errors.addAll(form.getValidationErrors());
	        Fund[] test1 = fundDAO.match(MatchArg.equals("name",form.getfundName()));
	        if(test1.length!=0){
	        	errors.add("This Fund Name has already created!");
	        	return "create_fund.jsp";
	        }
	        Fund[] test2 = fundDAO.match(MatchArg.equals("symbol",form.getfundSym()));
	        if(test2.length!=0){
	        	errors.add("This Fund Symbol has already created!");
	        	return "create_fund.jsp";
	        }
	        if (errors.size() > 0) {
	        	return "create_fund.jsp";
	        }
	        Fund fund = new Fund();
	        fund.setName(form.getfundName());
	        fund.setSymbol(form.getfundSym());
	        fundDAO.create(fund);
	         fundList = fundDAO.match();
        	request.setAttribute("fundList", fundList);
			return "create_success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "create_fund.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "create_fund.jsp";
        }
    }
}

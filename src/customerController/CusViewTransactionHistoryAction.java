package customerController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.Customer;
import databeans.Fund;
import databeans.Transaction;
import databeans.TransactionHis;
import formbeans.CusViewTransactionHistoryForm;

public class CusViewTransactionHistoryAction extends Action {
	private FormBeanFactory<CusViewTransactionHistoryForm> formBeanFactory = FormBeanFactory
			.getInstance(CusViewTransactionHistoryForm.class);

	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	DecimalFormat priceDF = new DecimalFormat("#,##0.00");//changed
	DecimalFormat shareNumberDF = new DecimalFormat("#,##0.000");//changed
	
	public CusViewTransactionHistoryAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "cus_view_trans_his.doc";
	}

	public String perform(HttpServletRequest request) {
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		Transaction[] list = null;

		try {

			CusViewTransactionHistoryForm form = formBeanFactory
					.create(request);
			request.setAttribute("form", form);

			HttpSession session = request.getSession(false);
			Customer customer = (Customer) session.getAttribute("user");

			int cId = customer.getCustomer_id();
			// int cId = Integer.parseInt(request.getParameter("customerId"));
			list = transactionDAO.getTransactinoList(cId);
			for (Transaction transaction : list) {
				System.out.println(" list of transactions for cid  " + cId
						+ "   " + transaction.getFund_id());

			}
			if (list.length == 0) {
				errors.add("No Transaction History exists !");
				return "cus-view-his.jsp";
			}


			List<TransactionHis> transactionList = new ArrayList<TransactionHis>();
			for (int i = 0; i < list.length; i++) {
				TransactionHis hisBean = new TransactionHis();
				hisBean.setExecute_date(list[i].getExecute_date());
				hisBean.setTransaction_type(list[i].getTransaction_type());
				/*************************************************************************************************/
				// hisBean.setFund_name(fundDAO.getFundName(list[i].getFund_id()));
				// generic dao provides a method no need to write your own
				// if fund id is zero it means the transaction is not buty or
				// sell so skip it
				if (list[i].getFund_id() != 0) {
					hisBean.setFund_name(fundDAO.read(list[i].getFund_id())
							.getName());
				}
				/**************************************************************************************/
				hisBean.setShare_number(shareNumberDF.format((((double)list[i].getShares())/1000.000)));//changed
				System.out.println("share in database@@@@@@@@" + list[i].getShares());
				if(list[i].getShares() != 0){
					hisBean.setShare_price(priceDF.format((((double)list[i].getAmount())/100.00)/((list[i].getShares())/1000.000)));//change
				} else{
					hisBean.setShare_price(priceDF.format(0));//changed
				}
				
				hisBean.setAmount(priceDF.format(((double)list[i].getAmount())/100.00));//changed
				System.out.println("In transaction History Bean!!!!!");
				System.out.println(hisBean.getExecute_date());
				System.out.println(hisBean.getTransaction_type());
				System.out.println(hisBean.getFund_name());
				System.out.println(hisBean.getShare_number());
				System.out.println(hisBean.getAmount());
				transactionList.add(hisBean);
			}

			request.setAttribute("transactionlist", transactionList);

			/*
			 * if (errors.size() > 0) { return "error.jsp"; }
			 */

			// Set up user list for nav bar
			return "cus-view-his.jsp";
		} catch (RollbackException e) {
			errors.add(e.toString());
			return "error.jsp";
		} catch (FormBeanException e) {
			errors.add(e.getMessage());
			return "register.jsp";
		}
	}
}

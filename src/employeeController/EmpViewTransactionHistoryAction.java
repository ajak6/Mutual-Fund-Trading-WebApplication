package employeeController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.TransactionDAO;

import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import employeeController.Action;
import databeans.Customer;
import databeans.Fund;
import databeans.Transaction;
import databeans.TransactionHis;
import formbeans.EmpViewTransactionHistoryForm;

public class EmpViewTransactionHistoryAction extends Action {
	private FormBeanFactory<EmpViewTransactionHistoryForm> formBeanFactory = FormBeanFactory.getInstance(EmpViewTransactionHistoryForm.class);
	
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	DecimalFormat priceDF = new DecimalFormat("#,##0.00");//changed
	DecimalFormat shareNumberDF = new DecimalFormat("#,##0.000");//changed
	
	public EmpViewTransactionHistoryAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
	}
	
	public String getName() {
		return "epm_view_trans_his.doe";
	}
	
	public String perform(HttpServletRequest request){
//		HttpSession session = request.getSession();
//
//		Customer customer = (Customer) request.getSession()
//				.getAttribute("customer");
		System.out.println("Start!!!");
		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		
		Transaction[] list = null;
		
		String customer = request.getParameter("customer");
		System.out.println("customer:"+ customer);
		
//		if (errors.size() > 0) {
//			System.out.println("Errors in Em View" + errors);
//			return "error.jsp";
//		}
		if (customer == null){
			return "emp-view-his.jsp";
		}
		if (customer == null || customer.equals("")) {
			errors.add("enter customer Name");
			return "emp-view-his.jsp";
		}
		
		System.out.println("customer:"+ customer);
		
		
		try {
			Customer cus[] = customerDAO.match(MatchArg.equals("userName", customer));
			if (cus.length == 0) {
				System.out.println("");
				errors.add("no matching customer found");
				return "emp-view-his.jsp";
			}
			EmpViewTransactionHistoryForm form = formBeanFactory.create(request);
//			request.setAttribute("form", form);
			

			//customer = form.getCustomer();
			//request.setAttribute("customer", customer);

				
			
//			errors.addAll(form.getValidationErrors());
//			if (errors.size() > 0) {
//				return "error.jsp";
//			}

		//	HttpSession session = request.getSession(false);
			
			
			int cId = customerDAO.getCustomerId(customer);
			System.out.println("Employee view his CID" + cId);
			list = transactionDAO.getTransactinoList(cId);
			
//			if(list.length == 0){
//				errors. add("No Transaction History for current customer now!");
//			}
			
			


			
			List<TransactionHis> transactionList = new ArrayList<TransactionHis>();
			for (int i = 0; i < list.length; i++) {
				TransactionHis hisBean = new TransactionHis();
				hisBean.setExecute_date(list[i].getExecute_date());
				hisBean.setTransaction_type(list[i].getTransaction_type());
				//*********************************************************
				if (list[i].getFund_id() != 0) {
					hisBean.setFund_name(fundDAO.read(list[i].getFund_id())
							.getName());
				}
				//*********************************************************

				hisBean.setShare_number(shareNumberDF.format((double)(list[i].getShares()/1000.000)));//changed
				System.out.println("share number!!!!@@@@@@" + shareNumberDF.format((double)(list[i].getShares()/1000.000)));
				if(list[i].getShares() != 0){
					hisBean.setShare_price(priceDF.format((double)(list[i].getAmount()/100.00)/(list[i].getShares()/1000.000)));//change
				} else{
					hisBean.setShare_price(priceDF.format(0));//changed
				}
				
				hisBean.setAmount(priceDF.format((double)list[i].getAmount()/100.00));//changed
				System.out.println("In transaction History Bean!!!!!");
				System.out.println(hisBean.getExecute_date());
				System.out.println(hisBean.getTransaction_type());
				System.out.println(hisBean.getFund_name());
				System.out.println(hisBean.getShare_number());
				System.out.println(hisBean.getAmount());
				transactionList.add(hisBean);
			}
			if(transactionList.isEmpty()){
				System.out.print("Empty!!!true");
				errors.add("No Transaction History for current customer now!");
				return "emp-view-his.jsp";
		}

			request.setAttribute("transactionlist", transactionList);
	
	
					
//			if (errors.size() > 0) {
//				System.out.println("Errors in Em View" + errors);
//				return "error.jsp";
//			}

			return "emp-view-his.jsp";
		 }catch (RollbackException e) {
			 e.printStackTrace();
	        } catch (FormBeanException e) {
	        	e.printStackTrace();
	        }
		return null;
		
		 }
	}



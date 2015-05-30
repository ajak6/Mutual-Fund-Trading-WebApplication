package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Customer;

public class CustomerDAO extends GenericDAO<Customer>{
	public CustomerDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Customer.class, tableName, pool);
	}
	//CustomerDAO action starts here

	public Customer[] getUsers() throws RollbackException {
		Customer[] users = match();

		return users;
	}
	
	public void changePassword(int customerID, String password) throws RollbackException {
        try {
        	System.out.println("in change pass word method");
        	Transaction.begin();
			Customer dbUser = read(customerID);
			
			if (dbUser == null) {
				throw new RollbackException("User  no longer exists");
			}
			
			dbUser.setPassword(password);
			
			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive()) Transaction.rollback();
		}
	}
	
	public void withdrawalCash(int customer_id, long amount) throws RollbackException{
		Customer[] list = match(MatchArg.equals("customer_id",customer_id));
		long cash = list[0].getCash();
		cash = cash - amount;
		return;
	}
	public int getCustomerId(String customerName) throws RollbackException{
		Customer[] users = match(MatchArg.equals("username", customerName));
		System.out.println("CustomerName In DAO" + customerName);
		int userId = users[0].getCustomer_id();
		return userId;
	}
	//colins original function
	/*public void changeBalance(int customer_id, double amount)throws RollbackException{
		Transaction.begin();
		Customer[] list = match(MatchArg.equals("customer_id",customer_id));
		long cash = list[0].getCash();
		System.out.println("In DAO: " + "cash"+ cash + "amount"+amount);
		cash = Math.round(((double)cash - amount));
	//datavase not updated??
		System.out.println("New cash amount" + cash);
		Transaction.commit();
		return;
	}*/
//change by amey
	public void changeBalance(int customer_id, double amount) {
		try {
			Transaction.begin();
			Customer[] list = match(MatchArg.equals("customer_id", customer_id));
			//decrease available cash when a request for buy is submitted
			//check if the available cash is greater than cash then proceed
			long cash = list[0].getAvailableCash();
			System.out.println("In DAO: " + "cash" + cash + "amount" + amount);
			cash = Math.round(((double) cash - amount));
			// updated the cash field in the database check with bin to update available cash or only cash field
			list[0].setAvailableCash(cash);
			update(list[0]);
			System.out.println("New cash amount" + cash);
			Transaction.commit();
			return;
		} catch (RollbackException e) {
			if(Transaction.isActive()) Transaction.rollback();
			e.printStackTrace();
		}
	}

	public Customer getCustomer(String customerName) throws RollbackException {
		Customer[] users = match(MatchArg.equals("username", customerName));
	
		return users[0];
	}
	
	public long getBalance(int customer_id) throws RollbackException{
		Customer[] list = match(MatchArg.equals("customer_id",customer_id));
		long cash = list[0].getAvailableCash();
		return cash;
	}
	

}

package databeans;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("transaction_id")
public class Transaction {
	private int transaction_id, customer_id, fund_id;
	private String transaction_type;
	
	//change type from String to Date
	private Date execute_date;
	private long shares, amount;    

	//Getter
	public int getTransaction_id() {
		return transaction_id;
	}
	
	public int getCustomer_id() {
		return customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}
	
	public Date getExecute_date() {
		return execute_date;
	}
	
	public String getTransaction_type() {
		return transaction_type;
	}
	
	public long getShares() {
		return shares;
	}
	
	public long getAmount() {
		return amount;
	}
	
	//Setter
	public void setTransaction_id(int i){
		transaction_id = i;
	}
	
	public void setCustomer_id(int i){
		customer_id = i;
	}
	
	public void setFund_id(int i){
		fund_id = i;
	}
	
	public void setExecute_date(Date d) {
		execute_date = d;
	}
	
	public void setShares(long l) {
		shares = l;
	}
	
	public void setTransaction_type(String s) {
		transaction_type = s;
	}
	
	public void setAmount(long l) {
		amount = l;
	}
}

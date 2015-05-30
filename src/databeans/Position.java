package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id,fund_id")
public class Position {
	private int customer_id, fund_id;
	private long shares, availableShares;    

	//Getter
	public int getCustomer_id() {
		return customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}
	
	public long getShares() {
		return shares;
	}
	
	public long getAvailableShares() {
		return availableShares;
	}
	
	//Setter
	public void setCustomer_id(int i){
		customer_id = i;
	}
	
	public void setFund_id(int i){
		fund_id = i;
	}
	
	public void setShares(long l) {
		shares = l;
	}
	
	public void setAvailableShares(long l) {
		availableShares = l;
	}
	
}

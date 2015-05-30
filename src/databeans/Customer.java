package databeans;

import org.genericdao.PrimaryKey;

@PrimaryKey("customer_id")
public class Customer {
	private int customer_id, zip;
	private String  customerName = null;
	private String  Password = "*";
	private String username, password, firstName, lastName, addr_line1, addr_line2, city, state;

	private long cash, availableCash;   // 2 decimal 

	public String  getCustomerName()       { return customerName;       }

	//Getter
	public int getCustomer_id() {
		return customer_id;
	}

	public int getZip() {
		return zip;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getAddr_line1() {
		return addr_line1;
	}
	
	public String getAddr_line2() {
		return addr_line2;
	}	
	
	public String getCity() {
		return city;
	}
	
	public String getState() {
		return state;
	}
	
	public long getCash() {
		return cash;
	}
	
	public long getAvailableCash() { return availableCash; }
	
	//Setter
	public void setCustomer_id(int i){
		customer_id = i;
	}

	public void setUsername(String s){
		username = s;
	}
	public void setPassword(String s) {
		password = s;
	}
	public void setFirstName(String s) {
		firstName = s;
	}
	public void setLastName(String s) {
		lastName = s;
	}
	
	public void setAddr_line1(String s) {
		addr_line1 = s;
	}
	
	public void setAddr_line2(String s) {
		addr_line2 = s;
	}
	
	public void setCity(String s) {
		city = s;
	}
	
	public void setState(String s) {
		state = s;
	}
	
	public void setZip(int i) {
		zip = i;
	}
	
	public void setCash(long l) {
		cash = l;
	}
	
	public void setAvailableCash(long l) {
		availableCash = l; 
	}
}

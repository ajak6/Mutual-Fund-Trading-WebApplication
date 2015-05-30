package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositCheckForm extends FormBean{
	private String username;
	private String amount;
	
	public String getUsername() { return username; }
	public String getAmount() { return amount;}
	
	public void setUsername(String i) { username = i; }
	public void setAmount(String s) { amount = s; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
try{
		if (Double.parseDouble(amount) <= 0) {
			errors.add("Amount must be positive");
		}
		if (Double.parseDouble(amount) > 1000000000) {
			errors.add("Maximum amount is 1 billion");
		}
		
}catch(NumberFormatException e){
	errors.add("Please enter a valid amount");
}
		if (errors.size() > 0) {
			return errors;
		}
		
		return errors;
	}
}

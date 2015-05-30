package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyFundForm extends FormBean {
	private String fundName;
	private String fundSymbol;
	private String amount;
	private String action;

	public String getFundName() {
		return fundName;
	}

	public String getFundSymbol() {
		return fundSymbol;
	}

	public String  getAmount() {
		return amount;

	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setAmount(String a) {

		amount = a;

	}

	public void setFundName(String s) {
		fundName = s;

	}

	public void setFundSymbol(String s) {
		fundSymbol = s;
	}

	public boolean isPresent() {
		return action != null;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fundName == null || fundName.length() == 0
				|| fundName.trim().length() == 0) {
			errors.add("Please select a fund name");
		}
		try {
			Double a = Double.parseDouble(amount);
			System.out.println("amount"+amount);
			if (a <= 0.00) {
				errors.add("Amount must be positive");//changed
			}
			
	
		} catch (Exception e) {
			errors.add("Enter a valid amount");
		}

		if (errors.size() > 0) {
			return errors;

		}
		return errors;
	}
}

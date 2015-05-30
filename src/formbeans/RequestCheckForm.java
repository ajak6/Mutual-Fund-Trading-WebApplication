package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String amount;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String s) {
		amount = trimAndConvert(s, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		// added a check if amount is numeric then we have to return error
		try {
			if (Double.parseDouble(amount) <= 0) {
				errors.add("Amount must be positive");
			}
		} catch (Exception e) {
			errors.add("Please enter a valid amount");
		}
		if (errors.size() > 0) {
			return errors;
		}

		return errors;
	}
}

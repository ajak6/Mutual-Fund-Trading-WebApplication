package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustRegisterForm extends FormBean {
	String custFirstName;
	String custLastName;
	String custAddress1;
	String custAddress2;
	String custCity;
	String custState;
	String custUsername;
	String custPassword;
	String custZip;
	String custCashValue;

	public String getCustFirstName() {
		return custFirstName;
	}

	public void setCustFirstName(String custFirstName) {
		this.custFirstName = trimAndConvert(custFirstName,"<>\"");;
	}

	public String getCustLastName() {
		return custLastName;
	}

	public void setCustLastName(String custLastName) {
		this.custLastName = trimAndConvert(custLastName,"<>\"");
	}

	public String getCustAddress1() {
		return custAddress1;
	}

	public void setCustAddress1(String custAddress1) {
		this.custAddress1 = trimAndConvert(custAddress1,"<>\"");
		
	}

	public String getCustAddress2() {
		return custAddress2;
	}

	public void setCustAddress2(String custAddress2) {
		this.custAddress2 = custAddress2;
	}

	public String getCustCity() {
		return custCity;
	}

	public void setCustCity(String custCity) {
		this.custCity = trimAndConvert(custCity,"<>\"");
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = trimAndConvert(custState,"<>\"");
	}

	public String getCustZip() {
		return custZip;
	}

	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}

	public String getCustCashValue() {
		return custCashValue;
	}

	public void setCustCashValue(String custCashValue) {
		this.custCashValue = custCashValue;
	}

	public String getCustUsername() {
		return custUsername;
	}

	public void setCustUsername(String custUsername) {
		 this.custUsername = trimAndConvert(custUsername, "<>\"");
//		this.custUsername = custUsername;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (custFirstName.contains("&gt;") || custFirstName.contains("&lt;") ||custLastName.contains("&quot;")) {
			errors.add("Special characters are not allowed");
		}

		else if (custLastName.contains("&gt;") || custLastName.contains("&lt;")|| custLastName.contains("&quot;")) {
			errors.add("Special characters are not allowed");
		} else if (custUsername.contains("&gt;") || custUsername.contains("&lt;")|| custUsername.contains("&quot;")) {
			errors.add("Special characters are not allowed");
		} else if (custAddress1.contains("&gt;") || custAddress1.contains("&lt;")||custAddress1.contains("&quot;")) {
			errors.add("Special characters are not allowed");
			setCustAddress1(trimAndConvert(custAddress1,"<>\""));
		} else if (custAddress2.contains("&gt;") || custAddress2.contains("&lt;") || custAddress2.contains("&quot;")) {
			errors.add("Special characters are not allowed");
		} else if (custState.contains("&gt;") || custState.contains("&lt;")) {
			errors.add("Special characters are not allowed ");
			setCustState(trimAndConvert(custState,"<>\""));
		} else if (custCity.contains("&gt;") || custCity.contains("&lt;")) {
			errors.add("Special characters are not allowed");
		}
		if (custFirstName == null || custFirstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (custLastName == null || custLastName.length() == 0) {
			errors.add("Last Name is required");
		}
		if (custUsername == null || custUsername.length() == 0) {
			errors.add("User Name is required");
		}

		if (custPassword == null || custPassword.length() == 0) {
			errors.add("Password is required");
		}

		if ((custAddress1 == null || custAddress1.length() == 0)
				&& ((custAddress2 == null || custAddress2.length() == 0))) {
			errors.add("Enter Address information ");
		}
		if (custState == null || custState.length() == 0) {
			errors.add("Enter the residence state ");
		}
		if (custCity == null || custCity.length() == 0) {
			errors.add("Enter the Cityname");
		}
		if (custZip == null || custZip.length() == 0) {
			errors.add("Enter the zip code");
		} else {
			try {
				Integer.parseInt(custZip);
			} catch (NumberFormatException e) {
				errors.add("Zip code should be numeric");
			}
		}
		if (Double.parseDouble(custCashValue) > 1000000.00) {
			errors.add("The maximum of initial amount is 1 million");
		}
		if (custCashValue == null || custCashValue.length() == 0) {
			errors.add("Enter the intial cash value");
		} else {
			try {

				System.out.println(Double.parseDouble(custCashValue));
			} catch (NumberFormatException e) {
				errors.add("Please enter a numeric cash value");
			}
		}

		return errors;
	}
}

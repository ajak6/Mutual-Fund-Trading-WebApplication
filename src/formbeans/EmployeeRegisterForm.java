package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class EmployeeRegisterForm extends FormBean {

	String empUsername;
	String empPassword;
	String empFirstName;
	String empLastName;

	public String getEmpUsername() {
		return empUsername;
	}

	public void setEmpUsername(String empUsername) {
		this.empUsername = trimAndConvert(empUsername, "<>\"");
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = trimAndConvert(empPassword, "<>\"");
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = trimAndConvert(empFirstName, "<>\"");
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = trimAndConvert(empLastName, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (empFirstName.contains("&gt;") || empFirstName.contains("&lt;")
				|| empFirstName.contains("&quot;")) {
			errors.add("Special characters are not allowed");
		}

		else if (empLastName.contains("&gt;") || empLastName.contains("&lt;")
				|| empLastName.contains("&quot;")) {
			errors.add("Special characters are not allowed");
		} else if (empUsername.contains("&gt;") || empUsername.contains("&lt;")) {
			errors.add("Special characters are not allowed");
		}
		if (empFirstName == null || empFirstName.length() == 0) {
			errors.add("First Name is required");
		}

		if (empLastName == null || empLastName.length() == 0) {
			errors.add("Last Name is required");
		}

		if (empUsername == null || empUsername.length() == 0) {
			errors.add("User Name is required");
		}

		if (empPassword == null || empPassword.length() == 0) {
			errors.add("Password is required");
		}

		return errors;
	}
}

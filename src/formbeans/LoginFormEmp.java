package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginFormEmp extends FormBean {
	private String eEmail;
	private String ePassword;

	public String geteEmail() {
		return eEmail;
	}

	public String getePassword() {
		return ePassword;
	}

	public void seteEmail(String s) {
		eEmail = trimAndConvert(s, "<>\"");
	}

	public void setePassword(String s) {
		ePassword = s.trim();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (eEmail == null || eEmail.length() == 0) {
			errors.add("Please enter username");
		}

		if (ePassword == null || ePassword.length() == 0) {
			errors.add("Please enter password");
		}

		return errors;
	}
}
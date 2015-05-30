
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePwdFormEmp extends FormBean {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (oldPassword.trim().length() == 0) {
			errors.add("Please enter current Password");
		}
		if (newPassword.trim().length() == 0) {
			errors.add("Please enter a new password");
		}
		if (confirmPassword.trim().length() == 0) {
			errors.add("Please re-enter new password");
		}
		if (!confirmPassword.equals(newPassword)) {
			errors.add(" Password does not match");
		}
		return errors;
	}
}

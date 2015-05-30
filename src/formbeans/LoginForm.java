package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String cEmail;
	private String eEmail;
	private String cPassword;
	private String ePassword;	
	public String getcEmail() {
		return cEmail;
	}

	public String getcPassword() {
		return cPassword;
	}
	
	public void setcEmail(String s) { cEmail = trimAndConvert(s,"<>\"");  }
	public void setcPassword(String s) {cPassword = s.trim();                  }
	public void seteEmail(String s) { eEmail = trimAndConvert(s,"<>\"");  }
	public void setePassword(String s) {ePassword = s.trim();                  }

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (cEmail == null || cEmail.length() == 0) {
			errors.add("Please enter username");
		}
	
		
		if (cPassword == null || cPassword.length() == 0) {
			errors.add("Please enter password");
		}
		
		return errors;
	}
}
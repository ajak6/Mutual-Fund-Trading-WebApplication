package forms;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	String fundSym;
	String fundName;
	public String getFundSym() {
		return fundSym;
	}
	public void setFundSym(String fundSym) {
		this.fundSym = fundSym;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	

}
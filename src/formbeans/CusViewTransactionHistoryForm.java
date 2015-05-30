package formbeans;

import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;

public class CusViewTransactionHistoryForm extends FormBean {
	private String transactionDate;
	private String operation;
	private String fundName;
	private long shareNumbers;
	private long sharePrice;
	private long dollarAmount;
	
	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public long getShareNumbers() {
		return shareNumbers;
	}
	public void setShareNumbers(long shareNumbers) {
		this.shareNumbers = shareNumbers;
	}
	public long getSharePrice() {
		return sharePrice;
	}
	public void setSharePrice(long sharePrice) {
		this.sharePrice = sharePrice;
	}
	public long getDollarAmount() {
		return dollarAmount;
	}
	public void setDollarAmount(long dollarAmount) {
		this.dollarAmount = dollarAmount;
	}
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		return errors;
	}
	
	
}

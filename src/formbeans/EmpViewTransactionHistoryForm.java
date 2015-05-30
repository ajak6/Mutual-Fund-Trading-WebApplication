package formbeans;

import org.mybeans.form.FormBean;

public class EmpViewTransactionHistoryForm extends FormBean{
	private String customer;
	private String transactionDate;
	private String operation;
	private String fundName;
	private long shareNumbers;
	private long sharePrice;
	private long dollarAmount;
	//private String action;
	
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
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
//	public String getAction() {
//		return action;
//	}
//	public void setAction(String action) {
//		this.action = action;
//	}
//	public boolean isPresent() {
//		return action != null;
//	}
	

}

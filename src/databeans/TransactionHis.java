package databeans;

import java.util.Date;

public class TransactionHis {
	private String share_number;
	private String transaction_type, fund_name;
	private Date execute_date;
	private String share_price, amount;
	
	public  String getShare_number() {
		return share_number;
	}
	public void setShare_number(String share_number) {
		this.share_number = share_number;
	}
	public Date getExecute_date() {
		return execute_date;
	}
	public void setExecute_date(Date execute_date) {
		this.execute_date = execute_date;
	}
	public String getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	public String getFund_name() {
		return fund_name;
	}
	public void setFund_name(String fund_name) {
		this.fund_name = fund_name;
	}
	public String getShare_price() {
		return share_price;
	}
	public void setShare_price(String share_price) {
		this.share_price = share_price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
    

	
}

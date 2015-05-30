package databeans;

import java.util.Date;

public class FundInfo {
	private int fund_id;
	private String name, symbol; 
	private Date price_date;
	private double shares, availableShares, value, price;
	public double getValue() {
		return value;
	}
	public double getAvailableShares() {
		return availableShares;
	}
	public void setAvailableShares(double availableShares) {
		this.availableShares = availableShares;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Date getPrice_date() {
		return price_date;
	}
	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getShares() {
		return shares;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
}

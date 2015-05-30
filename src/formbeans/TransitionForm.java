package formbeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mybeans.form.FormBean;

public class TransitionForm extends FormBean{
	
	private String[] fundIdList, priceList;
	private String transitDay;
	
	public String[] getFundIdList() {return fundIdList;}
	public String[] getPriceList() {return priceList; }
	public String getTransitDay() {return transitDay; }
	
	public void setFundIdList(String[] i) {
		fundIdList = i;
	}
	
	public void setPriceList(String[] l) {
		priceList = l;
	}
	
	public void setTransitDay(String d) {transitDay = d; }
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (transitDay == null) {
			errors.add("Transition Date cannot be empty");
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		int month = 0;
		int day = 0;
		try {
			Date today = formatter.parse(transitDay);
			month = Integer.parseInt(transitDay.substring(0,2));
			day = Integer.parseInt(transitDay.substring(3,5));
		} catch (ParseException e) {
			errors.add("Invalid Input!");
			return errors;
		} catch (NumberFormatException e) {
			errors.add("Invalid Input!");
			return errors;
		}
		
		if(month>12||month<1||day>31||day<1){
			errors.add("Invalid date!");
			return errors;
		}
		
		
		if (priceList == null || priceList.length == 0) {
			errors.add("Please input price.");
		}
		
		if (priceList != null) {
			for (int i = 0; i < priceList.length; i++) {
				if (priceList[i].equals("")) {
					errors.add("Please input price.");
				}
				if(priceList[i].isEmpty()){
					errors.add("Please input price.");
				}else{
					boolean range = (Double.parseDouble(priceList[i]) > 0) 
							&& (Double.parseDouble(priceList[i]) < 1000);

					if (!range){
							
						errors.add("Input price must be less than $1000. Please input reasonable fund price.");
					}
					
					if(((Double.parseDouble(priceList[i])*1000)/10)%1>0){
						errors.add("Only two decimal price is allowed");
					}
					if((Double.parseDouble(priceList[i]))<1.00){
						errors.add("The minimum price of fund is 1 $.");
					}
				}
				
			}
		}
		
		return errors;

	}
	
}

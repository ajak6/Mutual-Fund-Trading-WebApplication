package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Fund_Price_History;

public class Fund_Price_HistoryDAO extends GenericDAO<Fund_Price_History>{
	public Fund_Price_HistoryDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Fund_Price_History.class, tableName, pool);
	}
	
	//Fund_Price_HistoryDAO action starts here
	
	public long getLastPrice(int fund_id) throws RollbackException{
		Fund_Price_History[] fundPrice = match(MatchArg.equals("fund_id", fund_id));
		if(fundPrice.length>0){
			return fundPrice[fundPrice.length-1].getPrice();
		}
		return 0;
	}

	public Fund_Price_History[] getAllHistory() throws RollbackException{
		Fund_Price_History[] allHistory = match();
		return allHistory;
	}
	
}

package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databeans.Fund;

public class FundDAO extends GenericDAO<Fund> {
	public FundDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Fund.class, tableName, pool);
	}
	
	//FundDAO action starts here
	
	public Fund[] getFundList() throws RollbackException{
		Fund[] fund = match();
		return fund;
	}

	public int getFundId(String fundName) throws RollbackException{
		Fund[] fund = match(MatchArg.equals("name", fundName));
		int fundId = fund[0].getFund_id();
		return fundId;
	}
	
	public String getFundName(int fundId) throws RollbackException{
		Fund[] fund = match(MatchArg.equals("fund_id", fundId));
		String fundName = fund[0].getName();
		return fundName;
	}
}

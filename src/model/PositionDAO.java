package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Customer;
import databeans.Position;

public class PositionDAO extends GenericDAO<Position>{
	public PositionDAO(String tableName, ConnectionPool pool) throws DAOException {
		super(Position.class, tableName, pool);
	}
	
	public Position[] getPositions(int customerID) throws RollbackException{
		return match(MatchArg.equals("customer_id", customerID));
	}
	
	// a method to get available shares - Stella
	public long getAvailableShares(int customerID) throws RollbackException{
		Position[] positionList = match(MatchArg.equals("customer_id", customerID));
		return positionList[0].getAvailableShares();
	}
	// a method to set available shares - Stella
	public void setAvailableShares(int customerID, int fundID, long share){
		try {
			Transaction.begin();
			Position[] list = match(MatchArg.equals("customer_id", customerID));
			list[0].setAvailableShares(share);
			update(list[0]);
			Transaction.commit();
			return;
		} catch (RollbackException e) {
			if(Transaction.isActive()) Transaction.rollback();
			e.printStackTrace();
		}
	}
}

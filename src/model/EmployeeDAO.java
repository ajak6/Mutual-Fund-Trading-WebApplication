package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.Employee;
import databeans.Customer;

public class EmployeeDAO extends GenericDAO<Employee> {
	public EmployeeDAO(String tableName, ConnectionPool pool)
			throws DAOException {
		super(Employee.class, tableName, pool);
	}

	// EmployeeDAO action starts here
	public Employee[] getUsers() throws RollbackException {
		Employee[] users = match();
		Arrays.sort(users); // We want them sorted by last and first names (as
							// per User.compareTo());
		return users;
	}

	public void setPassword(String employeeName, String password)
			throws RollbackException {
		try {
			Transaction.begin();
			Employee dbUser = read(employeeName);

			if (dbUser == null) {
				throw new RollbackException("User " + employeeName
						+ " no longer exists");
			}

			dbUser.setPassword(password);

			update(dbUser);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}
	}

	public void changePassword(String username, String newPassword) {
		System.out.println("employee password changing");
		try {
			Transaction.begin();
			Employee e = read(username);
			e.setPassword(newPassword);
			update(e);
			Transaction.commit();
		} catch (RollbackException e) {
			e.printStackTrace();
		}

	}

}

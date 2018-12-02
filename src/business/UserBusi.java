package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import entity.User;

public class UserBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
	private String sql = "";
	
	public int login(User user) throws SQLException {
		String pass = "";
		Object a[] = new Object[1];
		a[0] = user.getId();
		sql = "select password from user where id=?";
		con = DAO.getConnection();
		ppst = DAO.getPreparedStatement(con, sql, a);
		rs = DAO.getResultSet(ppst);
		while(rs.next()) {
			pass = rs.getString(1);
		}
		DAO.closeConnection(con);
		if(pass.equals(user.getPassword())) {
			return 1;
		}else {
			return 0;
		}
		
	}

}

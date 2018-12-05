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
	private int rs_2;
	private String sql = "";
	
	public int login(User user) throws SQLException {
		sql = "select password from user where id=?";
		String pass = "";
		Object a[] = new Object[1];
		a[0] = user.getId();
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
	
	public int register(User user) throws SQLException {
		sql = "insert into user (id,password) values(?,?);";
		Object a[] = new Object[2];
		a[0] = user.getId();
		a[1] = user.getPassword();
		con = DAO.getConnection();
		ppst = DAO.getPreparedStatement(con, sql, a);
		rs_2 = DAO.update(ppst);
		DAO.closeConnection(con);
		
		System.out.println("表user "+rs_2+" 行受影响");
		return rs_2;
	}

}

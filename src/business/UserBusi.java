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
		
		System.out.println("��user "+rs_2+" ����Ӱ��");
		return rs_2;
	}

	public User getAllInfo(String id) {
		sql = "select * from user where id = ? ;";
		User user = new User();
		user.setId(id);
		Object a[] = new Object[1];
		a[0] = user.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
			while(rs.next()) {
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setAge(rs.getInt("age"));
				user.setPortrait(rs.getString("portrait"));
				user.setAddress(rs.getString("address"));
			}
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
}

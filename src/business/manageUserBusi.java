package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import entity.User;

public class manageUserBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
//	private int rs_2 = 0;
	private String sql = "";
	Object a[] = null;
	
	public int getUserCount() throws SQLException {
		int count = 0;
		sql = "select count(*) from user;";
		a = new Object[0];
		con = DAO.getConnection();
		ppst = DAO.getPreparedStatement(con, sql, a);
		rs = DAO.getResultSet(ppst);
		while(rs.next()){
			count = rs.getInt(1);
		}
		DAO.closeConnection(con);
		return count;
	}
	
	public User[] getUserList(int page,int pagesize) throws SQLException{
		User list[] = new User[pagesize];
		int i=0;
		
		sql="select id,name from user limit ?,?;";
		a = new Object[2];
		a[0] = (page-1)*pagesize;
		a[1] = pagesize;
		con = DAO.getConnection();
		ppst = DAO.getPreparedStatement(con, sql, a);
		rs = DAO.getResultSet(ppst);

		while(rs.next()){	
			User user = new User();
			user.setId(rs.getString(1));
			user.setName(rs.getString(2));
			list[i]=user;
			i++;
		}
		DAO.closeConnection(con);
		return list;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;	

public class DAO {
	
	private static Connection con = null; 
	private static PreparedStatement ppst = null;
	private static ResultSet rs = null;
	private static int rs_2;
	private static int i;
	
	private static ComboPooledDataSource cp = new ComboPooledDataSource();
	
	public static Connection getConnection() throws SQLException {
		System.out.println("到达连接池，下一步------获取连接");
		con = cp.getConnection();
		
		return con;
	}
	
	public static void closeConnection(Connection con) throws SQLException {
		if(con != null) {
			System.out.println("关闭连接");
			con.close();
		}
		
	}
	
	public static PreparedStatement getPreparedStatement(Connection con,String sql,Object a[]) throws SQLException {
		if(con != null) {
			ppst = con.prepareStatement(sql);
		}
		for(i=0;i<a.length;i++) {
			ppst.setObject(i+1, a[i]);
			System.out.println("sql语句参数："+a[i]);
		}
		
		return ppst;
	}
	
	public static ResultSet getResultSet(PreparedStatement ppst) throws SQLException {
		if(ppst != null) {
			rs = ppst.executeQuery();
		}
		
		return rs;
	}
	
	public static int update(PreparedStatement ppst) throws SQLException {
		if(ppst != null) {
			rs_2 = ppst.executeUpdate();
		}
		
		return rs_2;
	}

}

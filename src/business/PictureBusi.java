package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.DAO;
import entity.Picture;
import entity.Recipe;

public class PictureBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
//	private int rs_2;
	private String sql = "";
	Object a[] = null;
	
	public Iterator<Picture> getPicture(Recipe recipe){
		List<Picture> list = null;
		Iterator<Picture> listall = null;
		a = new Object[1];
		sql="select number,url from r_picture where recipe = ? ;";
		a[0] = recipe.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
			list = new ArrayList<Picture>();
			while(rs.next()){	
				Picture picture = new Picture();
				picture.setNumber(rs.getInt(1));
				picture.setUrl(rs.getString(2));
				list.add(picture);
			}	
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listall = list.iterator();
		
		return listall;
	} 

}

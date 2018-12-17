package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.DAO;
import entity.Include;
import entity.Recipe;

public class IncludeBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
//	private int rs_2;
	private String sql = "";
	Object a[] = null;
	
	public Iterator<Include> getInclude(Recipe recipe){
		List<Include> list = null;
		Iterator<Include> listall = null;
		a = new Object[1];
		sql="select include.material,material.name,include.quantity "
				+ "from include,material "
				+ "where include.material=material.id and include.recipe = ? ;";
		a[0] = recipe.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
			list = new ArrayList<Include>();
			while(rs.next()){	
				Include include=new Include();
				include.setMaterial(rs.getInt(1));
				include.setMatName(rs.getString(2));
				include.setQuantity(rs.getString(3));
				list.add(include);
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

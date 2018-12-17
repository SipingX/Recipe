package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import entity.Recipe;

public class RecipeBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
//	private int rs_2;
	private String sql = "";
	Object a[] = null;
	
	public Recipe getRecipePageInfo(Recipe recipe) throws SQLException {
		sql = "select author,name,rating,browse,complexity,minute,tasty,method,description,address from recipe where id = ?;";
		a = new Object[1];
		a[0] = recipe.getId();
		con = DAO.getConnection();
		ppst = DAO.getPreparedStatement(con, sql, a);
		rs = DAO.getResultSet(ppst);
		while(rs.next()) {
			recipe.setAuthor(rs.getString("author"));
			recipe.setName(rs.getString("name"));
			recipe.setRating(rs.getString("rating"));
			recipe.setBrowse(rs.getInt("browse"));
			recipe.setComplexity(rs.getString("complexity"));
			recipe.setMinute(rs.getInt("minute"));
			recipe.setTasty(rs.getString("tasty"));
			recipe.setMethod(rs.getString("method"));
			recipe.setDescription(rs.getString("description"));
			recipe.setAddress(rs.getString("address"));
		}
		DAO.closeConnection(con);
		
		return recipe;
	}

}

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
	private int rs_2;
	private String sql = "";
	Object a[] = null;
	
	public Recipe getRecipePageInfo(Recipe recipe) {
		sql = "select author,name,category,rating,browse,complexity,minute,tasty,method,description,address from recipe where id = ?;";
		a = new Object[1];
		a[0] = recipe.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
			while(rs.next()) {
				recipe.setAuthor(rs.getString("author"));
				recipe.setName(rs.getString("name"));
				recipe.setCategory(rs.getString("category"));
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return recipe;
	}
	
	public int InitiateOneRecipe() {
		int r = 0;
		sql = "insert \r\n" + 
				"into recipe (author,name,category,complexity,minute,tasty,method)\r\n" + 
				"value('init','init','init','init',0,'init','init')";
		a = new Object[0];
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs_2 = DAO.update(ppst);
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r = rs_2;
		
		return r;
	}
	
	public int getMaxId() {
		int id = 0;
		sql = "select MAX(id) from recipe ;";
		a = new Object[0];
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
			while(rs.next()) {
				id = rs.getInt(1);
			}
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
	public int upload(Recipe recipe) {
		int r = 0;
		sql = "update recipe\r\n" + 
				"set author=?,name=?,category=?,complexity=?,minute=?,tasty=?,method=?,description=?,address=?\r\n" + 
				"where id = ?";
		a = new Object[10];
		a[0] = recipe.getAuthor();
		a[1] = recipe.getName();
		a[2] = recipe.getCategory();
		a[3] = recipe.getComplexity();
		a[4] = recipe.getMinute();
		a[5] = recipe.getTasty();
		a[6] = recipe.getMethod();
		a[7] = recipe.getDescription();
		a[8] = recipe.getAddress();
		a[9] = recipe.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs_2 = DAO.update(ppst);
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r = rs_2;
		
		return r;
	}
	
	public boolean delete(int id) {
		boolean bool = false;
		rs_2 = 0;
		sql = "delete from recipe where id = ? ;";
		a = new Object[1];
		a[0] = id;
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs_2 = DAO.update(ppst);
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs_2 == 1) {
			bool = true ;
		}
		
		return bool;
	}

}

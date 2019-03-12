package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAO;
import entity.Recipe;
import entity.Step;

public class StepBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
	private int rs_2;
	private String sql = "";
	Object a[] = null;
	
	public List<Step> getStep(Recipe recipe){
		List<Step> list = new ArrayList<Step>();
		a = new Object[1];
		sql="select sequence,description,picture from step where recipe = ?;";
		a[0] = recipe.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
			while(rs.next()){	
				Step step = new Step();
				step.setSequence(rs.getInt(1));
				step.setDescription(rs.getString(2));
				step.setPicture(rs.getString(3));
				list.add(step);
			}	
			DAO.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	} 
	
	public int upload(Step step) {
		int r = 0;
		sql = "insert \r\n" + 
				"into step (recipe,sequence,description)\r\n" + 
				"value(?,?,?)";
		a = new Object[3];
		a[0] = step.getRecipe();
		a[1] = step.getSequence();
		a[2] = step.getDescription();
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

}

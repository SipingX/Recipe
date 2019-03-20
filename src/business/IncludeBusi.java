package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisUtils;

import dao.DAO;
import entity.Include;
import entity.Recipe;

public class IncludeBusi {
	
	private Connection con = null;
	private PreparedStatement ppst = null;
	private ResultSet rs = null;
	private int rs_2;
	private String sql = "";
	Object a[] = null;
	
	public List<Include> getInclude(Recipe recipe){
		List<Include> list = new ArrayList<Include>();
/*		a = new Object[1];
		sql="select include.material,material.name,include.quantity "
				+ "from include,material "
				+ "where include.material=material.id and include.recipe = ? ;";
		a[0] = recipe.getId();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs = DAO.getResultSet(ppst);
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
		}*/
		
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			list = sqlSession.selectList("mapper.IncludeMapper.selectIncludeByRecipeId",recipe);
		}finally {
			sqlSession.close();
		}
		
		return list;
	} 
	
	public int upload(Include include) {
		int r = 0;
/*		sql = "insert \r\n" + 
				"into include (recipe,material,quantity)\r\n" + 
				"value(?,?,?);";
		a = new Object[3];
		a[0] = include.getRecipe();
		a[1] = include.getMaterial();
		a[2] = include.getQuantity();
		try {
			con = DAO.getConnection();
			ppst = DAO.getPreparedStatement(con, sql, a);
			rs_2 = DAO.update(ppst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r = rs_2;*/
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			r = sqlSession.insert("mapper.IncludeMapper.insertInclude",include);
			if(r == 1){
		        System.out.println("您成功插入了 "+r+" 份食谱Include！");
		        sqlSession.commit();
		    }else{
		        System.out.println("执行插入食谱Include操作失败！！！");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}

}

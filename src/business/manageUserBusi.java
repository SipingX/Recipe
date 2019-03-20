package business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.MybatisUtils;

import entity.User;

public class manageUserBusi {
	
/*	public int getUserCount() throws SQLException {
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
	}*/
	
/*	public User[] getUserList(int page,int pagesize) throws SQLException{
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
	}*/
	
	public PageInfo<User> getUserList(int page,int pagesize) throws Exception{
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			PageHelper.startPage(page, pagesize);//要第一页的二条记录
			List<User> users = sqlSession.selectList("mapper.UserMapper.selectUser");
			PageInfo<User> pageInfo = new PageInfo<User>(users);
			return pageInfo;
		}finally {
			// 5、关闭SqlSession
			sqlSession.close();
		}
	}

}

package business;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.MybatisUtils;

import entity.User;

public class manageUserBusi {
	
	public PageInfo<User> getUserList(int page,int pagesize) throws Exception{
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			PageHelper.startPage(page, pagesize);
			List<User> users = sqlSession.selectList("mapper.UserMapper.selectUser");
			PageInfo<User> pageInfo = new PageInfo<User>(users);
			return pageInfo;
		}finally {
			sqlSession.close();
		}
	}

}

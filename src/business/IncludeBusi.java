package business;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisUtils;

import entity.Include;
import entity.Recipe;

public class IncludeBusi {
	
	public List<Include> getInclude(Recipe recipe){
		List<Include> list = new ArrayList<Include>();
		
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

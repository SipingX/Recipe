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
		        System.out.println("���ɹ������� "+r+" ��ʳ��Include��");
		        sqlSession.commit();
		    }else{
		        System.out.println("ִ�в���ʳ��Include����ʧ�ܣ�����");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}

}

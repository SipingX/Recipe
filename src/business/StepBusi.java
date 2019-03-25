package business;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisUtils;

import entity.Recipe;
import entity.Step;

public class StepBusi {
	
	public List<Step> getStep(Recipe recipe){
		List<Step> list = new ArrayList<Step>();
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			list = sqlSession.selectList("mapper.StepMapper.selectStepByRecipeId",recipe);
		}finally {
			sqlSession.close();
		}
		
		return list;
	} 
	
	public int upload(Step step) {
		int r = 0;

		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			r = sqlSession.insert("mapper.StepMapper.insertStep",step);
			if(r == 1){
		        System.out.println("���ɹ������� "+r+" ��ʳ�ײ��裡");
		        sqlSession.commit();
		    }else{
		        System.out.println("ִ�в���ʳ�ײ������ʧ�ܣ�����");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}

}

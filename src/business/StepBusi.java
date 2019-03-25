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
		        System.out.println("您成功插入了 "+r+" 个食谱步骤！");
		        sqlSession.commit();
		    }else{
		        System.out.println("执行插入食谱步骤操作失败！！！");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}

}

package business;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisUtils;

import entity.Recipe;

public class RecipeBusi {
	
	public Recipe getRecipePageInfo(Recipe recipe) {
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			recipe = sqlSession.selectOne("mapper.RecipeMapper.selectRecipePageInfo",recipe);
		}finally {
			sqlSession.close();
		}
		
		return recipe;
	}
	
	public int InitiateOneRecipe() {
		int r = 0;
		
		Recipe recipe = new Recipe();
		recipe.setAuthor("init");
		recipe.setName("init");
		recipe.setCategory("init");
		recipe.setComplexity("init");
		recipe.setMinute(0);
		recipe.setTasty("init");
		recipe.setMethod("init");
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			r = sqlSession.insert("mapper.RecipeMapper.insertRecipe",recipe);
			if(r == 1){
		        System.out.println("您成功初始化了 "+r+" 份食谱！");
		        sqlSession.commit();
		    }else{
		        System.out.println("执行初始化食谱操作失败！！！");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}
	
	public int getMaxId() {
		int id = 0;
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			id = sqlSession.selectOne("mapper.RecipeMapper.selectRecipeMaxId");
		}finally {
			sqlSession.close();
		}
		
		return id;
	}
	
	public int upload(Recipe recipe) {
		int r = 0;
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			r = sqlSession.update("mapper.RecipeMapper.updateRecipe",recipe);
			if(r == 1){
		        System.out.println("您成功更新了 "+r+" 份食谱！");
		        System.out.println(recipe.toString());
		        sqlSession.commit();
		    }else{
		        System.out.println("执行更新食谱操作失败！！！");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}
	
	public boolean delete(Recipe recipe) {
		boolean bool = false;
		int r = 0;
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			r = sqlSession.selectOne("mapper.RecipeMapper.deleteRecipe",recipe);
			if(r == 1){
		        System.out.println("您成功删除了 "+r+" 份食谱！");
		        sqlSession.commit();
		    }else{
		        System.out.println("执行删除食谱操作失败！！！");
		    }
		}finally {
			sqlSession.close();
		}
		
		if(r == 1) {
			bool = true ;
		}
		
		return bool;
	}

}

package business;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.util.MybatisUtils;

import entity.Picture;
import entity.Recipe;

public class PictureBusi {
	
	public List<Picture> getPicture(Recipe recipe){
		List<Picture> list = new ArrayList<Picture>();
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			list = sqlSession.selectList("mapper.PictureMapper.selectPictureByRecipeId",recipe);
		}finally {
			sqlSession.close();
		}
		
		return list;
	} 
	
	public int upload(Picture picture) {
		int r = 0;
		
		SqlSession sqlSession = MybatisUtils.getSession();
		try {
			r = sqlSession.insert("mapper.PictureMapper.insertPicture",picture);
			if(r == 1){
		        System.out.println("您成功插入了 "+r+" 张图片！");
		        sqlSession.commit();
		    }else{
		        System.out.println("执行插入图片操作失败！！！");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}

}

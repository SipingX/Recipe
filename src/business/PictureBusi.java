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
		        System.out.println("���ɹ������� "+r+" ��ͼƬ��");
		        sqlSession.commit();
		    }else{
		        System.out.println("ִ�в���ͼƬ����ʧ�ܣ�����");
		    }
		}finally {
			sqlSession.close();
		}
		
		return r;
	}

}

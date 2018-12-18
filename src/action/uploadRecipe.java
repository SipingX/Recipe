package action;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import business.IncludeBusi;
import business.RecipeBusi;
import business.StepBusi;
import entity.Include;
import entity.Recipe;
import entity.Step;

/**
 * Servlet implementation class uploadRecipe
 */
@WebServlet("/uploadRecipe")
public class uploadRecipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadRecipe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		int result = 0;

		//上传食谱单一数据
		Recipe recipe = new Recipe();
		RecipeBusi recbus = new RecipeBusi();
		
		recipe.setAuthor("17379506118");
		System.out.println(request.getParameter("recipe_name").trim());
		recipe.setName(request.getParameter("recipe_name").trim());
		recipe.setCategory(request.getParameter("category").trim());
		recipe.setComplexity(request.getParameter("summary").trim());
		recipe.setMinute(Integer.parseInt(request.getParameter("minute").trim()));
		recipe.setTasty(request.getParameter("tasty").trim());
		recipe.setMethod(request.getParameter("method").trim());
		recipe.setDescription(request.getParameter("description").trim());
		recipe.setAddress(request.getParameter("directions").trim());
		
		result = recbus.upload(recipe);
		if(result == 1) {
			System.out.println("食谱单一数据上传成功！");
		}else {
			String html = "食谱单一数据上传失败！<br><a href='submit-recipe.jsp'>重新上传</a>";
			response.getWriter().write(html);
		}
		
		recipe.setId(recbus.getMaxId());
		
		//上传步骤
		String[] steps = null;
		Step step = new Step();
		StepBusi stebus = new StepBusi();
		
		steps = request.getParameterValues("step");
		for(int i=0;i<steps.length;i++) {
			result = 0;
			step.setRecipe(recipe.getId());
			step.setSequence(i+1);
			step.setDescription(steps[i]);
			result = stebus.upload(step);
			if(result == 1) {
				System.out.println("步骤  "+i+"  上传成功！");
			}else {
				String html = "步骤上传失败！<br><a href='submit-recipe.jsp'>重新上传</a>";
				response.getWriter().write(html);
			}
		}
		
		//上传食材
		String[] includes = request.getParameterValues("ingredient_name");
		String[] Mquantities = request.getParameterValues("ingredient_note");
		Include include = new Include();
		IncludeBusi incbus = new IncludeBusi();
		
		for(int i=0;i<includes.length;i++) {
			result = 0;
			include.setRecipe(recipe.getId());
			include.setMaterial(Integer.parseInt(includes[i]));
			include.setQuantity(Mquantities[i]);
			result = incbus.upload(include);
			if(result == 1) {
				System.out.println("食材  "+include.getMaterial()+"  上传成功！");
			}else {
				String html = "食材上传失败！<br><a href='submit-recipe.jsp'>重新上传</a>";
				response.getWriter().write(html);
			}
		}
		
		//上传图片
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart) {//判断前台的form是否有multipart属性
//			FileItemFactory factory = new DiskFileItemFactory();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//通过parseRequest解析form中的所有请求字段，并保存到items集合中
			try {
				//设置上传文件时用到的临时文件的大小DiskFileItemFactory
				factory.setSizeThreshold(10485760);//设置临时文件缓冲区大小为10MB(单位为字节B)
				factory.setRepository(new File("D:\\Course\\Java\\workplace\\Recipe\\uploadtemp"));//设置临时文件的目录
				
				//控制上传单个文件的大小  此处为100MB
				upload.setSizeMax(104857600);
				
				List<FileItem> items = upload.parseRequest(request);
				//遍历items中的数据(item=sno, sname, file)
				Iterator<FileItem> iter = items.iterator();
				while(iter.hasNext()) {
					FileItem item = iter.next();
					String itemName = item.getFieldName();
					//判断前台字段，是普通form表单字段，还是文件字段
					
					//request.getParameter()   --iter.getString
					if(item.isFormField()) {//普通form表单字段上传
						System.out.println("跳过普通form表单字段上传");
					}else {
						if(itemName.equals("picture")) {
							//file 文件上传
							//getFieldName是获取普通表单字段name值
							//getName是获取文件名
							String fileName = item.getName();
							if(fileName.contains("\\")) {
		                        //如果包含则截取字符串
								fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
		                    }
							System.out.println("文件名："+fileName);
							//获取文件内容并上传
							//定义文件路径：指定上传的位置（服务器路径），这里放到workplace下本工程的一个文件夹
							String path = "D:\\Course\\Java\\workplace\\Recipe\\WebContent\\upload\\recipe\\picture";
							System.out.println("文件保存路径："+path);
							File file = new File(path,fileName);
							item.write(file);//上传
							System.out.println(fileName+"上传成功！");
						}
					}
				}
			} catch(FileUploadBase.SizeLimitExceededException e) {
				System.out.println("上传文件大小超过限制！最大100MB");
				String html = "图片上传失败！<br>上传文件大小超过限制！最大100MB!<br><a href='submit-recipe.jsp'>重新上传</a>";
				response.getWriter().write(html);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("前台的form无multipart属性！");
			System.out.println("图片上传失败！");
			String html = "图片上传失败！<br>前台的form无multipart属性！<br><a href='submit-recipe.jsp'>重新上传</a>";
			response.getWriter().write(html);
		}
		
		String html = "您的食谱上传成功！请耐心等待审核...<br><a href='submit-recipe.jsp'>返回上传页面</a>";
		response.getWriter().write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

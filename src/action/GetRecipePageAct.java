package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.IncludeBusi;
import business.PictureBusi;
import business.RecipeBusi;
import business.StepBusi;
import entity.Include;
import entity.Picture;
import entity.Recipe;
import entity.Step;

/**
 * Servlet implementation class getRecipePageAct
 */
@WebServlet("/getRecipePageAct")
public class GetRecipePageAct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRecipePageAct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RecipeBusi recb = new RecipeBusi();
		Recipe recipe = new Recipe();
		recipe.setId(Integer.parseInt(request.getParameter("recipeId")));
		System.out.println("ÇëÇóÊ³Æ×Id£º"+recipe.getId());
		try {
			recipe = recb.getRecipePageInfo(recipe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IncludeBusi incb = new IncludeBusi();
		Iterator<Include> include = incb.getInclude(recipe) ;
		
		StepBusi steb = new StepBusi();
		Iterator<Step> step = steb.getStep(recipe);
		
		PictureBusi picb = new PictureBusi();
		Iterator<Picture> picture = picb.getPicture(recipe);
		
		request.setAttribute("recipe", recipe);
		request.setAttribute("include", include);
		request.setAttribute("step", step);
		request.setAttribute("picture", picture);
		
		request.getRequestDispatcher("recipe-page.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

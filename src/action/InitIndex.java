package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.RecipeBusi;
import entity.Recipe;

/**
 * Servlet implementation class InitIndex
 */
@WebServlet("/InitIndex")
public class InitIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Recipe> list = new ArrayList<Recipe>();
		RecipeBusi recb = new RecipeBusi();
		for(int i=1;i<=5;i++) {
			Recipe recipe = new Recipe();
			recipe.setId(i);
			recipe = recb.getRecipePageInfo(recipe);
			recipe.setPictures();
			list.add(recipe);
		}
		
		Recipe latest_recipe = new Recipe();
		latest_recipe.setId(recb.getMaxId());
		latest_recipe = recb.getRecipePageInfo(latest_recipe);
		latest_recipe.setPictures();
		
		request.setAttribute("recipes_recommended", list);
		request.setAttribute("latest_recipe", latest_recipe);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

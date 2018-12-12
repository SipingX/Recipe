package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.manageUserBusi;
import entity.User;

/**
 * Servlet implementation class manageUserAct
 */
@WebServlet("/manageUserAct")
public class manageUserAct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageUserAct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int page=1;
		int pagesize = 2;
		int recordcount = 0;
		int pagecount = 0;
		manageUserBusi mub = new manageUserBusi();
		User userlist[] = null;
		
		//��ȡ�ͻ��˴��ݹ�����Ҫ��ʾ��ҳ��
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		try {
			recordcount = mub.getUserCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pagecount = recordcount/pagesize+(recordcount%pagesize==0?0:1);
		
		try {
			userlist = mub.getUserList(page, pagesize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ������ҳ������ǰҳ��user.jspҳ��
		request.setAttribute("page", page);
		request.setAttribute("userlist", userlist);
		request.setAttribute("pagecount",pagecount);

		request.getRequestDispatcher("user.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
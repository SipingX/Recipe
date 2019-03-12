package com.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.ValiMsgUtils;

@WebServlet("/sendMsg")
public class sendMsgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public sendMsgServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");*/
		doPost(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		String value = request.getParameter("value");
		if(value.matches("^1[34578]\\d{9}$")){
			System.out.println(value);
			request.getSession().setAttribute("valiNum", ValiMsgUtils.send(value));
			System.out.println("验证码："+request.getSession().getAttribute("valiNum"));
			
			System.out.println("鐭俊鍙戦�佹垚鍔燂紒");
		}else {
			System.out.println("鐭俊鍙戦�佸け璐ワ紒");
		}
	
	}

}

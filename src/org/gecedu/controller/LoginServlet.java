package org.gecedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gecedu.model.User;
import org.gecedu.service.UserService;
import org.gecedu.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		UserService userService = new UserServiceImpl();
		User user = userService.queryByAccountAndPassword(phone,password);
		
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return ;
		}
		
		/**
		 * ServletContext 默认下 里面放的数据一次会话里面有效
		 * request 里面放的数据只在一次请求中有效
		 */
		request.getServletContext().setAttribute("user", user);
		//重定向到首页
		response.sendRedirect(request.getContextPath()+"/index");
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

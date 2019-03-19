package org.gecedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.gecedu.model.User;
import org.gecedu.pojo.PageResult;
import org.gecedu.service.UserService;
import org.gecedu.service.impl.UserServiceImpl;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//适用于post请求里面参数中文乱码
		request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
		String method = request.getParameter("method");
		if (method == null && method == "") {
			request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
			return;
		}

		switch (method) {
		case "list":
			list(request, response);
			break;
		case "editUI":
			editUI(request, response);
			break;
		case "edit":
			edit(request, response);
			break;
		case "queryById":
			queryById(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		default:
			break;
		}
	}

	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		UserService userService = new UserServiceImpl();
		userService.delete(Integer.parseInt(id));
		//回到列表页面  因为我们使用iframe框架，所以可以直接调用方法。
		list(request, response);
		//response.sendRedirect(request.getContextPath() + "/user?method=list");
	}

	/**
	 * 根据用户Id查询
	 * 
	 * @param request
	 * @param response
	 */
	private void queryById(HttpServletRequest request, HttpServletResponse response) {

	}

	/**
	 * 编辑
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		// 一般用于get请求url上参数
		/*String province = new String(request.getParameter("province").getBytes("ISO-8859-1"),"UTF-8");
		String city = new String(request.getParameter("city").getBytes("ISO-8859-1"),"UTF-8");*/
		UserService userService = new UserServiceImpl();
		User user = new User(Integer.parseInt(id), name, phone, password, Integer.parseInt(gender), province, city);
		
		if(id!=null&&id.trim().length()>0) {
			userService.update(user);
		}else {
			userService.add(user);
		}
		list(request, response);
	}

	/**
	 * 跳转编辑页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void editUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if(idStr!=null&&idStr.trim().length()>0) {
			UserService userService = new UserServiceImpl();
			User user = userService.queryById(Integer.parseInt(idStr));
			request.setAttribute("user", user);
		}
		request.getRequestDispatcher("/WEB-INF/pages/user/edit.jsp").forward(request, response);
	}

	/**
	 * 用户列表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String pageSize = request.getParameter("pageSize");
		String userName = request.getParameter("userName");
		Integer pageSizeInt = 5;
		Integer currentPageInt = 1;
		if(pageSize!=null&&pageSize.length()>0) {
			pageSizeInt = Integer.parseInt(pageSize);
		}
		if(currentPage!=null&&currentPage.length()>0) {
			currentPageInt = Integer.parseInt(currentPage);
		}
		
		UserService userService = new UserServiceImpl();
		PageResult pageResult = userService.queryByPages(currentPageInt,pageSizeInt, userName);
		request.setAttribute("pageResult", pageResult);
		request.getRequestDispatcher("/WEB-INF/pages/user/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

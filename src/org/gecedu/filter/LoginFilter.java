package org.gecedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginFilter implements Filter {

    public LoginFilter() {
    }
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		if(httpServletRequest.getRequestURI().contains("login")||httpServletRequest.getRequestURL().toString().contains("assets")) {
			chain.doFilter(request, response);
		}else {
			Object obj = request.getServletContext().getAttribute("user");
			if(obj==null) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login.jsp");
			}else {
				//放行
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

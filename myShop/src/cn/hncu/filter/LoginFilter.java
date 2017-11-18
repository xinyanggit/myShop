package cn.hncu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * 1. 获取session中的user
		 * 2. 判断是否为null
		 *   > 如果为null：保存错误信息，转发到msg.jsp
		 *   > 如果不为null：放行
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println("filter========Login--filter11");
		Object user = req.getSession().getAttribute("sessionUser");
		if(user == null) {
			System.out.println("filter========Login--filter12");
			req.setAttribute("code", "error");//为了显示X图片
			req.setAttribute("msg", "您还没有登录，不能访问本资源");
			req.getRequestDispatcher("/jsps/msg.jsp").forward(req, response);
		} else {
			System.out.println("filter========Login--放走");
			chain.doFilter(request, response);//放行
		}
	}

	@Override
	public void destroy() {

	}

}

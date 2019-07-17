package com.hlc.mylogin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projo.User;

/**
 * Servlet implementation class mainServlet
 */
public class mainServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
		User u = (User) req.getSession().getAttribute("user");//可能会报空指针异常
		//处理请求信息
		//相应处理结果
		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<h3>欢迎"+u.getUname()+"来到hlc空间</h3>");
		resp.getWriter().write("<hr>");
		resp.getWriter().write("<form action='show' method='get'>");
		resp.getWriter().write("<input type='submit' value='查看个人信息'>");
		resp.getWriter().write("</form>");
		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}
}

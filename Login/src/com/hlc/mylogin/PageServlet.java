package com.hlc.mylogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageServlet
 */
public class PageServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		// 处理请求
		// 响应处理结果
		// 获取request作用域数据
		String str = (String) req.getAttribute("str") == null ? "" : (String) req.getAttribute("str");
		// System.out.println(str);
		resp.getWriter().write("<html>");
		resp.getWriter().write("<head>");
		resp.getWriter().write("</head>");
		resp.getWriter().write("<body>");
		resp.getWriter().write("<font color='red' size='2px'>" + str + "</font>");
		resp.getWriter().write("<form action='Login' method='get'>");
		resp.getWriter().write("User:<input type='text' name='uname' value=''/></br>");
		resp.getWriter().write("Password:<input type='password' name='pwd' value=''/></br>");
		resp.getWriter().write("<input type='submit'  value='submit'/></br>");
		resp.getWriter().write("</form>");
		resp.getWriter().write("</body>");
		resp.getWriter().write("</html>");
	}
}

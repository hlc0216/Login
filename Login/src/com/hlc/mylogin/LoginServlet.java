package com.hlc.mylogin;
/**
 * servlet流程总结
 * 浏览器发送请求到服务器（请求）
 * 服务器接收浏览器的请求，进行解析，创建request对象存储请求数据
 * 服务器调用对应的servlet进行请求处理，并将request对象作为实参传递给servlet的方法
 * servlet的方法执行进行请求处理（这是我们程序员做的，上面是服务器自动帮我们做的）
 *     //设置请求编码格式
 *     //设置响应编码格式
 *     //获取请求信息
 *     //处理请求信息
 *         //创建业务层对象
 *         //调用业务层对象的方法
 *     //响应处理结果
 *     数据流转流程
 *     浏览器--》服务器--》数据库
 *     数据库--》服务器--》浏览器
 *     
 * 请求转发学习：
 *		作用:实现多个servlet联动操作处理请求，这样避免代码冗余，让servlet的职责更加明确。
 *		使用：
 *				req.getRequestDispatcher("要转发的地址").forward(req, resp);
 *				地址：相对路径，直接书写servlet的别名即可。
 *		特点:
 *			一次请求，浏览器地址栏信息不改变。
 *		注意：
 *			请求转发后直接return结束即可。
 *reuqet作用域：
 *		解决了一次请求内的servlet的数据共享问题
 *重定向：
 *		解决了表单重复提交的问题，以及当前servlet无法处理的请求的问题。
 *		使用:
 *			resp.sendRedirect(String uri);
 *		示例:
 *			resp.sendRedirect("/login/main");
 *		特点：
 *			两次请求，两个request对象。
 *			浏览器地址栏信息改变
 *		时机：
 *			如果请求中有表单数据，而数据又比较重要，不能重复提交，建议使用重定向。
 *			如果请求被Servlet接收后，无法进行处理，建议使用重定向定位到可以处理的资源。	
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlc.Service.loginService;
import com.hlc.Service.impl.loginServiceImpl;

import projo.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      	//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		//获取请求信息
		String uname = req.getParameter("uname");
		//uname=new String(uname.getBytes("utf-8"));
		String pwd = req.getParameter("pwd");
		System.out.println(uname+":"+pwd); 
		//处理请求信息
		   //获取业务层对象
		loginService ls = new loginServiceImpl();
		User u =ls.checkLoginService(uname, pwd);
		System.out.println(u);
		//响应处理结果
		if(u!=null){
			//resp.getWriter().write("登陆成功");
			//创建cookie信息实现一小时免登陆
			Cookie c=new Cookie("uid", u.getUid()+"");//安全起见，存uid
			//设置Cookie的有效期
			c.setMaxAge(3*60*60);
			c.setPath("cookie");
			//添加cookie信息
			resp.addCookie(c);
			//请求转发
			//req.getRequestDispatcher("main").forward(req, resp);
			//重定向
			//重定向后是另一次新的request，所以没有上次请求的数据，所以页面显示null
			resp.sendRedirect("/Login/main");//或者直接用resp.sendRedirect("main");
			return;
		}else{
			
//			resp.getWriter().write("<html>");
//			resp.getWriter().write("<head>");
//			resp.getWriter().write("</head>");
//			resp.getWriter().write("<body>");
//			resp.getWriter().write("<form action='Login' method='get'>");
//			resp.getWriter().write("User:<input type='text' name='uname' value=''/></br>");
//			resp.getWriter().write("Password:<input type='password' name='pwd' value=''/></br>");
//			resp.getWriter().write("<input type='submit'  value='submit'/></br>");
//			resp.getWriter().write("</form>");
//			resp.getWriter().write("</body>");
//			resp.getWriter().write("</html>");
			//上面那种太冗余,使用下面的请求转发即可
			//使用request对象实现不同servlet的数据流转
			req.setAttribute("str", "用户名或密码错误");
			
			/**
			 * 请求转发
				作用：实现多个servlet联动操作处理请求，这样避免代码冗余，让servlet的职责更加明确。
				使用：req.getRequestDispatcher("Page").forward(req, resp);
				特点：一次请求，浏览器地址栏不改变
				注意：请求转发后直接return结束即可
			 */
			req.getRequestDispatcher("Page").forward(req, resp);
			return ;
		}
			
	}

}

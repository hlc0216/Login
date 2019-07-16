package com.hlc.mylogin;

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
 * cookie信息校验
 *   判断请求中是否携带正确的Cookie信息
 *   如果有则校验Cookie信息是否正确
 *   	正确则直接响应主页面给用户
 *   	不正确则响应登陆页面给用户
 *   没有则请求转发给登陆页面
 */
public class cookieservlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置响应请求编码格式
		//获取请求信息
			//获取cookie信息
		Cookie[] cks=req.getCookies();
		//处理请求信息
		if(cks!=null){
			//遍历cookie信息
			String uid="";
			for(Cookie c:cks){
				if("uid".equals(c.getName())){
					uid=c.getValue();
				}
			}
			if("".equals(uid)){
				//请求转发
				req.getRequestDispatcher("Page").forward(req, resp);
				return ;
			}else{
				//校验Uid用户信息
				  //获取业务层对象
				loginService ls =new loginServiceImpl();
				User u =ls.checkUidService(uid);
				if(u!=null){
					//重定向
					resp.sendRedirect("main");
					return ;
				}else{
					req.getRequestDispatcher("Page").forward(req, resp);;
				}
			}
		}else{
	    //响应处理结果 
			//请求转发
			req.getRequestDispatcher("Page").forward(req, resp);
			return ;
			
		}
		
		
	}

}

package com.hlc.Service.impl;



import com.hlc.Service.loginService;
import com.hlc.dao.loginDao;
import com.hlc.dao.impl.loginDaoImpl;

import projo.User;

public class loginServiceImpl implements loginService{
	//创建Dao层过渡
	loginDao ld = new loginDaoImpl();
	//校验用户登陆信息
	@Override
	public User checkLoginService(String uname, String pwd) {
		// TODO Auto-generated method stub
		return ld.checkLoginDao(uname, pwd);
	}
	//校验cookie信息
	public User checkUidService(String uid) {
		// TODO Auto-generated method stub
		
		return ld.checkUidDao(uid);
	}
	
}

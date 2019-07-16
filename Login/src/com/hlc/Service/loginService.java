package com.hlc.Service;

import projo.User;

public interface loginService {
	//校验用户登陆信息
	User checkLoginService(String uname,String pwd);
	//校验用户cookie信息
	User checkUidService(String uid);
}

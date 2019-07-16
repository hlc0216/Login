package com.hlc.dao;

import projo.User;

public interface loginDao {
	User checkLoginDao(String uname, String pwd);

	User checkUidDao(String uid);
}

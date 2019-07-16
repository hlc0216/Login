package com.hlc.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hlc.dao.loginDao;

import projo.User;

public class loginDaoImpl implements loginDao {
	@Override
	public User checkLoginDao(String uname, String pwd) {
		// jdba操作
		// 声明jdbc对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_in", "root", "123456");
			// 创建sql命令
			String sql = "select * from t_user where uname=? and pwd=?";
			// 创建sql命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			
			ps.setString(1, uname);
			ps.setString(2, pwd);
			// 执行
			rs = ps.executeQuery();
			// 遍历执行结果
			while (rs.next()) {
				u = new User();
				//System.out.println("mysql打印的数据："+rs.getInt("uid")+"-"+rs.getString("uname")+"-"+rs.getString("uname"));
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				rs.close();
				ps.close();
				conn.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 返回
		return u;
	}

	@Override
	public User checkUidDao(String uid) {
		// jdba操作
				// 声明jdbc对象
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				User u = null;
				try {
					// 加载驱动
					Class.forName("com.mysql.jdbc.Driver");
					// 获取连接对象
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_in", "root", "123456");
					// 创建sql命令
					String sql = "select * from t_user where uid=? ";
					// 创建sql命令对象
					ps = conn.prepareStatement(sql);
					// 给占位符赋值
					
					ps.setString(1, uid);
					// 执行
					rs = ps.executeQuery();
					// 遍历执行结果
					while (rs.next()) {
						u = new User();
						//System.out.println("mysql打印的数据："+rs.getInt("uid")+"-"+rs.getString("uname")+"-"+rs.getString("uname"));
						u.setUid(rs.getInt("uid"));
						u.setUname(rs.getString("uname"));
						u.setPwd(rs.getString("pwd"));
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					// 关闭资源
					try {
						rs.close();
						ps.close();
						conn.close();
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// 返回
				return u;
	
	}

}

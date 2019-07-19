package com.hlc.mylogin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class numServlet extends HttpServlet {
	public void destory() {
		System.out.println("调用了destory方法");
		// 获取网页计数器
		int nums = (int) this.getServletContext().getAttribute("nums");
		// 获取文件路径
		String path = this.getServletContext().getRealPath("/nums/nums.txt");
		// 声明流对象
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(nums+"");
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void init() throws ServletException {
		// 获取文件中的计数器数据
		//获取文件路径
		String path = this.getServletContext().getRealPath("/nums/nums.txt");
		//声明流对象
		FileReader fr=null;
		BufferedReader br=null;
		
		try {
			fr=new FileReader(path);
			br=new BufferedReader(fr);
			String nums=br.readLine();
			System.out.println("读物num.txt的阅读数为："+nums);
			this.getServletContext().setAttribute("nums", nums);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

}

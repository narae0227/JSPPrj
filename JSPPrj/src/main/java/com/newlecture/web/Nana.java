package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hi")
public class Nana extends HttpServlet{
	// 입력한 숫자만큼 print
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 웹서버에서 클라이언트(브라우저)에게 보낼 인코딩 방식 셋팅
		resp.setCharacterEncoding("UTF-8");
		// 클라이언트(브라우저)에게 웹서버가 어떤 인코딩 방식을 썼으니, 이걸로 해석해라 알려주는 셋팅
		// network - response header에서 확인 가능
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		String cnt_ = req.getParameter("cnt");
		System.out.println(cnt_);
		
		int cnt = 100;
				
		if(cnt_ != null && !cnt_.equals("")) {
			System.out.println("@@@@@@@여기옴?");
			cnt = Integer.parseInt(cnt_);
		}
		
		for(int i=0; i<cnt; i++)
			out.println((i+1)+": 안녕 servlet!<br >");
	}
}
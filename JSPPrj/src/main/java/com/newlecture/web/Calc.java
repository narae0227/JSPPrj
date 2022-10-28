package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calc")
public class Calc extends HttpServlet{
	// 더하기 or 빼기 버튼 눌렀을때 연산하기
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 서버 -> 클라이언트에 보여주는 화면에 알려주는 정보들
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		
		PrintWriter out = resp.getWriter();
		
		String x_ = req.getParameter("x");
		String y_ = req.getParameter("y");
		String op = req.getParameter("btn");
		
		
		int x= 0;
		int y= 0;
		
		if(x_ != null && !x_.equals("")) x = Integer.parseInt(x_);
		if(y_ != null && !y_.equals("")) y = Integer.parseInt(y_);

		int result = 0;
		
		if(op.equals("덧셈"))
			result = x + y;
		
		if(op.equals("뺄셈"))
			result = x - y;
		
		out.println("결과값 : "+ result);
		
	}
}
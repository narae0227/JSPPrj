package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add2")
public class Add2 extends HttpServlet{
	// 여러개의 동일한 name값을 배열로 받아서 더하기
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		PrintWriter out = resp.getWriter();
		
		// 동일한 name이 여러개 올 경우 getParameterValues 를 사용해야 한다.
		String[] num_ = req.getParameterValues("num");
		
		System.out.println(num_);
		
		int result = 0;

		for(int i=0; i<num_.length; i++) {
			// for문 안에 있다고 하더라도 여러번 반복되어 선언되는것이 아니다.
			// 한번만 선언이 되고, 연산만 반복이 된다.
			int num = Integer.parseInt(num_[i]);
			result+=num;
		}
		
		out.println("결과값 : "+ result);
		
	}
}
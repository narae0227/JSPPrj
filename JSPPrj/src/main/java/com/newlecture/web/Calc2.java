package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;


@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	// 한번만 연산이 가능한 계산기
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// ServletContext 생성 - value값을 담아놓을 저장소 application 생성
		//ServletContext application = req.getServletContext();
		
		// Session 객체 생성
		HttpSession session = req.getSession();
		
		// 서버 -> 클라이언트에 보여주는 화면에 알려주는 정보들
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		String v_ = req.getParameter("v");
		String op = req.getParameter("btn");
		
		
		int v= 0;
		if(v_ != null && !v_.equals("")) 
			v = Integer.parseInt(v_);

		int result = 0;
		
		// 값을 계산
		if(op.equals("=")) {
			
//			int x = (Integer)application.getAttribute("value"); // servlet Context에 저장된 값
			int x = (Integer)session.getAttribute("value"); // session에 저장되어 있던 값

			int y = v; // 사용자가 지금 전달한값
			
			// Servlet Context는 object 타입이기 때문에 mapper class로 타입을 지정해줘야 오류가 뜨지 않는다.
//			String operator = (String)application.getAttribute("op"); 
	
			String operator = (String)session.getAttribute("op"); 
			
			if(operator.equals("+"))
				result = x + y;
			else
				result = x - y;
			
			// '='을 눌렀을때 가는 화면
			resp.getWriter().printf("결과값 : %d\n", result);
		} 
		// 값을 저장
		else { 	
//		application.setAttribute("value", v); // 사용자 입력값 Servlet Context에 저장
//		application.setAttribute("op", op); // 사용자가 누른 버튼값( + or - ) 저장
		
		session.setAttribute("value", v); // 사용자 입력값 session에 저장
		session.setAttribute("op", op); // 사용자가 누른 버튼값( + or - ) 저장
		
		
		// response에 페이지 정보를 담아서 요청이 오면 마지막에 이 페이지로 보내주도록 하는것. 리디렉션
		resp.sendRedirect("calc2.html");
		}
		
	}
}
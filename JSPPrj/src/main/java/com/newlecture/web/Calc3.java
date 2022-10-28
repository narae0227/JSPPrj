package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	// 한번만 연산이 가능한 계산기
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 쿠키 객체 생성
		Cookie[] cookies = req.getCookies();

		String val = req.getParameter("val"); // 숫자값
		String operator = req.getParameter("operator"); // 연산자값
		String dot = req.getParameter("dot"); // '.' 소수점
		
		String exp = "";
		
		// 쿠키가 존재한다면
		if(cookies != null)	
			for(Cookie c : cookies) 
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
		// 연산자가 있고, = 을 눌렀을때
		if(operator != null && operator.equals("=")) {
			// 자바에서 자바스크립트를 사용하고자 할떄 쓰는 함수 ScriptEngine
			// jdk 11 버전이상에서는 안됨. 13에서는 됨.
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(engine.eval(exp)); // 그 연산식을 그대로 실행할수 있는 자바스크립트 nashorn
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else if(operator != null && operator.equals("C")) { // 클린키를 눌렀다면
			// 쿠키 삭제
			exp = ""; // 공백으로 업데이트
			
		}
		else { // 그외의 경우(연산중)
			
			// 한번에 한개만 누르기 때문에 어차피 셋중 1개만 누적된다
			exp += (val == null)? "" : val; // 넘어온 "val"값이 null이라면 공백, 아니면 기존에 + 
			exp += (operator == null)? "" : operator;
			exp += (dot == null)? "" : dot;

		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		
		
		if(operator != null && operator.equals("C")) { // 클린키를 눌렀다면
			expCookie.setMaxAge(0); // 쿠키 유지시간 0 , 즉 삭제
		}
		resp.addCookie(expCookie);

		// response에 페이지 정보를 담아서 요청이 오면 마지막에 이 페이지로 보내주도록 하는것. 리디렉션
		// 동일 경로로 갈때는 '/' 를 생략해도 된다. /calcpage와 동일하다.
		resp.sendRedirect("calcpage");
		
	}
		
}

package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("doGet 메소드가 호출되었습니다.");
		
		// calcpage 의 것을 가져옴
		Cookie[] cookies = req.getCookies();
		
		String exp = "0";
		if(cookies != null)
			for(Cookie c : cookies)
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
		
		
		// 서버 -> 클라이언트에 보여주는 화면에 알려주는 정보들
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = resp.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("	width:50px;");
		out.write("	height:50px;");
		out.write("}");
		out.write("                                 ");
		out.write(".output{");
		out.write("	height:50px;");
		out.write("	background: #e9e9e9;");
		out.write("	font-size : 24px;");
		out.write("	font-weight : bold;");
		out.write("	text-align : right;");
		out.write("	padding : 0px 5px;");
		out.write("}");

		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<div>");
		out.write("		<form method=\"post\">");
		out.write("			<table>");
		out.write("				<tr>");
		// 문자열이므로 %s로 바꿔준다.
		out.printf("		<td class=\"output\" colspan=\"4\">%s</td>", exp);
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" value=\"CE\" name=\"operator\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"C\" name=\"operator\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"BS\" name=\"operator\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"/\" name=\"operator\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" value=\"7\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"8\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"9\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"*\" name=\"operator\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" value=\"4\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"5\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"6\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"-\" name=\"operator\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("					<td><input type=\"submit\" value=\"1\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"2\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"3\" name=\"val\"/></td>");
		out.write("						<td><input type=\"submit\" value=\"+\" name=\"operator\"/></td>");
		out.write("				</tr>");
		out.write("				<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" value=\"0\" name=\"val\"/></td>");
		out.write("					<td><input type=\"submit\" value=\".\" name=\"dot\"/></td>");
		out.write("					<td><input type=\"submit\" value=\"=\" name=\"operator\"/></td>");
		out.write("				</tr>");
		out.write("			</table>");
					
		out.write("		</form>");
		out.write("	</div>");
		out.write("</body>");
		out.write("</html>");
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// calc3 가져옴
		System.out.println("doPost 메소드가 호출되었습니다.");
		
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
				
				expCookie.setPath("/calculator"); // 한개의 url만 설정이 가능하다.
				resp.addCookie(expCookie);
				
				// response에 페이지 정보를 담아서 요청이 오면 마지막에 이 페이지로 보내주도록 하는것. 리디렉션
				// 동일 경로로 갈때는 '/' 를 생략해도 된다. /calcpage와 동일하다.
				resp.sendRedirect("calculator"); // 현재 상태로는 doGet요청
		
		
	}
	
}

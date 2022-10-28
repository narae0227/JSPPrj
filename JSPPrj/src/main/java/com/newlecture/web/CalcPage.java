package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/calcpage")
public class CalcPage extends HttpServlet{
	// 한번만 연산이 가능한 계산기
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
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
		out.write("		<form action=\"calc3\" method=\"post\">");
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
}
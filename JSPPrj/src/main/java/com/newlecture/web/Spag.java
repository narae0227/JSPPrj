package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("n");
		
		if(num_ != null && !num_.equals("")) {
			num = Integer.parseInt(num_);
		}
		
		// String 담기
		String result;
		
		if(num%2 != 0)
			result = "홀수";
		else
			result = "짝수";
		
		request.setAttribute("result", result);
		
		// List 담기
		String[] names = {"미리","미리2"};
		
		request.setAttribute("names", names);
		
		// Map 담기
		Map<String, Object> notice = new HashMap<>();
		
		notice.put("id", 1);
		notice.put("title", "EL 좋아요");
		
		request.setAttribute("notice", notice);
		
		// redirect : 현재 작업하는 것과 상관없이 새로운 요청
		// forward : 현재 작업하는 내용을 이어갈 수 있도록 공유
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		
		// 이 페이지에서 작업한 내용이 forward를 통해 spage.jsp로 갈때 request, responce의 정보를 가지고 가는 것이다.
		// 이떄 상태를 담아서 갈 수 있는 저장소가 request 이다.
		dispatcher.forward(request, responce);
		
	}// doget end
}

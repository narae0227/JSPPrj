package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		NoticeService service = new NoticeService();
		Notice notice =  service.getNotice(id);
		request.setAttribute("n", notice);
		
		// redirect - 정보를 가지고 갈 필요가 x 일때 사용하는 것
		
		// forward - 작업한 정보를 이어받아서 jsp에서 쓸떄 사용하는 것
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
		.forward(request, responce); // 주소의 기준은 webapp <- / 
		// webapp/notice/detail.jsp 파일로  request, responce를 들고 가겠다는 뜻.
		
	}// doget end
}// class end
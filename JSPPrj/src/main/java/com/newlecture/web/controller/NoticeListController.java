package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
		
		// 검색여부에 따라 값이 있을수도, 없을수도 있기 때문에 임시 변수가 필요하다.
		
		
		
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");;
		String page_ = request.getParameter("p");
		
		String field = "title";
		if(field_ != null && !field_.equals(""))
				field = field_;
		
		String query = "";
		if(query_ != null && !query_.equals(""))
				query = query_;
		
		int page = 1;
		if(page_ != null && !page_.equals(""))
			page = Integer.parseInt(page_);

		NoticeService service = new NoticeService();
		
		List<Notice> list = service.getNoticeList(field, query, page);
		int count = service.getNoticeCount(field, query);

		System.out.println(list);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, responce); // 주소의 기준은 webapp <- / 
		
	}// doget end
	
	
	
	
	}// class end
 
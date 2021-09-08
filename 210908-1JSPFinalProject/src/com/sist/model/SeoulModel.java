package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class SeoulModel {
	@RequestMapping("seoul/seoul_main.do")
	public String seoul_main(HttpServletRequest request, HttpServletResponse response) {
		//include 파일 전송
		request.setAttribute("main_jsp", "../seoul/seoul_main.jsp");
		
		return "../main/main.jsp";
	}
}

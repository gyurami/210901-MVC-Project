package com.sist.controller;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//▼ 파일읽기 → 메뉴만들기(Model클래스 저장): 실행중에 한 번만 호출, Model클래스 메모리 할당 
	public void init(ServletConfig config) throws ServletException {

	
	}
	//▼ service(): 사용자 요청 시 자동호출, 요청처리 → Model찾기 → JSP로 request 전송
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}

}

package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
import java.util.*;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map clsMap=new HashMap();

	//▼ 파일읽기 → 메뉴만들기(Model클래스 저장): 실행중에 한 번만 호출, Model클래스 메모리 할당 
	public void init(ServletConfig config) throws ServletException {
		//1. 메뉴판: clsMap()
		clsMap.put("main.do", new MainModel());
		clsMap.put("ko.do", new KoModel());
		clsMap.put("ch.do", new ChModel());
		clsMap.put("ja.do", new JaModel());
	}
	
	//▼ service(): 사용자 요청 시 자동호출, 요청처리 → Model찾기 → JSP로 request 전송
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자가 주문 내용을 받음(ko.do(한식), ja.do(일식), ch.do(중식))
		String cmd=request.getRequestURI();
		//2. URI전체에서 처리되는 문장만 남김: http://localhost:8080/JSPMVCLastProject/ko.do → ko.do
		cmd=cmd.substring(request.getContextPath().length()+1);
		//3. 요청처리를 위해 Model을 호출(execute() 호출)
		Model model=(Model)clsMap.get(cmd);
		//4. JSP로 값 전송
		String jsp=model.execute(request, response);
		//5. 결과값 넘겨주기
		RequestDispatcher rd=request.getRequestDispatcher("main/"+jsp);
		rd.forward(request, response);
	}

}

package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import java.util.*;
import com.sist.vo.*;
@Controller
public class FreeboardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
		//▼ 사용자가 보내주는 요청값 받아오기
		String page=request.getParameter("page");
		if(page==null) {
			page="1";
		}
		//▼ 해당 페이지의 데이터 가져오기
		FreeboardDAO dao=FreeboardDAO.newInstance();
		int curpage=Integer.parseInt(page);
		List<FreeboardVO> list=dao.freeboardListData(curpage);
		int totalpage=dao.freeboardTotalPage();
		
		//▼ list.jsp로 전송
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("list", list);
		
		//▼ 화면 출력(include 대상)
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {
		//▼ 입력창만 보여주기
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		//▼ 한글이 넘어오므로 한글변환(디코딩)
		try {
			request.setCharacterEncoding("UTF-8");
		}catch (Exception e) {}
		//▼ 데이터를 모아 dao에 전송
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		FreeboardVO vo=new FreeboardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//▼ DAO로 전송
		FreeboardDAO dao=FreeboardDAO.newInstance();
		//▼ 추가하는 메소드 호출
		dao.freeboardInsertData(vo);
		return "redirect:../freeboard/list.do"; //처리 후 목록(list.do)를 보여준다
	}
	
	// 게시판 상세보기
	@RequestMapping("freeboard/detail.do")
	public String freeboardDetail(HttpServletRequest request, HttpServletResponse response) {
		//▼ 사용자에게 게시글번호 값 받아오기
		String no=request.getParameter("no");
		//▼ DAO로 전송
		FreeboardDAO dao=FreeboardDAO.newInstance();
		//▼ 메소드 호출, 댓글 받기
		FreeboardVO vo=dao.freeboardDetailData(Integer.parseInt(no));
		List<ReplyVO> list=dao.replyListData(Integer.parseInt(no), 1);
		//▼ detail.jsp로 값 전송
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		//▼ 화면 출력
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request, HttpServletResponse response) {
		// 기존 데이터 읽기
		String no=request.getParameter("no");
		// dao 연결해서 vo값 얻어오기
		FreeboardDAO dao=FreeboardDAO.newInstance();
		FreeboardVO vo=dao.freeboardUpdateData(Integer.parseInt(no));
		// update.jsp로 데이터 전송
		request.setAttribute("vo", vo);
		// 화면 출력
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboard_update_ok(HttpServletRequest request, HttpServletResponse response) {
		//▼ 한글이 넘어오므로 한글변환(디코딩)
		try {
			request.setCharacterEncoding("UTF-8");
		}catch (Exception e) {}
		//▼ 데이터를 모아 dao에 전송
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no");
				
		FreeboardVO vo=new FreeboardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		//▼ DAO로 전송
		FreeboardDAO dao=FreeboardDAO.newInstance();
			
		//▼ 메소드 호출
		boolean bCheck=dao.freeboardUpdate(vo);
		request.setAttribute("bCheck", bCheck);
		request.setAttribute("no", no);
		
		//▼ 화면 출력
		return "redirect:../freeboard/update_ok.jsp"; //실행된 결과를 detail에서 다시 보여줌
	}
	
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request, HttpServletResponse response) {
		//▼ 게시글 번호값 받아오기
		String no=request.getParameter("no");
		//▼ no값 delete.jsp에 보내기
		request.setAttribute("no", no);
		//▼ 화면 출력
		request.setAttribute("main_jsp", "../freeboard/delete.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/delete_ok.do")
	public String freeboard_delete_ok(HttpServletRequest request, HttpServletResponse response) {
		//▼ 값 받기
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no");
		
		//▼ DAO 연결해서 메소드 호출하여 pwd, no 삭제 요청
		FreeboardDAO dao=FreeboardDAO.newInstance();
		boolean bCheck=dao.freeboardDeleteData(Integer.parseInt(no), pwd);
		
		//▼ 결과값 전송
		request.setAttribute("bCheck", bCheck);		
		
		// redirect: 이미 만들어져 있는 화면으로 갈 때, forward: 새로운 화면으로 갈 때, ajax: 해당 jsp만 실행
		return "../freeboard/delete_ok.jsp";
	}

	
}

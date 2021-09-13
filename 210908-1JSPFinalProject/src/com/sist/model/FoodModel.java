package com.sist.model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Controller
public class FoodModel {
   @RequestMapping("food/food_main.do")
   public String food_main(HttpServletRequest request,HttpServletResponse response)
   {
	   return "redirect:../main/main.do";
   }
   // jsp(link,button-click) => Model(데이터받기) => DAO (데이터 수집) 
   // => Model(DAO에 출력된 결과값 읽기) => request로 묶어서 jsp로 전송
   @RequestMapping("food/food_list.do")
   public String food_list(HttpServletRequest request,HttpServletResponse response)
   {
	   // 카테고리 번호 받기
	   String cno=request.getParameter("cno");
	   // FoodDAO를 통해서 해당 카테고리의 맛집을 가지고 온다 
	   FoodDAO dao=FoodDAO.newInstance();
	   CategoryVO vo=dao.foodCategoryInfoData(Integer.parseInt(cno));
	   List<FoodVO> list=dao.foodCategoryListData(Integer.parseInt(cno));
	   // food_list.jsp로 데이터를 전송 => 출력 => main.jsp에 출력된 내용을 읽어 간다 
	   request.setAttribute("vo", vo);
	   request.setAttribute("list", list);
	   request.setAttribute("main_jsp", "../food/food_list.jsp");
	   return "../main/main.jsp";
   }
   
   @RequestMapping("food/food_detail_before.do")
   public String food_detail_before(HttpServletRequest request,HttpServletResponse response)
   {
	   // 쿠키 생성 => 쿠기 전송 => 상세보기로 넘어간다 (response는 한개의 jsp 한번만 전송이 가능)
	   // 전송 (1.쿠키 , 2.HTML) => 두개를 동시에 전송이 불가능하다 
	   String no=request.getParameter("no");
	   HttpSession session=request.getSession();
	   String id=(String)session.getAttribute("id");
	   Cookie cookie=new Cookie(id+"f"+no, no);
	   // 키,값 = Map = JSON , request,response ,session
	   cookie.setMaxAge(60*60*24); // 초단위 계산 (24시간)
	   cookie.setPath("/");
	   // 쿠키 전송 
	   response.addCookie(cookie);
	   /*
	    *  영화 / 레시피 (id+"r")/ 여행(id+"t") ==> 구분 쿠키 
	    *  id+"m" 
	    *  
	    *  => shim => 
	    *  => hong => 
	    */
	   // 클라이언트로 전송 
	   
	   return "redirect:../food/food_detail.do?no="+no;//RedirectAttribute()
   }
   
   @RequestMapping("food/food_detail.do")
   public String food_detail(HttpServletRequest request,HttpServletResponse response)
   {
	   // 요청: 번호에 해당하는 맛집을 보여달라! 
	   String no=request.getParameter("no");
	   
	   // DAO
	   FoodDAO dao=FoodDAO.newInstance();
	   FoodVO vo=dao.foodDetailData(Integer.parseInt(no));
	   String address=vo.getAddress(); //서울 용산구 회나무로 83 지번 서울 용산구 이태원동 2-5
	   String addr1=address.substring(0, address.lastIndexOf("지")); //서울 용산구 회나무로 83지번 서울 용산구 이태원동 2-5 
	   String addr2=address.substring(address.lastIndexOf("지")); //서울 용산구 회나무로 83지번 서울 용산구 이태원동 2-5
	   String temp=address.substring(address.indexOf(" ")+1); //용산구 회나무로 83지번 서울 용산구 이태원동 2-5
	   temp=temp.substring(0, address.indexOf(" ")); //용산구
	
	   // 결과값 보내기
	   vo.setAddr1(addr1);
	   vo.setAddr2(addr2);
	   request.setAttribute("vo", vo);
	   
	   //1. 여행정보(명소, 호텔, 자연)
	   List<SeoulLocationVO> aList=dao.foodSeoulLocationData(temp);
	   request.setAttribute("aList", aList);
	   List<SeoulHotelVO> bList=dao.foodSeoulHotelData(temp);
	   request.setAttribute("bList", bList);
	   List<SeoulNatureVO> cList=dao.foodSeoulNatureData(temp);
	   request.setAttribute("cList", cList);
	   
	   //2. 댓글
	   
	   request.setAttribute("main_jsp", "../food/food_detail.jsp");
	   return "../main/main.jsp";
   }
   
   
}
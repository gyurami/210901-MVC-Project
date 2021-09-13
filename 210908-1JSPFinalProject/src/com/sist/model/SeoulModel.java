package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.xml.seoulWeatherManager;

@Controller
public class SeoulModel {
	@RequestMapping("seoul/seoul_main.do")
	public String seoul_main(HttpServletRequest request, HttpServletResponse response) {
		//include 파일 전송
		request.setAttribute("main_jsp", "../seoul/seoul_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("seoul/seoul_weather.do")
	public String seoul_weather(HttpServletRequest request, HttpServletResponse response) {
		seoulWeatherManager sm=new seoulWeatherManager();
		String data=sm.seoulWeather();
		request.setAttribute("data", data);
		request.setAttribute("main_jsp", "../seoul/seoul_weather.jsp");
		return "../main/main.jsp";
	}
}

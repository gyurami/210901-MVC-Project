package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainModel  implements Model{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return "main.do";
	}

}


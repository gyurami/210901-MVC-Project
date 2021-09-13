package com.sist.xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
public class seoulWeatherManager {
	public String seoulWeather() {
		String data="";
		try {
			Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
			Element elem=doc.selectFirst("section#content");
			String temp=elem.html();
			temp=temp.replace("src=\"", "src=\"https://korean.visitseoul.net");
			data=temp;
		}catch (Exception e) {}
		return data;
	}
	
}

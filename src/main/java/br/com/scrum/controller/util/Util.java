package br.com.scrum.controller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	public static Date parseDate(String source) {
		Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(source);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}
		return date;
	}
}

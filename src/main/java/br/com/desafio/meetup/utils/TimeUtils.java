package br.com.desafio.meetup.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	public static String calendarDateAsString(Calendar calendarDate, String dateFormat) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
        return dateFormatter.format(calendarDate.getTime());
    }
	
	public static Date stringToDate(String dateInString, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
        	e.printStackTrace();
            return null;
        }
    }

}

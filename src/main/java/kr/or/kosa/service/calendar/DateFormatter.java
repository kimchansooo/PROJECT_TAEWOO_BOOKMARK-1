package kr.or.kosa.service.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	
	String dateFormat = "YYYY-MM-DD";
	
	public Date dateParser(String input) throws ParseException {
		SimpleDateFormat formmater = new SimpleDateFormat(dateFormat);
		Date mydate = formmater.parse(input);
		return mydate;
	}
}

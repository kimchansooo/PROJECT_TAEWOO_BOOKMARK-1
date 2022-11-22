package kr.or.kosa.utils;

import javax.naming.NamingException;

import kr.or.kosa.dao.BlogDao;
import kr.or.kosa.dao.BookDao;
import kr.or.kosa.dao.BookMarkDao;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dao.PaymentDao;
import kr.or.kosa.dao.QuestionDao;
import kr.or.kosa.dao.StatisticsDao;
import kr.or.kosa.dao.UsersDao;

public class DaoFactory {
	public static BookMarkDao getDao(String name) {
		BookMarkDao dao = null;
		
		if(name.toLowerCase().equals("blog")) {
				dao = new BlogDao();
		} else if(name.toLowerCase().equals("book")) {
			dao = new BookDao();
		} else if(name.toLowerCase().equals("calendar")) {
			dao = new CalendarDao();
		} else if(name.toLowerCase().equals("payment")) {
			dao = new PaymentDao();
		} else if(name.toLowerCase().equals("quesition")) {
			dao = new QuestionDao();
		} else if(name.toLowerCase().equals("statistics")) {
			dao = new StatisticsDao();
		} else if(name.toLowerCase().equals("users")) {
			dao = new UsersDao();
		}
		return dao; 
	}
}

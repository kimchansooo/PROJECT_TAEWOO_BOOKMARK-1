package kr.or.kosa.service.calendar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dto.Calendar;

public class CalendarAllListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		System.out.println("캘린더 서비스 진입");
		
		//List<Calendar> calendarList = new ArrayList<>();
		List<Calendar> calendarList = null;
		
		//일정 받아와서 setAttribute	
		
		//DB작업
		try {
			CalendarDao dao = new CalendarDao();
			calendarList = dao.CalendarAlllist();
			
			request.setAttribute("calendarList", calendarList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//TODO:뷰 설정하기
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/calendarpage/calendarMain.jsp");
		
		return forward;
	}

}

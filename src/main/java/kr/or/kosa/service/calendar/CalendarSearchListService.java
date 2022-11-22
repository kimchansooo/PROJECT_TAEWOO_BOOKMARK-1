package kr.or.kosa.service.calendar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dto.Calendar;

public class CalendarSearchListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		List<Calendar> searchCalendarList = null;
		
		System.out.println("캘린더 서치 리스트 서비스 진입");
		try {
			
			String content = request.getParameter("content"); //검색어
			
			CalendarDao dao = new CalendarDao();
			searchCalendarList = dao.CalendarLikeList(content);
			
			request.setAttribute("searchCalendarList", searchCalendarList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		forward.setRedirect(false);
		forward.setPath(null); //TODO:뷰 설정
		
		return forward;
	}

}

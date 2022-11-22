package kr.or.kosa.service.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dto.Calendar;

public class CalendarDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		System.out.println("캘린더 디테일 서비스 진입");
		
		String calendar_no = request.getParameter("calendar_no");
//		String calendar_start = request.getParameter("calendar_start");
//		String calendar_end = request.getParameter("calendar_end");
//		String calendar_content = request.getParameter("calendar_content");
//		String calendar_status = request.getParameter("calendar_status");
		
		//SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		
		try {
			if(calendar_no == null || calendar_no.trim().equals("")) {
				response.sendRedirect(""); //TODO: 캘린더 리스트로
				return null;
			}
			
			CalendarDao dao = new CalendarDao();
			Calendar calendar = dao.CalendarDetail(Integer.parseInt(calendar_no));
			
			request.setAttribute("calendar", calendar);
			
			//TODO:이렇게만 해줘도 되는지 ? ? getter로 다 지정 해줘야되나 ??
			
			
//			calendar.setCalendar_content(calendar_content);
//			calendar.setCalendar_start(formatter.parse(calendar_start));
//			calendar.setCalendar_end(formatter.parse(calendar_end));
//			calendar.setCalendar_status(0);
			
			forward.setRedirect(false);
			forward.setPath("/views/calendarpage.jsp");//TODO: 디테일 뷰 지정
			
		} catch (Exception e) {
			
		} 
		return forward;
	}

}

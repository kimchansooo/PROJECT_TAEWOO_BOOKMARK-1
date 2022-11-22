package kr.or.kosa.service.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dto.Calendar;

public class CalendarAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String msg = "";
		String url = "";
		
		System.out.println("캘린더추가 서비스 진입");
		
//calendar_no, id, calendar_start, calendar_end, calendar_content, calendar_status
		
		Calendar calendar = new Calendar();
		
		//calendar_no는 시퀀스 사용
		String calendar_start = request.getParameter("calendar_start");
		String calendar_end = request.getParameter("calendar_end");
		String calendar_content = request.getParameter("calendar_content");
		//String calendar_status = request.getParameter("calendar_status");
		
		//TODO : calendar에는 date가 어떤 포맷으로 들어가지...
		//String으로 받은 date값 simple date format으로 date로...
		
		//SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		DateFormatter formatter = new DateFormatter();
		
		try {
			CalendarDao dao = new CalendarDao();
			calendar.setCalendar_content(calendar_content);
			calendar.setCalendar_start(formatter.dateParser(calendar_start));
			calendar.setCalendar_end(formatter.dateParser(calendar_end));
			//TODO:근데 status는 기본적으로 0으로 만들어지지 않나? default없나??
			calendar.setCalendar_status(0);
			
			int result = dao.CalendarAdd(calendar);
			
			if(result > 0) {
				msg = "캘린더 추가 성공";
				//url = ""; 캘린더 성공 서블릿
			}else {
				msg = "캘린더 추가 실패";
				//url = ""캘린더 실패 서블릿
			}
			
			//TODO: 이거 파라미터값
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		forward.setRedirect(false);
		//TODO:뷰 설정
		forward.setPath("");
		return forward;
	}

}

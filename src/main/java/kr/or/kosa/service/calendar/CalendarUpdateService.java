package kr.or.kosa.service.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dto.Calendar;

public class CalendarUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		System.out.println("캘린더 수정 서비스 진입");
		
		String calendar_no = request.getParameter("calendar_no");
		String calendar_start = request.getParameter("calendar_start");
		String calendar_end = request.getParameter("calendar_end");
		String calendar_content = request.getParameter("calendar_content");
		String calendar_status = request.getParameter("calendar_status");
		
		//SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		DateFormatter formatter = new DateFormatter();
		
		String msg = "";
		String url = "";
		
		try {
			CalendarDao dao = new CalendarDao();
			Calendar calendar = dao.CalendarDetail(Integer.parseInt(calendar_no));//이 함수로 가져오는거 맞지?
			
			//인덱스가 잘못됐을 경우
			if(calendar_no == null || calendar_no.trim().equals("")) {
				response.sendRedirect(""); //TODO:캘린더 목록으로 가도록
				return null;
			}
			
			//수정하려는 calendar가 null일 경우
			if(calendar == null) {
				msg = "캘린더 데이터 오류";
				url = "";//TODO:캘린더 목록으로 가도록
				
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				
				forward.setRedirect(false);
				forward.setPath(""); //TODO: redirect.jsp
			}
			
			//수정사항 반영
			calendar.setCalendar_start(formatter.dateParser(calendar_start));
			calendar.setCalendar_end(formatter.dateParser(calendar_end));
			calendar.setCalendar_content(calendar_content);
			calendar.setCalendar_status(Integer.parseInt(calendar_status));
			
			int result = dao.CalendarUpdate(calendar);
			
			if(result > 0) {
				msg = "캘린더 수정 성공";
				url = ""; //TODO:리스트로 뷰 지정
			}else {
				msg = "캘린더 수정 실패";
				url ="";//TODO:리스트로 뷰 지정
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			forward.setRedirect(false);
			//TODO:뷰 지정 리다이렉트.jsp
			forward.setPath("");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return forward;
	}

}

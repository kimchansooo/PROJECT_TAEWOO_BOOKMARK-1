package kr.or.kosa.service.calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.CalendarDao;
import kr.or.kosa.dto.Calendar;

public class CalendarDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		System.out.println("캘린더 삭제 서비스 진입");
		
		String calendar_no = request.getParameter("calendar_no");
		String msg = "";
		String url = "";
		
		try {
			CalendarDao dao = new CalendarDao();
			
			int result = dao.CalendarDelete(Integer.parseInt(calendar_no));
			
			if(result > 0) {
				msg = "일정 삭제 성공";
				url = ""; //TODO : 캘린더 리스트
			}else {
				msg = "일정 삭제 실패";
				url = "";//TODO:캘린더 리스트
			}
			
			forward.setRedirect(false);
			forward.setPath(""); //TODO:뷰 지정
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return forward;
	}

}

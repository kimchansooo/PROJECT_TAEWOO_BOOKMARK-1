package kr.or.kosa.service.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;

public class QuestionRewriteService implements Action {
//답글 달기
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String quetion_no = request.getParameter("question_no");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		String question_title = request.getParameter("question_title"); // 답글의 제목으로 사용

		try {
			if (quetion_no == null || question_title == null || quetion_no.trim().equals("") || question_title.trim().equals("")) {
				response.sendRedirect("BoardList.do");
				return null;
			}
			if (cpage == null || pagesize == null) {
				cpage = "1";
				pagesize = "5";
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
		request.setAttribute("quetion_no", quetion_no);
		request.setAttribute("cp", cpage);
		request.setAttribute("ps", pagesize);
		request.setAttribute("question_title", question_title);
		
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/board/board_rewrite.jsp");
		
		
		return forward;
	}
	

}

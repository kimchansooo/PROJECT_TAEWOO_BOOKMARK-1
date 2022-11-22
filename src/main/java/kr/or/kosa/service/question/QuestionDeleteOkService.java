package kr.or.kosa.service.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.QuestionDao;

public class QuestionDeleteOkService implements Action {
//문의사항 삭제
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		String pwd =request.getParameter("pwd");
		
		String msg ="";
		String url = "";
		QuestionDao qdao;
		try {
			qdao = new QuestionDao();
			
			int result = 0;//qdao.deleteQuestion(question_no);
			
			if(result > 0) {
				msg="delete success";
				url=".do";
			}else {
				msg="delete fail";
				url=".do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url" , url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/redirect.jsp");
		return forward;
	}

}

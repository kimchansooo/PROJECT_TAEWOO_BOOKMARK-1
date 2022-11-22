package kr.or.kosa.service.question;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.QuestionDao;
import kr.or.kosa.dto.Question_Board;

public class QuestionUpdateOkService implements Action {
//수정 처리
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String question_no = request.getParameter("question_no");

		String msg = "";
		String url = "";
		ActionForward forward = null;
		
		try {
			QuestionDao qdao = new QuestionDao();
			
			if(question_no == null || question_no.trim().equals("")) {
				msg = "글번호 입력 오류";
				url = "BoardList.do";
			}else {
				int result = 0;//qdao.updateQuestion();
				
				if(result > 0) {
					msg="edit success";
					url = "BoardList.do";
				} else {
					msg = "edit fail";
					url = "BoardEdit.do?question_no=" + question_no;
				}
			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/redirect.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}

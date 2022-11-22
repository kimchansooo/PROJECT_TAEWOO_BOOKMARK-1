package kr.or.kosa.service.question;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.QuestionDao;
import kr.or.kosa.dto.Question_Board;

public class QuestionWriteService implements Action {
//문의사항 쓰기 페이지 이동
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
			QuestionDao qdao = new QuestionDao();
			Question_Board qboard = new Question_Board();
			qboard.setId(request.getParameter("id"));
			qboard.setQuestion_title(request.getParameter("question_title"));
			qboard.setQuestion_content(request.getParameter("question_content"));
			qboard.setHits(Integer.parseInt(request.getParameter("hits")));
			qboard.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
			
			int result = 0;
			
			try {
				result = qdao.writeQuestionBoard(qboard);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String msg = "";
			String url ="";
			if(result >0) {
				msg = "insert success";
				url = "BoardList.do";
			}else {
				msg = "insert fail";
				url ="BoardWrite.do";
			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/board/redirect.jsp");
			return forward;
	}

}

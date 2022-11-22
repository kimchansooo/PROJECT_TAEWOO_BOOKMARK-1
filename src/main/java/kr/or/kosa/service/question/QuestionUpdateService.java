package kr.or.kosa.service.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.QuestionDao;
import kr.or.kosa.dto.Question_Board;

public class QuestionUpdateService implements Action {
//문의사항 수정하기
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
String question_no = request.getParameter("question_no");
		
		String msg="";
	    String url="";

		QuestionDao qdao;
		ActionForward forward = null;
		try {		
			if(question_no == null || question_no.trim().equals("")){
				response.sendRedirect("BoardList.do"); //cpage=1 , ps=5
				return null;
			}

			qdao = new QuestionDao();
			
			//BoardService service = BoardService.getInBoardService();
			 Question_Board qboard =null;// qdao.getEditContent(question_no);
			
			if(qboard == null){
				msg ="데이터 오류";
				url ="BoardList.do";
				
				request.setAttribute("board_msg", msg);
				request.setAttribute("board_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/board/redirect.jsp");
				
			}else {
				request.setAttribute("question_no", question_no);
				request.setAttribute("qboard", qboard);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/board/board_edit.jsp");
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}

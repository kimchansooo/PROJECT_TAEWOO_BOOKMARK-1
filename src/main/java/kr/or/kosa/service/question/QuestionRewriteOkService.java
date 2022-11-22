package kr.or.kosa.service.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.QuestionDao;
import kr.or.kosa.dto.Question_Board;

public class QuestionRewriteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String strquestion_no = request.getParameter("question_no").trim();		
		String question_title = request.getParameter("question_title");
		String id = request.getParameter("id");
		String question_content = request.getParameter("question_content");
		String notice_no = request.getParameter("notice_no");
		String msg="";
	    String url="";
		
				
		try {
			int question_no = Integer.parseInt(strquestion_no);
			
			Question_Board board = new Question_Board();
			
			board.setQuestion_no(question_no);
			board.setQuestion_title(question_title);
			board.setId(id);
			board.setQuestion_content(question_content);
			board.setNotice_no(Integer.parseInt(notice_no));
			

			QuestionDao dao = new QuestionDao();
			
			int result = dao.rewriteQuestion(board);

		    if(result > 0){
		    	msg ="rewrite insert success";
		    	url ="BoardList.do";
		    }else{
		    	msg="rewrite insert fail";
		    	url="BoardContent.do?idx="+board.getQuestion_no();
		    }
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("msg",msg);
		request.setAttribute("url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/utils/redirect.jsp");
		
		return forward;
	}

}

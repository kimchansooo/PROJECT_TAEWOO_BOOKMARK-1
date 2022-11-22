package kr.or.kosa.service.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.QuestionDao;
import kr.or.kosa.dto.Question_Board;
//글쓰기 처리 
public class QuestionWriteOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String msg="";
	    String url="";
	    
	    try {
	    	QuestionDao dao = new QuestionDao();
	    	Question_Board board = new Question_Board();
	    	
	    	board.setId(request.getParameter("id"));
	    	board.setQuestion_title(request.getParameter("question_title"));
	    	board.setQuestion_content(request.getParameter("question_content"));
	    	board.setHits(Integer.parseInt(request.getParameter("hits")));
	    	board.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
	    	
	    
	    	int result = dao.writeQuestionBoard(board);
	    	 
		    if(result > 0){
		    	msg ="insert success";
		    	url ="_list.jsp";
		    }else{
		    	msg="insert fail";
		    	url="_write.jsp";
		    }
		    
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		
			    
	    //write.jsp 화면  >> writeok.jsp 처리 >> service >> dao > DB 작업 > 
	    //return dao > return service >  writeok.jsp 결과처리 >> 이동 (공통) >> redirect.jsp
	    		
	   
	    request.setAttribute("board_msg",msg);
	    request.setAttribute("board_url", url);
	    
	    
	    forward.setRedirect(false);
	    forward.setPath("redirect.jsp");
	    
		return forward;
	}
}

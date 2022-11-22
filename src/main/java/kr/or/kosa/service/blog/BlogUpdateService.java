package kr.or.kosa.service.blog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BlogDao;
import kr.or.kosa.dto.Blog_Board;

public class BlogUpdateService implements Action {
//블로그 게시글 수정 페이지 이동
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BlogDao dao = new BlogDao();
			
			Blog_Board board = dao.getContent(Integer.parseInt(request.getParameter("blog_no")));
			
			request.setAttribute("content", board);
			
			forward.setPath("blogedit.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			e.printStackTrace();
			String msg  = "update error";
			String path = "블로그게시글.do?" + request.getParameter("blog_no");
		forward.setPath("redirect.jsp");
		forward.setRedirect(false);
		} 
		return forward;
	}

}

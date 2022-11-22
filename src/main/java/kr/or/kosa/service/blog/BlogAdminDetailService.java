package kr.or.kosa.service.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BlogDao;
import kr.or.kosa.dto.Blog_Board;

public class BlogAdminDetailService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BlogDao dao = new BlogDao();
			int board_no = Integer.parseInt(request.getParameter("blog_no"));
			
			Blog_Board board = dao.getContent(board_no);
			
			request.setAttribute("content", board);
			forward.setPath("");
			forward.setRedirect(false);
		} catch (Exception e) {
			request.setAttribute("board_msg", "없는 게시글 입니다.");
			request.setAttribute("board_url", "blog.do?id="+ (String)request.getSession().getAttribute("id"));
			forward.setPath("redirect.jsp");
			forward.setRedirect(false);
		} 
		return forward;
	}

}

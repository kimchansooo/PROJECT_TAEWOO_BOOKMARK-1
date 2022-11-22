package kr.or.kosa.service.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BlogDao;
import kr.or.kosa.dto.Blog_Board;

public class BlogAdminEdit implements Action {
 //22.  관리자 - 블로그 게시글 수정 페이지 이동
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			BlogDao dao = new BlogDao();
			int blog_no = Integer.parseInt(request.getParameter("blog_no"));
			Blog_Board board = dao.getContent(blog_no);
			
			request.setAttribute("content", board);
			
			forward.setPath("adminblogedit.jsp");
			forward.setRedirect(false);
		} catch (Exception e) {
			e.printStackTrace();
			String msg  = "서버 오류 발생";
			String path = "main.do";
			request.setAttribute("msg", msg);
			request.setAttribute("path", path);
			forward.setPath("redirect.jsp");
			forward.setRedirect(false);
		} 
		return forward;
	}

}

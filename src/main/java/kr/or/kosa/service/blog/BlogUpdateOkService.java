package kr.or.kosa.service.blog;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BlogDao;
import kr.or.kosa.dto.Blog_Board;

public class BlogUpdateOkService implements Action {
//회원 - 블로그 게시글 수정 처리
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
			String msg = "";
			String path = "";
		
		try {
			BlogDao dao = new BlogDao();
			
//			Blog_Board blog = new Blog_Board();
//			blog.setBlog_content(request.getParameter("blog_content"));
//			blog.setBlog_date(new Date(request.getParameter("blog_date")));
//			blog.setBlog_filename(null);
//			blog.setBlog_no(Integer.parseInt(request.getParameter("id")));
//			blog.setBlog_title(request.getParameter("blog_title"));
//			blog.setHits(Integer.parseInt(request.getParameter("hits")));
//			blog.setId(request.getParameter("id"));
			
			int row = dao.blogEdit(request);
			
			if(row >0) {
				msg = "update success";
				path = "블로그게시글.do?" + request.getParameter("blog_no");
			} else {
				msg = "update fail";
				path = "블로그게시글.do?" + request.getParameter("blog_no");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "update error";
			path = "블로그게시글.do?" + request.getParameter("blog_no");
		} 
		forward.setPath("redirect.jsp");
		forward.setRedirect(false);
		return forward;
	}

}

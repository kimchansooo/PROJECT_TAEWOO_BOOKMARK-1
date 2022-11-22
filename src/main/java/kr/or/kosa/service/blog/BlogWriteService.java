package kr.or.kosa.service.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.BlogDao;
import kr.or.kosa.dto.Blog_Board;

public class BlogWriteService implements Action {
//회원 - 블로그 게시글 작성 처리
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		MultipartRequest multi = null;
		try {
			BlogDao dao = new BlogDao();
			
			multi = new MultipartRequest(
					request,
					"/WEB-INF/Blog/upload",
					1024*1024*10,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);
			
			String id = (String)request.getSession().getAttribute("id");
			String title = multi.getParameter("blog_title");
			String content = multi.getParameter("blog_content");
			String blog_filename = multi.getFilesystemName("file");
			
			Blog_Board board = new Blog_Board();
			board.setId(id);
			board.setBlog_title(title);
			board.setBlog_content(content);
			board.setBlog_filename(blog_filename);
			
			forward.setRedirect(false);
			forward.setPath("blog.do?id="+id);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "에러가 발생했습니다.");
			request.setAttribute("url", "main.do");
			forward.setPath("에러페이지");
			forward.setRedirect(true);
		} 
		return forward;
	}
}

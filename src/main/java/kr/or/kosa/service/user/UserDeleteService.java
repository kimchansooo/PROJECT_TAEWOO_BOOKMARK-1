package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UsersDao;

public class UserDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String id = request.getParameter("id");
		String msg = "";
		String url = "";
		
		try {
			UsersDao dao = new UsersDao();
			
			boolean result = dao.deleteUser(id);
			
			if(result) {
				msg = "회원삭제가 완료되었습니다.";
				url = "userlist.do";
			}else {
				msg = "회원삭제의 실패하였습니다.";
				url = "userlist.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/utils/redirect.jsp");
		
		return forward;
	}

}

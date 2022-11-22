package kr.or.kosa.service.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UsersDao;

public class UserLoginService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String id = "";
		String pwd = "";
		
		try {
			UsersDao dao = new UsersDao();
			PrintWriter out = response.getWriter();
			
			id = request.getParameter("id");
			pwd = request.getParameter("passward");
			
			int ok = dao.userLogin(id, pwd);
			
			out.print(ok); //0:아이디 없음, 1:비밀번호틀림, 2:회원, 3:관리자
			
		} catch (Exception e) {
			
		} 
		return null;
	}

}

package kr.or.kosa.service.user;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UsersDao;
import kr.or.kosa.dto.Users;

public class UserIdCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		UsersDao dao = new UsersDao();
		
		
		String id = request.getParameter("id");
		boolean user = dao.userIdCk(id);
		
		boolean ok =true;
		
		try {
			PrintWriter out = response.getWriter();
			if(user) { //중복검사 중복됨
				ok = false;
			}
			out.print(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

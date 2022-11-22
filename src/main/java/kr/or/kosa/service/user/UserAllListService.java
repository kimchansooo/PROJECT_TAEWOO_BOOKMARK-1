package kr.or.kosa.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.UsersDao;
import kr.or.kosa.dto.Users;

public class UserAllListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String ps = "";
		String cp = "";
		try {
			UsersDao dao = new UsersDao();
			
			int totalusercount = dao.hashCode();//이름 알아내서 바꿔야함
			
			ps = request.getParameter("pagesize"); // pagesize
			cp = request.getParameter("cpage"); // current page

			// List 페이지 처음 호출 ...
			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}

			if (cp == null || cp.trim().equals("")) {
				// default 값 설정
				cp = "1"; // 1번째 페이지 보겠다
			}

			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;

			// 23건 % 5
			if (totalusercount % pagesize == 0) {
				pagecount = totalusercount / pagesize; // 20 << 100/5
			} else {
				pagecount = (totalusercount / pagesize) + 1;
			}

			// 102건 : pagesize=5 >> pagecount=21페이지

			// 전체 목록 가져오기
			List<Users> list = dao.getUserAllList(pagesize, cpage);//count하는 dao있어야함
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return forward;
	}

}

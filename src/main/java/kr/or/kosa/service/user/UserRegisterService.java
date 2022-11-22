package kr.or.kosa.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dto.Users;

public class UserRegisterService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		Users user = new Users();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("passward");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String detail_addr = request.getParameter("detail_addr");
		String register_no = request.getParameter("register_no");
		String phone = request.getParameter("phone");
		
		user.setId(id);
		user.setPassword(pwd);
		user.setName(name);
		user.setAddr(addr);
		user.setDetail_addr(detail_addr);
		user.setRegist_no(register_no);
		user.setPhone(phone);
		try {
			
		} catch (Exception e) {
			
		} 
		return forward;
	}

}

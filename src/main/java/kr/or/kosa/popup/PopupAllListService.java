package kr.or.kosa.popup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.dao.PopupDao;
import kr.or.kosa.dto.Popup;
import kr.or.kosa.utils.ThePager;

public class PopupAllListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		try {
			PopupDao dao = new PopupDao();
			
			int totalpopupcount = dao.totalPopupCount();
			
			String ps = request.getParameter("ps"); // pagesize
			String cp = request.getParameter("cp"); // current page

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

			
			if (totalpopupcount % pagesize == 0) {
				pagecount = totalpopupcount / pagesize; // 20 << 100/5
			} else {
				pagecount = (totalpopupcount / pagesize) + 1;
			}

			// 102건 : pagesize=5 >> pagecount=21페이지

			// 전체 목록 가져오기
			List<Popup> list = dao.AllListPopup(cpage, pagesize); // list >> 1 , 20
			
			int pagersize=3; //[1][2][3]
			ThePager pager = new ThePager(totalpopupcount,cpage,pagesize,pagersize,"PopupAllList.do");
			
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalpopupcount);
			request.setAttribute("pager", pager);

			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/Popup/PopupAllList.jsp");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return forward;
	}

}

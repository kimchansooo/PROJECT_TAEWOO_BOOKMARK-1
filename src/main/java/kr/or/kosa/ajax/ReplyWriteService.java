package kr.or.kosa.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosa.dto.ReplyInterface;

@WebServlet("/ReplyWrite")
public class ReplyWriteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyWriteService() {
    }
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReplyInterface reply = null;
//		BookMarkDao dao = null;
		try {
			//request에 type이라는 이름으로 블로그/책 댓글 구분됨
//			if() {
//				 dao = new DaoFactory().getDao(""); //dao 팩토리. 이름만 넣으면 됨
//			} else {
//				 dao = new DaoFactory().getDao(""); //dao 팩토리. 이름만 넣으면 됨
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//작성된 댓글 객체 그대로 저장
		request.setAttribute("reply", reply);
		//forward 작업?
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

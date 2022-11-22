package kr.or.kosa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.kosa.action.Action;
import kr.or.kosa.action.ActionForward;
import kr.or.kosa.service.calendar.CalendarAllListService;

@WebServlet("*.do")
public class BookMarkController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookMarkController() {
        super();
    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	ActionForward forward=null;
    	//페이지 - 기능
    	//=================================================================//
    	//                                                     메인페이지이동
    	//=================================================================//
    	 if(url_Command.equals("")) { //0. 메인 페이지 이동
     		// 이동+처리
     		action = null;                                                
     		forward = action.execute(request, response);
    	//=================================================================//
    	//                                                     문의사항
    	//=================================================================//
     	} else if(url_Command.equals("")) { //1. 회원 - 문의사항 페이지 이동
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //2. 관리자 - 문의사항 관리 페이지 이동
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //3. 문의사항 검색(like) - 비동기
    		// 처리 + json
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //4. 회원 - 문의사항 글쓰기 페이지 이동
    		// 이동
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //5. 회원 - 문의사항 글쓰기 처리
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //6. 회원 - 문의사항 글 수정 페이지 이동
    		//이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //7. 회원 - 문의사항 글 수정 페이지 처리
    		//이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //8. 회원 - 문의사항 글 삭제 처리 기능
    		//이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //9. 회원 - 문의사항 글 상세보기
    		//이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 10. 회원 - 문의사항 답글 작성 페이지 이동 
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //11. 회원(관리자) - 문의사항 답글 작성 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //12. 관리자 - 문의사항 글 상세보기
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	}
    	//=================================================================//
    	//                                                     블로그
    	//=================================================================//
    	 else if(url_Command.equals("")) { //13. 관리자 - 블로그 게시글 전체조회
     		// 이동+처리
     		action = null;
     		forward = action.execute(request, response);
     	} else if(url_Command.equals("")) { //14. 관리자 - 블로그 게시글 like 조회
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //15. 회원 - 회원 블로그 페이지 보기
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //16. 회원 - 블로그 게시글 작성 페이지 이동
    		// 이동
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 17. 회원 - 블로그 게시글 작성 처리
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 18. 회원 - 블로그 게시글 수정 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 19. 회원 - 블로그 게시글 수정 처리
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 20. 회원 - 블로그 게시글 삭제 처리
    		// 처리 + 이동 ( 비동기 ?)
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //21. 회원 - 블로그 게시글 상세보기
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //22.  관리자 - 블로그 게시글 수정 페이지 이동
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //23. 관리자 - 블로그 게시글 수정 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //24. 관리자 - 블로그 게시글 삭제 처리
    		// 처리+이동 (비동기?)
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //25. 관리자 - 블로그 게시글 상세보기
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	}
    	//=================================================================//
    	//                                                     책
    	//=================================================================//
    	else if(url_Command.equals("")) { // 26. 관리자 - 책 전체조회
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 27. 회원 - 책 like 조회 ( 책검색 )
    		// 이동 + 처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { // 28 .관리자 - 책 like 조회
    		// 처리 (비동기)
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //29.관리자 - 책 추가 페이지 이동
    		// 이동
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //30.관리자 - 책 추가 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //31 관리자 - 책 수정 페이지 이동
    		// 이동+추가
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //32  관리자 - 책 수정 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //33 관리자 - 책 삭제 처리
    		// 처리(비동기?)
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //34.회원 - 책 상세보기 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //35. 관리자 - 책 상세보기 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //36.회원 - 책 좋아요 기능
    		// 처리(비동기)
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //37.관리자 - 추천책 설정 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //38.관리자 - 추천책 추가
    		// 처리 (비동기)
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //39.관리자 - 추천책 삭제
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //40. 회원 - e-book 리스트 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	}
     	//=================================================================//
     	//                                                     결제
     	//=================================================================//
    	else if(url_Command.equals("")) { //41 회원 - 장바구니 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //42 회원 - 장바구니 추가
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //43 회원 - 장바구니 삭제
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //44 회원 - 결제 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //45 회원 - 결제 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //46 회원 - 결제 내역 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //47 관리자 - 결제 전체 조회 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //48 관리자 - 결제 id 검색 기능
    		// 비동기?
    		action = null;
    		forward = action.execute(request, response);
    	}
     	//=================================================================//
     	//                                                     회원
     	//=================================================================//
    	 else if(url_Command.equals("")) { //49  회원 - 로그인 페이지 이동
     		// 이동
     		action = null;
     		forward = action.execute(request, response);
     	} else if(url_Command.equals("")) { //50  회원 - 로그인 처리 
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //51  회원 - 회원가입 페이지 이동 
    		// 이동
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //52 회원 - 회원가입 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //53 회원 - 마이페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //54 회원 - 회원정보 수정 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //55 회원 - 회원정보 수정 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //56 회원 - 회원탈퇴 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //57 관리자 - 회원 리스트 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //58 관리자 - 회원 like 검색
    		// 비동기?
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //59 관리자 - 회원 상세 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //60 관리자 - 회원정보 수정 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //61 관리자 - 회원 정보 수정 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //62 관리자 - 회원 삭제 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //63 회원 - 아이디 중복 체크
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	}
     	//=================================================================//
     	//                                                     통계페이지
     	//=================================================================// 
    	else if(url_Command.equals("")) { //64 관리자 - 통계 페이지 이동(남녀성비통계)
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //65 관리자 - 연령 비율 통계
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //66 관리자 - 일매출 통계
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //67 관리자 - 월매출 통계
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //68 관리자 - 주 매출 통계
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //69 관리자 - 년 매출 통계
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	}
     	//=================================================================//
     	//                                                     일정관리 - 관리자 전용
     	//=================================================================//
    	else if(url_Command.equals("/calendarall.do")) { //70 일정관리 전체 페이지 이동 (관리자페이지)
    		// 이동+처리
    		System.out.println("calendarall.do 분기 진입");
    		action = new CalendarAllListService();
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //71 일정관리 검색
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //72 일정 추가
    		// 비동기
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //73 일정수정(비동기?동기?)
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //74 일정수정처리(비동기면 없어도됨/동기면 만들어야됨)
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //75 일정 삭제
    		// 비동기?
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //76 일정상세보기
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	}
     	//=================================================================//
     	//                                                     팝업공지사항
     	//=================================================================// 
    	else if(url_Command.equals("")) { // 77 관리자 - 팝업 공지사항 전체 조회
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //78 관리자 - 팝업 공지사항 like 조회
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //79 관리자 - 팝업 공지사항 추가 페이지 이동
    		// 이동
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //80 관리자 - 팝업 공지사항 추가 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //81 관리자 - 팝업 공지사항 수정 페이지 이동
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //82 관리자 - 팝업 공지사항 수정 처리
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //83 관리자 - 팝업 공지사항 삭제
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	} else if(url_Command.equals("")) { //84 관리자 - 팝업 공지사항 상세보기
    		// 이동+처리
    		action = null;
    		forward = action.execute(request, response);
    	}
    	 
    	//=================================================================//
    	//                                                     RequestDispatcher
    	//=================================================================//
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		} else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}

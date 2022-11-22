package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Question_Board;
import kr.or.kosa.utils.ConnectionHelper;

public class QuestionDao implements BookMarkDao{
	//글 전체조회
	//select * from question_board;
	public List<Question_Board> getQuestionAllList(int cpage , int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Question_Board> list = new ArrayList<Question_Board>();
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from"
					+ "    (select rownum rn,question_no, id, question_title,question_content,hits,question_date,"
					+ "    refer, depth, step, notice_no from"
					+ "        ( SELECT * FROM question_board ORDER BY notice_no asc, refer DESC , step ASC )"
					+ "    where rownum <= ?) where rn >= ?";
			pstmt = conn.prepareStatement(sql);

			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Question_Board question = new Question_Board();
				question.setQuestion_no(rs.getInt(1));
				question.setId(rs.getString(2));
				question.setQuestion_title(rs.getString(3));
				question.setQuestion_content(rs.getString(4));
				question.setHits(rs.getInt(5));
				question.setQuestion_date(rs.getDate(6));
				question.setRefer(rs.getInt(7));
				question.setDepth(rs.getInt(8));
				question.setStep(rs.getInt(9));
				question.setNotice_no(rs.getInt(10));
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
		}
		return list;
	}
	//게시물 총 건수 구하기
		public int totalBoardCount() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalcount = 0;
			try {
				conn = ConnectionHelper.getConnection("oracle"); //연결객체
				String sql = "select count(*) as cnt from question_board";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalcount = rs.getInt("cnt");
				}
			} catch (Exception e) {
				System.out.println("totalBoardCount 예외 : " + e.getMessage());
			}
			return totalcount;
		}
	
	//글 like 조회
	//select * from question_board where [type] like [value];
	public List<Question_Board> getQuestionLikeList(String type, String value,int cpage , int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Question_Board> list = new ArrayList<Question_Board>();
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select * from"
					+ "    (select rownum rn,question_no, id, question_title,question_content,hits,question_date,"
					+ "    refer, depth, step, notice_no from"
					+ "        ( SELECT * FROM question_board where ? like ?ORDER BY notice_no asc, refer DESC , step ASC )"
					+ "    where rownum <= ?) where rn >= ?";
			pstmt = conn.prepareStatement(sql);
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			pstmt.setString(3, type);
			pstmt.setString(4, value);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Question_Board question = new Question_Board();
				question.setQuestion_no(rs.getInt(1));
				question.setId(rs.getString(2));
				question.setQuestion_title(rs.getString(3));
				question.setQuestion_content(rs.getString(4));
				question.setHits(rs.getInt(5));
				question.setQuestion_date(rs.getDate(6));
				question.setRefer(rs.getInt(7));
				question.setDepth(rs.getInt(8));
				question.setStep(rs.getInt(9));
				question.setNotice_no(rs.getInt(10));
				list.add(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			ConnectionHelper.close(conn);
		}
		return list;
	}
	//검색한 게시물 총 건수 구하기
			public int totalBoardCountByLike(String type, String value) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int totalcount = 0;
				try {
					conn = ConnectionHelper.getConnection("oracle"); //연결객체
					String sql = "select count(*) as cnt from question_board where ? like ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, type);
					pstmt.setString(2, value);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						totalcount = rs.getInt("cnt");
					}
				} catch (Exception e) {
					System.out.println("totalBoardCount 예외 : " + e.getMessage());
				}
				return totalcount;
			}
	//(원본)글쓰기
	public int writeQuestionBoard(Question_Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "insert into question_board"
					+ "(question_no,id,question_title,question_content,hits,"
					+ "question_date,refer,notice_no)"
					+ "values(question_no_seq.nextval,?,?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getQuestion_title());
			pstmt.setString(3, board.getQuestion_content());
			pstmt.setInt(4, board.getHits());
			pstmt.setInt(6, board.getNotice_no());
			
			int refermax = getMaxRefer();
			int refer = refermax + 1;
			pstmt.setInt(5,refer);
			
			row = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return row;
	}
	//(원본글)글쓰기 refer 값 생성하기
	private int getMaxRefer() {
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int refer_max=0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql="select nvl(max(refer),0) from question_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				refer_max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return refer_max;
	}
	//답글쓰기
	public int rewriteQuestion(Question_Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			
			//원본글의 게시글 번호
			int board_no = board.getQuestion_no();
			
			
			//1. 원본글의 refer, depth, step
			String refer_depth_step_sal ="select refer , depth , step from question_board where question_no=?";
			//2. 위치
			String step_update_sql = "update question_board set step= step+1 where step  > ? and refer =? ";
			//3. 답글 insert
			String rewrite_sql = "insert into question_board(question_no, id, question_title,question_content,hits,question_date,refer, depth, step, notice_no)"
					+ "values (question_no_seq.nextval,?,?,?0,sysdate,?,?,?,?)";
			
			//[1] 실행
			pstmt = conn.prepareStatement(refer_depth_step_sal);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");
				
				//[2] 실행
				pstmt = conn.prepareStatement(step_update_sql);
				pstmt.setInt(1, step);
				pstmt.setInt(2, refer);
				pstmt.executeUpdate();
				
				//[3] insert 작업 실행
				//question_no, id, question_title,question_content,hits,question_date,refer, depth, step, notice_no
				//question_no_seq.nextval,?,?,?0,sysdate,?,?,?,?)
				//id, title, content, refer,depth,step,notice
				pstmt = conn.prepareStatement(rewrite_sql);
				pstmt.setString(1, board.getId());
				pstmt.setString(2, board.getQuestion_title());
				pstmt.setString(3, board.getQuestion_content());
				pstmt.setInt(4, refer);
				pstmt.setInt(5, depth+1);
				pstmt.setInt(6, step+1);
				pstmt.setInt(7, board.getNotice_no());
				
				int row = pstmt.executeUpdate();
				if(row > 0) {
					result = row;
				} else {
					result = -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	//글수정
	// 제목, 내용, 날짜, 공지사항유무 업데이트
	public int updateQuestion(Question_Board board) {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int row = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "update question_board"
					+ "set question_title = ?,question_content=?,question_date=sysdate, notice_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getQuestion_title());
			pstmt.setString(2, board.getQuestion_content());
			pstmt.setInt(3, board.getNotice_no());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return row;
	}
	//글삭제
	public boolean deleteQuestion(String question_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql="delete from question_board where question_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question_no);
			
			int row = pstmt.executeUpdate();
			
			if(row > 0 ) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	//상세정보
	public Question_Board getQuestionByNo(String questionNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Question_Board question = null;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select question_no, id, question_title,question_content,hits,question_date,refer, depth, step, notice_no where question_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, questionNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				question.setQuestion_no(rs.getInt(1));
				question.setId(rs.getString(2));
				question.setQuestion_title(rs.getString(3));
				question.setQuestion_content(rs.getString(4));
				question.setHits(rs.getInt(5));
				question.setQuestion_date(rs.getDate(6));
				question.setRefer(rs.getInt(7));
				question.setDepth(rs.getInt(8));
				question.setStep(rs.getInt(9));
				question.setNotice_no(rs.getInt(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			ConnectionHelper.close(conn);
		}
		return question;
	}
	//조회수 증가
	public int hitUp(int questionNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			String sql = "update question_board set hit=hit+1 where question_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, questionNo);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return row;
	}
}
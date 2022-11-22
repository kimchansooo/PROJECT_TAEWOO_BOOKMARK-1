package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import kr.or.kosa.dto.Blog_Board;
import kr.or.kosa.dto.Blog_Reply;
import kr.or.kosa.utils.ConnectionHelper;

public class BlogDao implements BookMarkDao{

	//TODO : autoCommit 어떻게 해야되는지?
	//TODO : 시퀀스 사용 제대로 되는지 확인
	
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	
	public BlogDao() {
		try {
			conn = ConnectionHelper.getConnection("orcle");
			pstmt = null;
			rs = null;
			sql = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//블로그 글 전체 불러오기
	public List<Blog_Board> AllBoard(){//int cpage , int pagesize){
		List<Blog_Board> boardList = null;
		try {
			String sql=  "select a.blog_no, a.id, a.blog_title, a.blog_content, a.hits, a.blog_date, b.file_name "
					+ "from blog_board a left join blogfile b "
					+ "on a.blog_no = b.blog_no ";
			pstmt = conn.prepareStatement(sql);
			//공식같은 로직
//			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
//			int end = cpage * pagesize; // 1 * 5 >> 5
//			
//			pstmt.setInt(1, end);
//			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			boardList = new ArrayList<>();
			
			while(rs.next()) {
				Blog_Board board = new Blog_Board();
				board.setBlog_no(rs.getInt(1));//블로그 번호
				board.setId(rs.getString(2)); //작성자
				board.setBlog_title(rs.getString(3)); //제목
				board.setBlog_content(rs.getString(4));//내용
				board.setHits(rs.getInt(5));//조회수
				board.setBlog_date(rs.getDate(6)); //날짜
				board.setBlog_filename(rs.getString(7)); //파일이름
				
				boardList.add(board);
			}
			
		}catch (Exception e) {
			System.out.println("AllBoard 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
//				pstmt.close();
//				rs.close();
//				conn.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return boardList;
	}
	
	//특정 아이디의 블로그 게시글 전체 조회 추가 김태우 (11.21)//블로그 글 전체 불러오기
	public List<Blog_Board> getBoardListById(String id){//int cpage , int pagesize){
		List<Blog_Board> boardList = null;

		try {
			String sql=  "select a.blog_no, a.id, a.blog_title, a.blog_content, a.hits, a.blog_date, b.file_name "
					+ "from blog_board a left join blogfile b "
					+ "on a.blog_no = b.blog_no where a.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//공식같은 로직
//			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
//			int end = cpage * pagesize; // 1 * 5 >> 5
//			
//			pstmt.setInt(1, end);
//			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			boardList = new ArrayList<>();
			
			while(rs.next()) {
				Blog_Board board = new Blog_Board();
				board.setBlog_no(rs.getInt(1));//블로그 번호
				board.setId(rs.getString(2)); //작성자
				board.setBlog_title(rs.getString(3)); //제목
				board.setBlog_content(rs.getString(4));//내용
				board.setHits(rs.getInt(5));//조회수
				board.setBlog_date(rs.getDate(6)); //날짜
				board.setBlog_filename(rs.getString(7)); //파일이름
				
				boardList.add(board);
			}
			
		}catch (Exception e) {
			System.out.println("AllBoard 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return boardList;
	}
	
	//게시물 총 건수 구하기
	public int totalBoardCount() {

		int totalcount = 0;
		try {
			String sql = "select count(*) as cnt from blog_board";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			System.out.println("totalBoardCount 예외 : " + e.getMessage());
		}finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return totalcount;
	}
	
	
	//블로그 특정 글 조회
	//파일이 있을 경우 조인해서...
	public Blog_Board getContent(int blog_no) {

		Blog_Board board = null;
		
		try {
			String sql = "select a.blog_no, a.id, a.blog_title, a.blog_content, a.hits, a.blog_date, b.file_name "
					+ "from blog_board a left join blogfile b "
					+ "on a.blog_no = b.blog_no "
					+ "where a.blog_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blog_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString("id");
				String blog_title = rs.getString("blog_title");
				String blog_content = rs.getString("blog_content");
				int hits = rs.getInt("hits");
				java.sql.Date blog_date = rs.getDate("blog_date");
				String file_name = rs.getString("file_name");
				
				board = new Blog_Board(blog_no, id, blog_title, blog_content, hits, blog_date, file_name);
			}	
		} catch (Exception e) {
			System.out.println("getContent 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return board;
	}
	
	//게시글 조회수 증가
	public boolean upHits(int blog_no) {
		boolean result = false;
		
		try {
			String sql = "update blog_board set hits = hits + 1"
					+ " where blog_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blog_no);
			
			int resultrow = pstmt.executeUpdate();
			if(resultrow > 0) {
				result = true;
			}
			
		} catch (Exception e) {
			System.out.println("upHits 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return result;
	}
	
	//글 작성
	public int writeok(Blog_Board board) {

		int row = 0;
		
		try {
			String boardsql = "insert into"
					+ " blog_board(blog_no, id, blog_title, blog_content, hits)"
					+ " values(blog_no_seq.nextval,?,?,?,?)";
			//blog_no 에 _seq 추가 (11.21 김태우)
			pstmt = conn.prepareStatement(boardsql);
			
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getBlog_title());
			pstmt.setString(3, board.getBlog_content());
			pstmt.setInt(4, board.getHits());
			row = pstmt.executeUpdate();
			//파일이 있다면 /////////////////////////
			if(board.getBlog_filename() != null) {
				pstmt.close();
				String filesql = "insert into blogfile(blog_no, file_name) values(?,?)";
				pstmt = conn.prepareStatement(filesql);
				pstmt.setInt(1, board.getBlog_no()); ////////////이 부분은 어떻게 해야될까??
						//TODO : 블로그 파일 테이블 인덱스 처리하기
						//블로그테이블에서는 nextval로 인덱스를 증가시켰는데
						//모든 글이 파일을 가지고 있는건 아니니까 여기선 그렇게 하면 안되는데
						//그럼 인덱스를 어떻게 같게 하지 ??
				pstmt.setString(2, board.getBlog_filename());
				pstmt.execute();
			}
		} catch (Exception e) {
			System.out.println("writeok 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	//글 수정
	public int blogEdit(HttpServletRequest board) {
		int row = 0;
		
		String blog_no = board.getParameter("blog_no");
		//String id = board.getParameter("id");
		String blog_title = board.getParameter("blog_title");
		String blog_content = board.getParameter("blog_content");
		
		try {
			String sql = "update blog_board set blog_title=?, blog_content=? "
					+ "where blog_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, blog_title);
			pstmt.setString(2, blog_content);
			pstmt.setInt(3, Integer.parseInt(blog_no));
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("blogEdit 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	//글 삭제
	public int deleteOk(int blog_no) {
		int row = 0;
		
		try {
			String sql = "delete from blog_board where blog_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blog_no);
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteOk 예외 : " + e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return row;
	}
	
	//특정 글 댓글 불러오기
	public List<Blog_Reply> getReply(int blog_no) {

		ArrayList<Blog_Reply> replylist = null;
		
		try {
			String sql = "select blog_reply_no, id, refer, depth, step, reply_date, reply_content, del "
					+ "from blog_reply where blog_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blog_no);
			//왜 blogDto에선 blog_no가 int인데 여기선 String이지 .. ??
			//실수였대~int로 바꿨삼 221118 19:04
			
			rs = pstmt.executeQuery();
			replylist = new ArrayList<>();
			
			while(rs.next()) {
				int blog_reply_no = rs.getInt("blog_reply_no");
				String id = rs.getString("id");
				int refer = rs.getInt("refer");
				int depth = rs.getInt("depth");
				int step = rs.getInt("step");
				java.sql.Date reply_date = rs.getDate("reply_date");
				String reply_content = rs.getString("reply_content");
				int del = rs.getInt("del");
				
				Blog_Reply reply = new Blog_Reply(blog_reply_no, blog_no, id, reply_date, reply_content, refer, depth, step, del);
				replylist.add(reply);
			}
			
		} catch (Exception e) {
			System.out.println("getReply 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return replylist;
	}
	
	//댓글 작성
	public int replyWrite(int blog_reply_no, int blog_no, String id, String content){

		int resultrow = 0;
		int maxrefer = getMaxRefer();
		int refer = maxrefer + 1;
		//blog_reply_no를 지금은 받아주고 나중엔 안받아줘도 된다 ? ?
		//원댓글 쓸 땐 blog_reply_no = refer
		try {
			String sql = "insert into blog_reply(blog_reply_no, blog_no, id, refer, reply_content, del)"
					+ " values(blog_reply_no_seq.nextval,?,?,?,?)";
			//blog__reply_no_ 에 seq 추가 (11.21 김태우)
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setInt(1, blog_reply_no);
			pstmt.setInt(1, blog_no);
			pstmt.setString(2, id);
			pstmt.setInt(3, refer);
			pstmt.setString(4, content);
			pstmt.setInt(5, 0); //del
			
			resultrow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("replyWrite 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return resultrow;
	}
	
	//TODO : 댓글 refer 값 생성하기 ?
	private int getMaxRefer() {

		int refer_max = 0;
		
		try {
			String sql = "select nvl(max(refer), 0) from blog_reply";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				refer_max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getMaxRefer 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			}catch (Exception e) {
				
			}
		}
		
		return refer_max;
	}
	//계층형 게시판
	//refer(참조값) , step , depth
	//1. 원본글 : refer 생성?  , step(0) default , depth(0) default
	//2. 답변글 : refer 생성?  , step +1 , depth +1, 현재 읽은 글에 depth + 1
	
	//대댓글 작성
	public int replyRewrite(Blog_Reply reply) {
		int result = 0;

		try {
			int blog_reply_no = reply.getBlog_reply_no(); //현재 댓글의 번호
			//대댓글 작성하려는 원댓글
			String originsql = "select refer, depth, step from blog_reply where blog_reply_no = ?";
			//대댓글 insert 쿼리
			String insertsql = "insert into blog_reply(blog_reply_no, id, refer, depth, step, reply_content, del) "
					+ "values(blog_reply_no_seq.nextval, ?, ?, ?, ?, ?, 0";
			//여기 테이블에 시퀀스가 있나 ?? -> 만들라고 했삼 221120 16:05
			//blog__reply_no_ 에 seq 추가 (11.21 김태우)
			
			pstmt = conn.prepareStatement(originsql);
			pstmt.setInt(1, blog_reply_no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) { //데이터가 존재한다면
				int refer = rs.getInt("refer");
				int step = rs.getInt("step");
				int depth = rs.getInt("depth");
				
				pstmt = conn.prepareStatement(insertsql);
				pstmt.setString(1, reply.getId());
				pstmt.setInt(2, refer);
				pstmt.setInt(3, depth + 1);
				pstmt.setInt(4, step + 1);
				pstmt.setString(5, reply.getReply_content());
				
				int row = pstmt.executeUpdate();
				if(row > 0) {
					result = row;
				}else {
					result = -1;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return result;
	}
	
	
	
	//댓글 수정
	public int replyEdit(HttpServletRequest reply) {
		int row = 0;
		String blog_reply_no = reply.getParameter("blog_reply_no");
		String reply_content = reply.getParameter("reply_content");
		
		try {
			//TODO : 수정 시간 반영하기 (sysdate)
			String sql = "update blog_reply set reply_content=? where blog_reply_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply_content);
			pstmt.setInt(2, Integer.parseInt(blog_reply_no));
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("replyEdit 예외 : " + e.getMessage());
		} finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return row;
	}
	
	//댓글 삭제
	public int replyDelete(int blog_reply_no) {
		int result = 0;

		try {
			//String sql = "delete from blog_reply where blog_reply_no = ?";
			String sql = "update blog_reply set del = 1 where blog_reply_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blog_reply_no);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("replyDelete 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}

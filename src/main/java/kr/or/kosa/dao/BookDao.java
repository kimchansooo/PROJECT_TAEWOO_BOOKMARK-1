package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Book;
import kr.or.kosa.dto.Book_Recommend;
import kr.or.kosa.dto.Book_Reply;
import kr.or.kosa.utils.ConnectionHelper;

public class BookDao implements BookMarkDao{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	public BookDao() {
		conn = null;
		pstmt = null;
		rs = null;
		sql = "";
	}
	
	//책 전체조회
	public List<Book> BookAlllist(int cpage, int pagesize){
		List<Book> booklist = new ArrayList<Book>();
		
		try {
			//sql = "select a.isbn as isbn, book_name, description, price, book_filename, b.file_name as file_name from book a left join ebook b on a.isbn = b.isbn";
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select * from"
				+ "(select rownum, a.isbn as isbn, book_name, description, price, book_filename, b.file_name as file_name from"
				+ "book a join ebook b on a.isbn=b.isbn where rownum<=?)where rownum >=?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize-(pagesize-1);
			int end = cpage*pagesize;
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString(1));
				book.setBook_name(rs.getString(2));
				book.setDescription(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setBook_filename(rs.getString(5));
				if(rs.getString(6) != null) {
					book.setFile_name(rs.getString(6));
				}
				
				booklist.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return booklist;
	}
	//책 전체 count
	public int BookListCount() {
		int count = 0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
			sql = "select count(*) cnt form book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return count;
	}
	//책 like조회
	public List<Book> BookLikeList(String bookname){
		List<Book> booklike = new ArrayList<Book>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select a.isbn as isbn, book_name, description, price, book_filename, b.file_name as file_name from book a left join ebook b on a.isbn = b.isbn where book_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  "%"+bookname+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString(1));
				book.setBook_name(rs.getString(2));
				book.setDescription(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setBook_filename(rs.getString(5));
				if(rs.getString(6) != null) {
					book.setFile_name(rs.getString(6));
				}
				
				booklike.add(book);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return booklike;
	}
	//책 추가
	public int InsertBook(Book book) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "insert into book(isbn, book_name, description, price, book_filename) values(?,?,?,?,?)";
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getIsbn());
			pstmt.setString(2, book.getBook_name());
			pstmt.setString(3, book.getDescription());
			pstmt.setInt(4, book.getPrice());
			pstmt.setString(5, book.getBook_filename());
			
			row = pstmt.executeUpdate();
			
			if(book.getFile_name() != null) {
				sql = "insert into ebook(isbn, file_name) values(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book.getIsbn());
				pstmt.setString(2, book.getFile_name());
				if(pstmt.executeUpdate() > 0) {
					row += 1;
				}
			}
			
			if(row>0) {
				conn.commit();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//책 수정
	public int UpdateBook(Book book) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			conn.setAutoCommit(false);
			sql = "update book set book_name=?, description=?, price=?, book_filename=? where isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBook_name());
			pstmt.setString(2, book.getDescription());
			pstmt.setInt(3, book.getPrice());
			pstmt.setString(4, book.getBook_filename());
			pstmt.setString(5, book.getIsbn());
			
			row = pstmt.executeUpdate();
			
			if(book.getFile_name() != null) {
				sql = "update ebook set file_name=? where isbn=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, book.getFile_name());
				pstmt.setString(2, book.getIsbn());
				
				if(pstmt.executeUpdate() > 0) {
					row +=1;
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//책 삭제
	public int DeleteBook(String isbn) {
		int row = 0;

		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "delete from book where isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//책 상세
	public Book getBookListByIsbn(String isbn) {
		Book book = new Book();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select book_name, description, price, book_filename b.file_name as file_name form book a left join ebook b on a.isbn=b.isbn where a.isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				book.setIsbn(isbn);
				book.setBook_name(rs.getString(1));
				book.setDescription(rs.getString(2));
				book.setPrice(rs.getInt(3));
				book.setBook_filename(rs.getString(4));
				book.setFile_name(rs.getString(5));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return book;
	}
	//책 댓글 전체조회
	public List<Book_Reply> Book_ReplyIsbnList(String isbn){
		List<Book_Reply> brl = new ArrayList<Book_Reply>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select book_reply_no, reply_date, reply_content, id from book_reply where isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book_Reply br = new Book_Reply();
				
				br.setBook_reply_no(rs.getInt(1));
				br.setIsbn(isbn);
				br.setReply_date(rs.getDate(2));
				br.setReply_content(rs.getString(3));
				br.setId(rs.getString(4));
				
				brl.add(br);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return brl;
	}
	
	//관리자용 책 댓글 전체조회
		public List<Book_Reply> Book_ReplyAllList(){
			List<Book_Reply> brl = new ArrayList<Book_Reply>();
			
			try {
				conn = ConnectionHelper.getConnection("orcle");
				sql = "select book_reply_no, reply_date, reply_content, id, isbn from book_reply";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Book_Reply br = new Book_Reply();
					
					br.setBook_reply_no(rs.getInt(1));
					br.setReply_date(rs.getDate(2));
					br.setReply_content(rs.getString(3));
					br.setId(rs.getString(4));
					br.setIsbn(rs.getString(5));
					
					brl.add(br);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					ConnectionHelper.close(rs);
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
			return brl;
		}
		
	//책 댓글 like조회
	public List<Book_Reply> Book_ReplyLikeList(String id){
		List<Book_Reply> brl = new ArrayList<Book_Reply>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select book_reply_no, reply_date, reply_content, isbn from book_reply where id =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book_Reply br = new Book_Reply();
				br.setBook_reply_no(rs.getInt(1));
				br.setIsbn(rs.getString(2));
				br.setReply_date(rs.getDate(3));
				br.setReply_content(rs.getString(4));
				br.setId(id);
				
				brl.add(br);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return brl;
	}
	//책 댓글쓰기
	public int InsertBook_Reply(Book_Reply br) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "insert into book_reply(book_reply_no, isbn, reply_content, id)" +
				  "values(book_reply_no_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, br.getIsbn());
			pstmt.setString(2, br.getReply_content());
			pstmt.setString(3, br.getId());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//책 댓글 수정
	public int UpdateBook_Reply(Book_Reply br) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "update book_reply set reply_date=sysdate, reply_content=? where book_reply_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, br.getReply_content());
			pstmt.setInt(2, br.getBook_reply_no());
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//책 댓글 삭제
	public int DeleteBook_Reply(int no) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "delete book_reply where book_reply_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	//책 좋아요 추가
	public int Book_Like(String isbn, String id) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select id, isbn from book_like where isbn=? and id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "delete book_like where isbn=? and id=?";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, isbn);
				pstmt.setString(2, id);
				
				if(pstmt.executeUpdate() > 0) {
					row = 1;
				}else {
					row = -1;
				}
			}else {
				sql = "insert into book_like(isbn, id, like_date) values(?,?,sysdate)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, isbn);
				pstmt.setString(2, id);
				
				if(pstmt.executeUpdate() > 0) {
					row = 2;
				}else {
					row = -1;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(rs);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//좋아요 순 순위 조회
	public List<Book> RankBook_Like(java.util.Date startdate, java.util.Date enddate){
		List<Book> hmr = new ArrayList<Book>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select isbn, count(isbn) as likecount from book_like where like_date between ? and ? group by isbn order by 2 desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (Date)startdate);
			pstmt.setDate(2, (Date)enddate);
			rs = pstmt.executeQuery();
			
			for(int i = 10; i>=1; i--) {
				if(!rs.next()) {
					break;
				}else {
					Book book = getBookListByIsbn(rs.getString(1));
					hmr.add(book);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return hmr;
	}
	//e-book리스트 조회
	public List<Book> EbookList(String id){
		List<Book> ebl = new ArrayList<Book>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select a.isbn as isbn, book_name, description, price, book_filename, b.file_name as file_name from book a join ebook b on a.isbn = b.isbn where isbn in (select isbn from ebook_list where id = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString(1));
				book.setBook_name(rs.getString(2));
				book.setDescription(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setBook_filename(rs.getString(5));
				book.setFile_name(rs.getString(6));
				
				ebl.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ebl;
	}
	//e-book리스트 추가
	public int InsertEbookList(String isbn, String id) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "insert into ebook_list(id, isbn) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, id);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//추천 책 추가
	public int InsertBook_Recommend(String isbn, String recommend_content) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "insert into book_recommend(isbn, recommend_content) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, recommend_content);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return row;
	}
	//추천 책 삭제
	public int DeleteBook_Recommend(String isbn) {
		int row = 0;
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "delete book_recommend where isbn=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//추천책 리스트 조회
	public List<Book_Recommend> Book_RecommendList(){
		List<Book_Recommend> bl = new ArrayList<Book_Recommend>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select a.isbn as isbn, book_name, description, price, book_filename, b.recommend_content as recommend_content from book a join book_recommend b on a.isbn = b.isbn";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString(1));
				book.setBook_name(rs.getString(2));
				book.setDescription(rs.getString(3));
				book.setPrice(rs.getInt(4));
				book.setBook_filename(rs.getString(5));
				
				Book_Recommend br = new Book_Recommend(book, rs.getString(6));
				bl.add(br);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return bl;
	}
	//책 매출 순위조회
	public List<Book> SellBookList(java.util.Date start, java.util.Date end){
		List<Book> hmr = new ArrayList<Book>();
		
		try {
			conn = ConnectionHelper.getConnection("orcle");
			sql = "select a.isbn, count(b.isbn) as likecount from book a join book_payment b on a.isbn=b.isbn where payment_date between ? and ? group by a.isbn order by 2 desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, (Date) start);
			pstmt.setDate(2, (Date) end);
			
			rs = pstmt.executeQuery();
			
			for(int i = 10; i>=1; i--) {
				if(!rs.next()) {
					break;
				}else {
					Book book = getBookListByIsbn(rs.getString(1));
					hmr.add(book);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return hmr;
	}
}

package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kr.or.kosa.dto.Book;
import kr.or.kosa.dto.Book_Payment;
import kr.or.kosa.utils.ConnectionHelper;

public class PaymentDao implements BookMarkDao{
	
	

		public List<Book> cartlist(String id){ //장바구니 리스트
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<Book> cartlist = null;
			
			try {
				String sql = "select book.isbn as isbn, book.book_name, book.description,book.price,book.book_filename,ebook.file_name from book left join ebook on book.isbn = ebook.isbn where book.isbn in (select isbn from cart where id =?)";
				//일단은 ebook파일까지 같이 담기도록 짰다.
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				cartlist = new ArrayList<Book>();
				while(rs.next()) {
					Book book = new Book();
					book.setIsbn(rs.getString("isbn"));
					book.setBook_name(rs.getString("book_name"));
					book.setDescription(rs.getString("description"));
					book.setFile_name(rs.getString("file_name"));
					book.setPrice(rs.getInt("price"));
					book.setBook_filename(rs.getString("book_filename"));
					
					cartlist.add(book);
				}
			} catch (Exception e) {
				e.getStackTrace();
			}finally {
				try {
					ConnectionHelper.close(rs);
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				} catch (Exception e2) {
					
				}
			}
			return cartlist;
		}
		public int AddBook(String id, String isbn) {
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int row = 0;
			
			try {
				String sql = "insert into cart(id, isbn) values(?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,id);
				pstmt.setString(2,isbn);
				row = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.getStackTrace();
			}finally {
				try {
					ConnectionHelper.close(rs);
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return row;
		}

		public int deleteOk(String id, String isbn) { //장바구니 목록에서 지우기
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int row = 0;
				
			try {
				String sql = "delete from cart where id=? and isbn=?";
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2,isbn);
				row = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.getStackTrace();
			}finally {
				try {
					ConnectionHelper.close(rs);
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				} catch (Exception e2) {
				
				}
			}
			
			return row;
		}
		//유저 한명의 결제목록
		public List<Book_Payment> paymentlist(String id){
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<Book_Payment> paymentlist = null;
			
			try {
				String sql = "Select book_payment.payment_no,isbn,count,payment_date,sumprice from book_payment join payment on book_payment.payment_no = payment.payment_no where payment.id = ?";
				pstmt.setString(1, id);
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				paymentlist = new ArrayList<Book_Payment>();
				while(rs.next()) {
					Book_Payment bookpayment = new Book_Payment();
					bookpayment.setPayment_no(rs.getString("payment_no"));
					bookpayment.setIsbn(rs.getString("isbn"));
					bookpayment.setCount(rs.getInt("count"));
					bookpayment.setPayment_date(rs.getDate("payment_date"));
					bookpayment.setSumprice(rs.getInt("sumprice"));
					
					paymentlist.add(bookpayment);
					
				}
			} catch (Exception e) {
				e.getStackTrace();
			}finally {
				try {
					ConnectionHelper.close(rs);
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				} catch (Exception e2) {
				
				}
			}
			return paymentlist;
		} 
		//전체 결제 목록
		public List<Book_Payment> allpaymentlist(int cpage , int pagesize){
			Connection conn = ConnectionHelper.getConnection("oracle");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<Book_Payment> allpaymentlist = new ArrayList<Book_Payment>();
			
			try {
				String sql = "select * from"
						+ "    (select rownum rn,payment_no, isbn, count,payment_date,sumprice"
						+ "    from"
						+ "        ( SELECT * FROM book_payment ORDER BY payment_no asc )"
						+ "    where rownum <= ?) where rn >= ?";
				pstmt = conn.prepareStatement(sql);
				
				int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
				int end = cpage * pagesize; // 1 * 5 >> 5
				
				pstmt.setInt(1, end);
				pstmt.setInt(2, start);
				rs = pstmt.executeQuery();
				
				
				while(rs.next()) {
					Book_Payment bookpayment = new Book_Payment();
					bookpayment.setPayment_no(rs.getString("payment_no"));
					bookpayment.setIsbn(rs.getString("isbn"));
					bookpayment.setCount(rs.getInt("count"));
					bookpayment.setPayment_date(rs.getDate("payment_date"));
					bookpayment.setSumprice(rs.getInt("sumprice"));
					
					allpaymentlist.add(bookpayment);
					
				}
				
			} catch (Exception e) {
				e.getStackTrace();
			}finally {
				try {
					ConnectionHelper.close(rs);
					ConnectionHelper.close(pstmt);
					ConnectionHelper.close(conn);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
			return allpaymentlist;
		}
		
		
}
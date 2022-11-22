package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Popup;
import kr.or.kosa.utils.ConnectionHelper;

public class PopupDao {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	public PopupDao() {
		conn=ConnectionHelper.getConnection("oracle");
		pstmt=null;
		rs=null;
		sql="";
	}
	//공지사항 전체조회
	public List<Popup> AllListPopup(int cpage , int pagesize){
		List<Popup> pl = new ArrayList<Popup>();
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "select rownum popup_no, id, popup_title, popup_filename, popup_date from popup where rownum between ? and ?";
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
		
			
			while(rs.next()) {
				Popup pu = new Popup();
				pu.setPopup_no(rs.getInt(1));
				pu.setId(rs.getString(2));
				pu.setPopup_title(rs.getString(3));
				pu.setPopup_filename(rs.getString(4));
				pu.setPopup_date(rs.getDate(5));
				
				pl.add(pu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return pl;
	}
	//
	public int totalPopupCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");//연결객체
			String sql = "select count(*) as cnt from popup";
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
	//공지사항 조건조회(title like조회)
	public List<Popup> LikeListPopup(String title){
		List<Popup> pl = new ArrayList<Popup>();
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "select popup_no, id, popup_title, popup_filename, popup_date from popup where popup_title like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+title+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Popup pu = new Popup();
				pu.setPopup_no(rs.getInt(1));
				pu.setId(rs.getString(2));
				pu.setPopup_title(rs.getString(3));
				pu.setPopup_filename(rs.getString(4));
				pu.setPopup_date(rs.getDate(5));
				
				pl.add(pu);
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
		
		return pl;
	}
	//공지사항 등록
	public int InsertPopup(Popup popup) {
		int row = 0;
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "insert into popup(popup_no, id, popup_title, popup_filename, popup_date) values(popup_no_seq.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, popup.getId());
			pstmt.setString(2, popup.getPopup_title());
			pstmt.setString(3, popup.getPopup_filename());
			pstmt.setDate(4, (Date) popup.getPopup_date());
			
			row= pstmt.executeUpdate();
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
	//공지사항 삭제
	public int DeletePopup(int no) {
		int row = 0;
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "delete from popup where popup_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
						
			row= pstmt.executeUpdate();
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
	//공지사항 수정
	public int UpdatePopup(Popup popup) {
		int row = 0;
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "update popup set popup_title=?, popup_filename=?, popup_date=? where popup_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, popup.getPopup_title());
			pstmt.setString(2, popup.getPopup_filename());
			pstmt.setDate(3, (Date) popup.getPopup_date());
			pstmt.setInt(4, popup.getPopup_no());
			
			row= pstmt.executeUpdate();
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
	//공지사항 상세보기(관리용)
	public Popup DetailPopup(int no) {
		Popup pu = new Popup();
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "select popup_no, id, popup_title, popup_filename, popup_date from popup where popup_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pu.setPopup_no(rs.getInt(1));
				pu.setId(rs.getString(2));
				pu.setPopup_title(rs.getString(3));
				pu.setPopup_filename(rs.getString(4));
				pu.setPopup_date(rs.getDate(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return pu;
	}
	//공지사항 필터링
	//select * from popup where popupdate > sysdate
	public List<Popup> FilterPopup(){
		List<Popup> pl = new ArrayList<Popup>();
		
		try {
			conn=ConnectionHelper.getConnection("oracle");
			sql = "select popup_no, id, popup_title, popup_filename, popup_date from popup where TO_CHAR(popup_date, 'YYYY-MM-DD HH24:MI:SS')>to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS')";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Popup po = new Popup();
				po.setPopup_no(rs.getInt(1));
				po.setId(rs.getString(2));
				po.setPopup_title(rs.getString(3));
				po.setPopup_filename(rs.getString(4));
				po.setPopup_date(rs.getDate(5));
				
				pl.add(po);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(conn);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			try {
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return pl;
	}
	/*
	 * 1 ~22.11.30 10:10:01
	 * 2 ~10.1
	 * 
	 * 
	 * 
	 * */
	
	
	
}

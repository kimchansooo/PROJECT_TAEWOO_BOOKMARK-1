package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import kr.or.kosa.dto.Calendar;
import kr.or.kosa.utils.ConnectionHelper;

public class CalendarDao implements BookMarkDao{

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	 
	public CalendarDao() {
		conn = ConnectionHelper.getConnection("oracle");
		pstmt = null;
		rs = null;
		sql = "";
		
	}
	
	//일정 전체조회
	public List<Calendar> CalendarAlllist(){
		List<Calendar> calendaralllist = new ArrayList<Calendar>();
		
		try {
			sql = "select calendar_no, id, calendar_start, calendar_end, calendar_content, calendar_status from calendar";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Calendar calendar = new Calendar();
				calendar.setCalendar_no(rs.getInt(1));
				calendar.setId(rs.getString(2));
				calendar.setCalendar_start(rs.getDate(3));
				calendar.setCalendar_end(rs.getDate(4));
				calendar.setCalendar_content(rs.getString(5));
				calendar.setCalendar_status(rs.getInt(6));
				
				calendaralllist.add(calendar);
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
		
		return calendaralllist;
	}
	//일정 조건조회
	public List<Calendar> CalendarLikeList(String content){
		List<Calendar> calendaralllist = new ArrayList<Calendar>();
		
		try {
			sql = "select calendar_no, id, calendar_start, calendar_end, calendar_content, calendar_status from calendar where calendar_content like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+content+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Calendar calendar = new Calendar();
				calendar.setCalendar_no(rs.getInt(1));
				calendar.setId(rs.getString(2));
				calendar.setCalendar_start(rs.getDate(3));
				calendar.setCalendar_end(rs.getDate(4));
				calendar.setCalendar_content(rs.getString(5));
				calendar.setCalendar_status(rs.getInt(6));
				
				calendaralllist.add(calendar);
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
		
		return calendaralllist;
	}
	//일정 추가
		public int CalendarAdd(Calendar calendar){
			int row = 0;
			
			try {
				sql = "insert into Calendar(calendar_no, id, calendar_start, calendar_end, calendar_content, calendar_status) values(calendar_no_seq.nextval, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, calendar.getId());
				pstmt.setDate(2, (Date) calendar.getCalendar_start());
				pstmt.setDate(3, (Date) calendar.getCalendar_end());
				pstmt.setString(4, calendar.getCalendar_content());
				pstmt.setInt(5, calendar.getCalendar_status());
				
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
	//일정 수정
		public int CalendarUpdate(Calendar calendar){
			int row = 0;
			
			try {
				sql = "update calendar set calendar_start=?, calendar_end=?, calendar_content=?, calendar_status=? where calendar_no =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(5, calendar.getCalendar_no());
				pstmt.setDate(1, (Date) calendar.getCalendar_start());
				pstmt.setDate(2, (Date) calendar.getCalendar_end());
				pstmt.setString(3, calendar.getCalendar_content());
				pstmt.setInt(4, calendar.getCalendar_status());
				
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
	//일정 삭제
		public int CalendarDelete(int no){
			int row = 0;
			
			try {
				sql = "delete calendar where calendar_no =?";
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
	//일정 상세조회
		public Calendar CalendarDetail(int calendar_no){
			Calendar calendar = new Calendar();
			try {
				sql = "select calendar_no, id, calendar_start, calendar_end, calendar_content, calendar_status from calendar where calendar_no = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, calendar_no);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					calendar.setCalendar_no(rs.getInt(1));
					calendar.setId(rs.getString(2));
					calendar.setCalendar_start(rs.getDate(3));
					calendar.setCalendar_end(rs.getDate(4));
					calendar.setCalendar_content(rs.getString(5));
					calendar.setCalendar_status(rs.getInt(6));
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
			
			return calendar;
		}
}
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
import javax.sql.DataSource;

import kr.or.kosa.dto.Users;
import kr.or.kosa.utils.ConnectionHelper;

public class UsersDao implements BookMarkDao{
	//로그인
	//select id, password from users where userid='[value]'
	//아이디 틀림 0 | 비밀번호 틀림 1 | 성공 2 | 관리자 3
	public int userLogin(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select id, password,state from users where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//아이디 ㅇ
				  result = 1;
				  if(password.equals(rs.getString(2))) {
					  result = 2;
					  if(rs.getInt(3) == 1) {
						  result = 3;
					  }
				  }
			  }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	//회원가입
	//insert into users(...) values(...)
	public int userRegister(Users user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			conn.setAutoCommit(false);
			String sql = "insert into users(id,password,name)"
					+ "values(?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			
			row = pstmt.executeUpdate();
			
			if(row != 0) {
				sql = "insert into user_detail(id,addr,detail_addr,regist_no,phone) value(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getId());
				pstmt.setString(2,user.getAddr());
				pstmt.setString(3, user.getDetail_addr());
				pstmt.setString(4, user.getRegist_no());
				pstmt.setString(5, user.getPhone());
				
				row = pstmt.executeUpdate();
				
				if(row != 0) { //user, user_detail 모두 성공하면 commit;
					conn.commit();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return row;
	}
	//회원정보수정
	//update users set pwd='...', nickname='....'
	public boolean updateUser(Users user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			conn.setAutoCommit(false);
			String sql="update users set password=?,name=? where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getId());
			
			int row = pstmt.executeUpdate();
			
			if(row > 0 ) {
				//users 테이블 성공 했을 경우
				result = true;
				sql = "update user_detail set addr=?,detail_addr=?, regist_no=?,phone=? where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user.getAddr());
				pstmt.setString(2, user.getDetail_addr());
				pstmt.setString(3, user.getRegist_no());
				pstmt.setString(4, user.getPhone());
				pstmt.setString(5, user.getId());
				
				row = pstmt.executeUpdate();
				if(row > 0) {
					//user_detail  테이블까지 업데이트 되었을 경우
					result = true;
					conn.commit();
				}
			}
			
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				result=false;
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	//회원삭제
	//delete from users where userid='';
	public boolean deleteUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql="delete from users where id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
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
	//회원전체리스트조회
	//select * from users
	public List<Users> getUserAllList(int cpage , int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Users> list = new ArrayList<Users>();
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select  rn, id, password, name, state, addr, detail_addr, regist_no, phone from"
					+ " (select rownum rn, u.id, u.password, u.name, u.state, d.addr, d.detail_addr, d.regist_no, d.phone"
						+ " from users u left join user_detail d on u.id = d.id"
						+ " where rownum <= ?)"
					+ " where rn > ?";
			
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Users user = new Users();
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setState(rs.getInt(4));
				user.setAddr(rs.getString(5));
				user.setDetail_addr(rs.getString(6));
				user.setRegist_no(rs.getString(7));
				user.setPhone(rs.getString(8));
				list.add(user);
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
	//회원like조회
	//select * from users where [type] like '[value]'
	public List<Users> getUserListByLike(String type, String value){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Users> list = new ArrayList<Users>();
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select "
					+ "u.id, u.password, u.name, u.state, d.addr, "
					+ "d.detail_addr, d.regist_no, d.phone "
					+ "from users u left join user_detail d on u.id = d.id"
					+ "? like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, type);
			pstmt.setString(2,"%"+ value+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Users user = new Users();
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setState(rs.getInt(4));
				user.setAddr(rs.getString(5));
				user.setDetail_addr(rs.getString(6));
				user.setRegist_no(rs.getString(7));
				user.setPhone(rs.getString(8));
				list.add(user);
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
	//회원 조회
	//select * from users where id=[아이디]
	public Users getUserById(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Users user = new Users();
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select "
					+ "u.id, u.password, u.name, u.state, d.addr, "
					+ "d.detail_addr, d.regist_no, d.phone "
					+ "from users u left join user_detail d on u.id = d.id"
					+ "where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user.setId(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setName(rs.getString(3));
				user.setState(rs.getInt(4));
				user.setAddr(rs.getString(5));
				user.setDetail_addr(rs.getString(6));
				user.setRegist_no(rs.getString(7));
				user.setPhone(rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			ConnectionHelper.close(conn);
		}
		return user;
	}
	//id 체크(비동기 아이디 중복체크 기능에 사용)
	//select id from users where id='[value]'
	public boolean userIdCk(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = ConnectionHelper.getConnection("oracle");
			String sql = "select id from users where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(rs);
			ConnectionHelper.close(conn);
		}
		return result;
	}
}	










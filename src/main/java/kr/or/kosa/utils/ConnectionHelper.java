package kr.or.kosa.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionHelper {
	public static Connection getConnection(String dsn) {
		//Oracle, MySql 두개의 DB를 사용한다는 전제하에...
		
		if(dsn.toLowerCase().equals("oracle")) {
			try {
				Context context = new InitialContext();
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
				Connection conn = ds.getConnection();
				return conn;
			} catch (Exception e) {
				System.out.println("Connection 예외 발생...");
				e.printStackTrace();
				return null;
			}
		}else if(dsn.toLowerCase().equals("mysql")) {
			try {
				  Class.forName("oracle.jdbc.OracleDriver");
				  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE");
				  return conn;
			} catch (Exception e) {
				
			}
		}
		return null;
	}

    public static void close(Connection conn) {

		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void close(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}

<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomcat Connection Pool 사용하기</title>
</head>
<body>
	<%
		Connection conn = null;
		
		//JNDI
		Context context = new InitialContext();
		//현재 프로젝트에서 특정 이름을 가진 객체를 검색해서 사용 가능 (이름 기반 검색)
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle"); //이름찾기 - 정해진 약속 >> java:comp/env/[원하는이름] >> java:comp/env/jdbc/oracle
		//lookup 된 객체의 타입이 DataSource >> Object 형태로 return >> 다운캐스트 해야된다
	
		//ds >> 튜브를 가지고 있는 가계의 주소
		
		conn = ds.getConnection();
		
		out.print("db 연결 여부 : " + !conn.isClosed() + "<br>");
		
		out.print("연결 해제...<br>");
		
		conn.close();
		
		out.print("db 연결 여부 : " + !conn.isClosed() + "<br>");
		
		//POOL 객체의 scope >> method로 한정해야
	%>
</body>
</html>
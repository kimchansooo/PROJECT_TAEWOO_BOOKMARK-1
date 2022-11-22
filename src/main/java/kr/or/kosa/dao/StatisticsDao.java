package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosa.dto.Statistics;
import kr.or.kosa.utils.ConnectionHelper;

public class StatisticsDao implements BookMarkDao {

/* 책결제 */
//CREATE TABLE BOOK_PAYMENT (
//	PAYMENT_NO NUMBER NOT NULL, /* 결제번호 */
//	ISBN VARCHAR2(17) NOT NULL, /* 책코드 */
//	COUNT NUMBER NOT NULL, /* 수량 */
//	PAYMENT_DATE DATE DEFAULT SYSDATE NOT NULL, /* 결제일자 */
//	SUMPRICE NUMBER NOT NULL /* 총가격 */
//);
//
//CREATE UNIQUE INDEX PK_BOOK_PAYMENT
//	ON BOOK_PAYMENT (
//		PAYMENT_NO ASC,
//		ISBN ASC
//	);
//
//ALTER TABLE BOOK_PAYMENT
//	ADD
//		CONSTRAINT PK_BOOK_PAYMENT
//		PRIMARY KEY (
//			PAYMENT_NO,
//			ISBN
//		);
	/* 회원 */
//	CREATE TABLE USERS (
//		ID VARCHAR2(50) NOT NULL, /* 아이디 */
//		PASSWORD VARCHAR2(255) NOT NULL, /* 비밀번호 */
//		NAME VARCHAR2(50) NOT NULL, /* 이름 */
//		NICKNAME VARCHAR2(255) NOT NULL, /* 별명 */
//		STATE NUMBER DEFAULT 0 NOT NULL /* 관리자여부확인 */
//	);
//
//	CREATE UNIQUE INDEX PK_USERS
//		ON USERS (
//			ID ASC
//		);
//
//	ALTER TABLE USERS
//		ADD
//			CONSTRAINT PK_USERS
//			PRIMARY KEY (
//				ID
//			);
	/* 회원세부 */
//	CREATE TABLE USER_DETAIL (
//		ID VARCHAR2(50) NOT NULL, /* 아이디 */
//		ADDR VARCHAR2(255) NOT NULL, /* 주소 */
//		DETAIL_ADDR VARCHAR2(255) NOT NULL, /* 상세주소 */
//		REGIST_NO VARCHAR2(13) NOT NULL, /* 주민번호 */
//		PHONE VARCHAR2(30) NOT NULL /* 휴대폰번호 */
//	);
//
//	CREATE UNIQUE INDEX PK_USER_DETAIL
//		ON USER_DETAIL (
//			ID ASC
//		);
//
//	ALTER TABLE USER_DETAIL
//		ADD
//			CONSTRAINT PK_USER_DETAIL
//			PRIMARY KEY (
//				ID
//			);
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	public StatisticsDao(){
		try {
			conn = ConnectionHelper.getConnection("orcle");
			pstmt = null;
			rs = null;
			sql = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//남녀 성비 통계
	public Statistics genderStatistics(String gender) {

		Statistics femaleStatistics = null;
		Statistics maleStatistics = null;
		//주민번호 예 : 9802112******
		//마지막 자리만 받아서 2, 4면 여자 / 1, 3이면 남자
		//모든 컬럼의 성별 번호를 받아서 배열에 넣고
		//for문 돌면서 각 성별에 맞게 변수 + 1
		
		List<String> genderArr = new ArrayList<>();
		int male = 0;
		int female = 0;
		
		try {
			String sql = "select substr(regist_no, -1) as num from user_detail";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				genderArr.add(rs.getString("num")); //TODO:이렇게 써도 되는거지 ??
			}
			for(String s : genderArr) {
				if(s.equals("2") || s.equals("4")) {
					female++;
				}else if(s.equals("1") || s.equals("3")) {
					male++;
				}
			}
			
			femaleStatistics = new Statistics("여성", female);
			maleStatistics = new Statistics("남성", male);
			
			
		} catch (Exception e) {
			System.out.println("genderStatics 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
			}
		}
		
		if(gender.equals("male")) {
			return maleStatistics;
		}else if(gender.equals("female")) {
			return femaleStatistics;
		}
		return null;
	}
	
	//남녀성비 return 리스트로
	public List<Statistics> getGender(){
		
		List<Statistics> genderArr = new ArrayList<>();
		List<String> gender = new ArrayList<>();
		
		Statistics femaleStatistics = null;
		Statistics maleStatistics = null;

		int male = 0;
		int female = 0;
		
		try {
			String sql = "select substr(regist_no, -1) as num from user_detail";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				gender.add(rs.getString("num")); //TODO:이렇게 써도 되는거지 ??
			}
			for(String s : gender) {
				if(s.equals("2") || s.equals("4")) {
					female++;
				}else if(s.equals("1") || s.equals("3")) {
					male++;
				}
			}
			
			femaleStatistics = new Statistics("여성", female);
			maleStatistics = new Statistics("남성", male);
			genderArr.add(femaleStatistics);
			genderArr.add(maleStatistics);
			
			
		} catch (Exception e) {
			System.out.println("getGender 예외 : " + e.getMessage());
		}finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
			}
		}
		
		return genderArr;
	}
	/*
	 private int getAge(int birthYear, int birthMonth, int birthDay)
	 {
	         Calendar current = Calendar.getInstance();
	        
	         int currentYear  = current.get(Calendar.YEAR);
	         int currentMonth = current.get(Calendar.MONTH) + 1;
	         int currentDay   = current.get(Calendar.DAY_OF_MONTH);
	         
	         System.out.println("현재 년 : "+currentYear);
	         System.out.println("현재 월 : "+currentMonth);
	         System.out.println("현재 일 : "+currentDay);
	         
	         // 만 나이 구하기 2022-1995=27 (현재년-태어난년)
	         int age = currentYear - birthYear;
	         // 만약 생일이 지나지 않았으면 -1
	         if (birthMonth * 100 + birthDay > currentMonth * 100 + currentDay) 
	             age--;
	         // 5월 26일 생은 526
	         // 현재날짜 5월 25일은 525
	         // 두 수를 비교 했을 때 생일이 더 클 경우 생일이 지나지 않은 것이다.
	         return age;
	 }

	//연령 비율 통계
	public List<Statistics> getAge() {
		List<Statistics> ageArr = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int child = 0; //10세미만
		int teen = 0; //10대
		int twenty = 0; //20대
		int thirty = 0; //30대
		int fourty = 0; //40대
		int fifty = 0; //50대
		int senior = 0; //60대 이상
		
		
		try {
			conn = ds.getConnection();
			String sql = "select substr(regist_no, 0, 6) as birthdate from user_detail";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int age = 0;
			
			int birthyear;
			int birthmonth;
			int birthday;
			
			//980211 <- 이 String에서 year, month, day를 int형으로 얻어야 함
			while(rs.next()) {
				String birthdate = rs.getString("birthdate");
				int dummyyear = Integer.parseInt(birthdate.substring(0, 2));
				birthmonth = Integer.parseInt(birthdate.substring(2, 4));
				birthday = Integer.parseInt(birthdate.substring(4));
				
				//연도 계산
				if(dummyyear < 23) {
					birthyear = 2000 + dummyyear;
				}else {
					birthyear = 1900 + dummyyear;
				}
				
				//만나이 계산
				age = getAge(birthyear, birthmonth, birthday);
				int dummyage = age / 10;
				if(dummyage == 0) {
					child++;
				}else if(dummyage == 1) {
					teen++;
				}else if(dummyage == 2) {
					twenty++;
				}else if(dummyage == 3) {
					thirty++;
				}else if(dummyage == 4) {
					fourty++;
				}else if(dummyage == 5) {
					fifty++;
				}else {
					senior++;
				}
				
				
			}
			Statistics childStatistics = new Statistics("어린이", child);
			Statistics teenStatistics = new Statistics("10대", teen);
			Statistics twentyStatistics = new Statistics("20대", twenty);
			Statistics thirtyStatistics = new Statistics("30대", thirty);
			Statistics fourtyStatistics = new Statistics("40대", fourty);
			Statistics fiftyStatistics = new Statistics("50대", fifty);
			Statistics seniorStatistics = new Statistics("그 이상", senior);
			
			ageArr.add(seniorStatistics);
			ageArr.add(twentyStatistics);
			ageArr.add(teenStatistics);
			ageArr.add(fiftyStatistics);
			ageArr.add(fourtyStatistics);
			ageArr.add(thirtyStatistics);
			ageArr.add(childStatistics);
			
		} catch (Exception e) {
			System.out.println("getAge 예외 : " + e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		
		return ageArr;
	}
	*/
	
	//연령 통계 쿼리로
	public List<Statistics> getAgeQuery(){
		List<Statistics> ageArr = new ArrayList<>();

		try {
			String sql = "select 세대, count(세대) as count from (select "
					+ "case when y between 0 and 10 then 0 "
					+ "when y between 11 and 20 then 10 "
					+ "when y between 21 and 30 then 20 "
					+ "when y between 31 and 40 then 30 "
					+ "when y between 41 and 50 then 40 "
					+ "when y between 51 and 60 then 50 "
					+ "else 60 "
					+ "end as 세대 "
					+ "from (select extract(year from sysdate) - extract(year from d) as y "
					+ "from test1120)) group by 세대 order by 세대 asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String generation = rs.getString("세대");
				int count = rs.getInt("count");
				
				ageArr.add(new Statistics(generation, count));
			}
		} catch (Exception e) {
			System.out.println("getAgeQuery 예외 : " + e.getMessage());
		} finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		return ageArr;
	}
	
	//일별 매출 통계
	public List<Statistics> dailySales(){
		
		List<Statistics> dailyarr = new ArrayList<>();
		
		try {
			String sql = "select substr(payment_date, 0, 10) as daily, sum(sumprice) as total "
					+ "from book_payment group by substr(payment_date, 0, 10)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String date = rs.getString("daily"); //TODO: payment_date는 string인데 이렇게 해도 받아와지나?
				int total = rs.getInt("total");
				
				dailyarr.add(new Statistics(date, total));
			}
			
		} catch (Exception e) {
			System.out.println("dailySales 예외 : " + e.getMessage());
		} finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {

			}
		}
		
		return dailyarr;
	}
	
	//date_format='YYYY-MM-DD HH24:MI:SS';
	//월별 매출 통계
	public List<Statistics> monthlySales(){
		List<Statistics> monthlyarr = new ArrayList<>();
		
		try {
			String sql = "select substr(payment_date, 0, 7) as monthly, sum(sumprice) as total "
					+ "from book_payment group by substr(payment_date, 0, 7)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String dummymonth = rs.getString("monthly");
				int total = rs.getInt("total");
				//month : 2022-11
				String year = dummymonth.substring(0,4);
				String month = dummymonth.substring(5);
				
				monthlyarr.add(new Statistics(year + "년 " + month + "월", total));
				
			}
			
		} catch (Exception e) {
			System.out.println("monthlySales 예외 : " + e.getMessage());
		} finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return monthlyarr;
	}
	
	//이번 주 매출 통계
	public List<Statistics> curWeekSales(){
		List<Statistics> curWeekarr = new ArrayList<>();

		try {
			String sql = "SELECT"
					+ "to_char(TRUNC(payment_date, 'iw'),'YYYY.MM.DD')|| ' - ' || to_char(sysdate, 'YYYY.MM.DD') AS weekly,"
					+ "    sum(sumprice) AS total "
					+ "FROM book_payment "
					+ "group by TRUNC(payment_date, 'iw') "
					+ "having TRUNC(payment_date, 'iw') > sysdate - 7";
			
			pstmt = conn.prepareCall(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String week = rs.getString("weekly");
				int total = rs.getInt("total");
				
				curWeekarr.add(new Statistics(week, total));
			}
			
		} catch (Exception e) {
			System.out.println("curWeekSales 예외 : " + e.getMessage());
		} finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {

			}
		}
		
		return curWeekarr;
	}
	
	
	//연간 매출 통계 ....
	public List<Statistics> yearlySales(){
		List<Statistics> yearlyarr = new ArrayList<>();
		
		try {
			String sql = "select substr(payment_date, 0,4) as yearly, sum(sumprice) as total "
					+ "from book_payment group by substr(payment_date, 0, 4)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String year = rs.getString("yearly");
				int total = rs.getInt("total");
				
				yearlyarr.add(new Statistics(year, total));
				
			}
			
		} catch (Exception e) {
			System.out.println("yearlySales 예외 : " + e.getMessage());
		} finally {
			try {
				ConnectionHelper.close(rs);
				ConnectionHelper.close(pstmt);
				ConnectionHelper.close(conn);
			} catch (Exception e2) {
				
			}
		}
		
		return yearlyarr;
	}
	
	//검색 기간 매출 조회
	public List<Statistics> searchList(String date1, String date2){ //TODO : 파라미터 date? string?
		List<Statistics> searchArr = new ArrayList<>();
		
		try {
			String sql = "select sumprice "
					+ "from book_payment "
					+ "where payment_date"
					+ " between to_date(?, 'YYYY-MM-DD') and to_date(?, 'YYYY-MM-DD') +1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){

				do{
					//결과 있을 때 내용...
					int total = rs.getInt("sumprice"); 
					searchArr.add(new Statistics("조회 결과", total));
					
				}while(rs.next());
				}else{
					// TODO: 결과 없을 때 내용..
					System.out.println("검색 결과 없음");
					

				}
			
		} catch (Exception e) {
			System.out.println("searchList 예외 : " + e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		
		return searchArr;
	}
	
}

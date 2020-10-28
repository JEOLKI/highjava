package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class T01_JdbcTest {
/*
	JDBC를 이용한 데이터베이스 처리 순서
	
	순서 : JDBC드라이버 로딩 -> 해당 DB에 접속 -> 질의(SQL명령을 수행)
		 -> 질의결과를 받아서 처리한다. -> 종료(자원반납)
*/	
	public static void main(String[] args) {
		// DB 작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; // 쿼리문이 select인 경우에 사용
		
		try {
			
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB에 접속 (Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String user = "HJG";
			String password = "java";
			
			conn = DriverManager.getConnection(url, user, password);
			
			// 3. Statement객체 생성 => Connection 객체를 이용한다.
			stmt = conn.createStatement();
			
			// 4. SQL문을 Statement객체를 이용하여 실행하고 실행 결과를 ResultSet객체에 저장한다.
			String sql = "SELECT * FROM LPROD"; // 실행할 SQL문
			
			rs = stmt.executeQuery(sql); // select문의 경우
			
			// 5. ResultSet 객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차려대로 읽어와 처리한다.
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("=== 쿼리문 결과 ===");
			
			// rs.next() => ResultSet 객체의 데이터를 가리키는 포인터를 다음 레코드로 이동 시키고, 
			//				그곳에 자료가 있으면 true, 없으면 false를 반환한다.
			while (rs.next()) {
				// 컬럼의 자료를 가져오는 방법
				// 방법1) rs.get자료형 이름 ("컬럼명")
				// 방법2) rs.get자료형 이름 (컬럼번호) => 컬럼번호는 1부터 시작
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");
			}
			
			System.out.println("출력 끝....");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
	}
}

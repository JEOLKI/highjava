package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	static {
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			// 2. DB에 접속 (Connection 객체 생성)
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"HJG",
					"java");
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!");
			e.printStackTrace();
			return null;
		}
	}
	
	
}

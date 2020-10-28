package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정
 */
public class JDBCUtil2 {
	static Properties prop;
	
	static {
		
		prop = new Properties();
		
		File file = new File("res/db.properries");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
			
		} catch (FileNotFoundException e) {
			//System.out.println("파일을 찾을수 없음");
			e.printStackTrace();
		} catch (IOException e) {
			//System.out.println("파일을 로드 실패");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			//System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (SQLException e) {
			//System.out.println("DB연결실패");
			e.printStackTrace();
			return null;
		}
	}
	

	
	
}





























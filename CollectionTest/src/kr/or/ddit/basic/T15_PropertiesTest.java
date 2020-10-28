package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class T15_PropertiesTest {
/*
	Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
	Map은 모든 형태의 객체데이터를 key와 value값으로 사용할 수 있지만,
	Properties는 key와 value값으로  String만 사용할 수 있다.
	
	Map은 put(), get()메서드를 이용하여 데이터를 입출력 하지만
	Properties는 setProperty(), gryProperty() 메서드를 통해서 데이터를 입출력한다.
	
*/	
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");
		
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		
		System.out.println("이름 : " + name);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + prop.getProperty("addr"));
		
		// 내용을 파일로 저장하기 //유지보수가 좋음. properties파일만 수정하면 되기때문에
		try {
			prop.store(new FileOutputStream("src/kr/or/ddit/basic/test.properties"), "코멘트");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

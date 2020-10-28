package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/*
 *  ResourceBundle객체 => 확장자가 properties인 파일 정보를 읽어와 key값과 value값을 분리한 정보를 갖는 객체 
 *  				 => 읽어올 파일은 'key갑=value값' 형태로 되어 있어야 한다.
 *  				 => 다국어 처리를 하기위한 목적으로 사용한다.
 */
public class T07_ResourceBundleTest {
	public static void main(String[] args) {
		// ResourceBundle 객체를 이용하여 파일 읽어오기
		
		System.out.println(Locale.getDefault()); // 기본값의 지역확인
		
		// ResourceBundle 객체 생성하기
		// => 파일을 지정할 때는 '파일명'만 지정하고 확장자는 지정하지 않아도 된다. (항상 properies이기 때문)
//		ResourceBundle bundle = ResourceBundle.getBundle("db");
		ResourceBundle bundle = ResourceBundle.getBundle("db", Locale.US); // 강제적으로 지역을 바꿔서 영어로 나오도록
		
		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		// key값 개수만큼 반복해서 value값 가져오기
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			// key값을 이용하여 value값을 읽어오는 방법
			// => bundle 객체변수, getString(key값);
			String value = bundle.getString(key);
			
			System.out.println(key + " => " + value);
			
			
		}
		
	}
	
	
}

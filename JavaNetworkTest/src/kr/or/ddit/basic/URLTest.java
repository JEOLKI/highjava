package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
	
	// 특정 리소스를 지칭 하는 것 URI(uniform resource identifier) : URL(locator), URN이 있다.
	// 참고 사이트 => https://mygumi.tistory.com/139
	
	
	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		
		// URL클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
		// 		   => locator 위치 정보를 제공함 
		
		// URL을 다루기위한 URL클래스 객체 사용 
		// http://ddit.or.kr:80/index.html?ttt=123
		URL url = new URL("http", "ddit.or.kr", 80, "main/index.html?ttt=123#kkk"); // URL 객체생성
		
		System.out.println("전체 URL 주소 : http://ddit.or.kr:80/main/index.html?ttt=123#kkk");
		
		// URL의 정보요소 추출하는 메서드
		System.out.println("protocol : "+ url.getProtocol()); // http
		System.out.println("host : "+ url.getHost()); // ddit.or.kr
		System.out.println("file : "+ url.getFile()); // main/index.html?ttt=123 (쿼리정보포함)
		System.out.println("qurey : "+ url.getQuery()); // ttt=123
		System.out.println("path : "+ url.getPath()); // main/index.html (쿼리정보 미포함)
		System.out.println("port : "+ url.getPort()); // 80
		System.out.println("ref : "+ url.getRef()); // kkk => 레퍼런스 정보
		System.out.println();
		
		// 결과 동일 : http://ddit.or.kr:80main/index.html?ttt=123#kkk
		System.out.println(url.toExternalForm());
		System.out.println(url.toString());
		System.out.println(url.toURI().toString());
		
	}
	
	
}

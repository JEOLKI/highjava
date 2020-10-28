package kr.or.ddit.basic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 서블릿의 라이프사이클을 확인하기 위한 예제
 * (서블릿이란 ? 컨테이너(서블릿엔진)에 의해 관리되는 자바기반 웹 컴포넌트로서, 동적인 웹 컨텐츠 생성을 가능하게 해준다.)
 * 웹 프로그래밍을 하기 위해 만드는 것. 기본적으로 http 프로토콜에 맞춰서 서블릿프로그램을 하게 됨.
 */
public class T01_ServletLifeCycle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * 생성자
     */
    public T01_ServletLifeCycle() {
    	System.out.println("생성자 호출됨.");
    }

    
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성...
		System.out.println("init() 호출됨...");
	}
    
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// tomcat이 HttpServletRequest req, HttpServletResponse resp 을 만들어 파라미터로 넣는다.
		// 실제적인 작업 수행이 시작되는 지점... (자바의 메인메서드 역할)
		System.out.println("service() 호출됨...");
		super.service(req, resp);
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메서드 방식이 get인 경우 호출됨...
		System.out.println("doGet() 호출됨...");
		
		throw new ServletException(); // T04를 위해 추가함 던져도상관없음 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메서드 방식이 post인 경우 호출됨...
		System.out.println("doPost() 호출됨...");
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		// 객체 소멸시(컨테이너로부터 서블릿객체 제거시) 필요한  코드 작성...자동  ex)자원반납
		System.out.println("destroy() 호출됨...");
	}

}

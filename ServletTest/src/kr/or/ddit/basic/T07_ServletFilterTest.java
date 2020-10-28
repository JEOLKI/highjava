package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T07_ServletFilterTest implements Filter{ // 서블릿이아닌 서블릿 필터이다.(javax filter)

	/**
	 * 
	 * 서블릿 필터에 대하여...
	 * = 내가 개발한 서블릿에 공통적인 기능을 제공해주고 싶으면 필터를 사용하여 제공한다.
	 * 
	 * 
	 * 1. 사용목적
	 * - 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
	 * - 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
	 * 
	 * 2. 사용예
	 * - 인증필터 : 사용인증을 매번 하는것을 간단하게 
	 * - 데이터 압축필터 : 결과의 크기가 크면 속도의 저하를 방지하기위해 압축
	 * - 인코딩 필터 : 한글사용시 문제가 발생하기 때문에 필터를 만들어 서블릿내에서 사용한다.
	 * - 로깅 및 감사처리 필터 : 로그남기기 감사하는 기능을 사용하기위해
	 * - 이미지 변환 필터 등 : 이미지를 변환하는작업을 하기위해
	 * 
	 */
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("T07_ServletFilterTest => init() 호출됨.");
	
		// 초기화 파라미터 정보 가져오기, web.xml에서 넣어준 파라미터정보를 가져오는것
		String initParam = config.getInitParameter("init-param"); 

		System.out.println("init-param : " + initParam);
	
	}

	@Override
	public void destroy() {
		// 필터 객체가 웹컨테이너에 의해 서비스로부터 제거되기 전에 호출됨.
		System.out.println("T07_ServletFilterTest => destroy() 호출됨.");
	}

	@Override // 서블릿에서 service역할하는것과 비슷
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {

		System.out.println("T07_ServletFilterTest 시작.");
		
		// 클라이언트의 IP주소 가져오기
		String ipAddress = req.getRemoteAddr();
		System.out.println("IP주소 : " + ipAddress + "\n포트번호 : " + req.getRemotePort() 
							+ "\n현재시간 : " + new Date().toString());
		
		
		// 필터체인을 실행한다. (req, resp객체 전달)
		fc.doFilter(req, resp); // 그다음 필터를 실행해달라는 것 => 필터체인객체에 보냄 없으면 서블릿으로감
		
		System.out.println("T07_ServletFilterTest 완료.");
		
		
		
	}


}

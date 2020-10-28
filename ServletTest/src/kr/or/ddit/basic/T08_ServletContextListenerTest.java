package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T08_ServletContextListenerTest extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 컨텍스트에 저장 : 에플리케이션에 같이 보이는 전역객체 개념 : 전역변수와 비슷하게 범위를 생각
		req.getServletContext().setAttribute("ATTR1", "속성1");  // 속성값 추가
		req.getServletContext().setAttribute("ATTR1", "속성11"); // 속성값 변경
		req.getServletContext().setAttribute("ATTR2", "속성2");  // 속성값 추가
		
		getServletContext().removeAttribute("ATTR1"); 		    // 속성값 제거
		
		// request객체에 저장 응답하면사라짐 : 잠깐 사용 : 가급적으로 사용해야 메모리를 소모하지 않음
		//req.setAttribute("ATTR1", "속성1");
		
		// 세션에다가 저장 세션이 유지된상태만 의미가있다 : 특정한 사용자와 해당사람을 위한 세션객체에 저장 
		//req.getSession().setAttribute("ATTR1", "속성1");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

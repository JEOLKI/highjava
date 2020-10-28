package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 동작 방식에 대하여...
 * 1. 사용자(클라이언트)가 URL을 클릭하면 HTTP Request를  Servlet Container로 전송(요청)한다.
 * 2. 컨테이너는 web.xml에 정의된 url패턴을 확인하여 어느 서블릿을 통해 처리해야 할지를 검색한다.
 *    (로딩이 안된 경우에는 로딩함. 로딩사 init()메서드 호출됨.)
 * 3. Servlet Container는 요청을 처리할 개별 쓰레드 객체를 생성하여 해당 서블릿 객체의 service()메서드를 호출한다.
 *    (HttpServletRequest 및 HttpServletResponse 객체를 생성하여 파라미터로 넘겨준다.)
 * 4. service() 메서드는 메서드 타입을 체크하여 적절한 메서드를 호출한다.(doGet, doPost, doPut, doDelete 등)
 * 5. 요청 처리가 완료되면 (HttpServletRequest 및 HttpServletRespinse 객체는 소멸된다.)
 * 6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.
 */
public class T02_ServletTest extends HttpServlet{ // extends하면 서블릿 클래스가 됨
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// <request>
			
		// Post방식으로 넘어오는 Byte데이터를 인코딩 처리함. get방식은 톰캣의 URIEncoding 설정을 이용함.
		// 반드시 request에서 값을 가져오기 전에 먼저 설정해야 적용됨.
		//req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name"); // 요청 정보로 부터 name 값을 가져옴.
		
		System.out.println(name);
		System.out.println(req.getParameter("age"));
		System.out.println(req.getParameter("food"));
		System.out.println("요청 URL : " + req.getServletPath()); // 요청 URL 
		
		//-----------------------------------------------------------------------------
		// <response>
		
		//resp.setCharacterEncoding("UTF-8");					// 응답메시지 인코딩 설정
		resp.setContentType("text/plain"); 	// 응답 메시지 컨텐트 타입 설정 
		//text/plain 바디부분이 모두 문자열로 보낼것이다.
		
		// 실제 수행 할 로직(기능)이 시작되는 부분...
		
		PrintWriter out = resp.getWriter(); // 버퍼기능
		
		out.println("name => " + name);
		out.println("age => " + req.getParameter("age"));
		out.println("food => " + req.getParameter("food"));
		
		//out.flush(); 
		// 사용하면 java.lang.IllegalStateException
		// : Cannot forward after response has been committed 예외가 발생한다.
		// flush 하는 순간 내부적으로 결과를 사용자에게 내보내기 때문에 
		// 이후에 다른작업인 forward가 나오면 의미가 없기때문에 tomcat에서 예외시킨다.
		
		// ==> 버퍼의 내용을 방출하기 시작하면 forward 및 redirect시 예외가 발생함. (방출전엔 버퍼내용 무시됨.)
		
		
		// 사용자의 요청을 그대로 보내는 기능을 가지고있는 객체 : 메소드 호출 (보낼위치:URL정보)
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/index.html");
		//dispatcher.forward(req, resp); // forward로 실행됨. 꺼내와서 forward
		// 주소는 유지가 되지만 입력해주면 index.html로 이동이된다.
		// 어떤 작업이 일어났는지 모르도록 할수 있다 서버단에서 처리후 나옴  
		// Url에 name등이 표시되지만 네트워크단에서는 이동이 보이지않음

		
		// 요청을 하면 URL주소가 바꾸어 status가 302로 리턴해주면 브라우저가 (index.html)을 찾아서 보여줌
		// 네트워크 단에서 작업의 이동에따라  URL의 이동이 보인다.
		// URL 주소에는 name등이 표기되지않음
		// 우리서버에서 이 URL을 작업할 수있는 내용이 아니야 그럼 302를 리턴하여 브라우저가 다른 URL을 찾아감
		// 처리하는 요청방향이 바뀌는것 => URL의 주소가 바뀔수 밖에 없다.
		//resp.sendRedirect(req.getContextPath() + "/index.html");
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}



}

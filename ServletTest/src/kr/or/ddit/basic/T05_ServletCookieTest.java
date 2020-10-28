package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T05_ServletCookieTest extends HttpServlet{
	
	// 쿠키의 단점 : 정보를 임의로 수정할 수 있다. 보안사항이 취약하다. => 세션이용
	// 쿠키정보는 클라이언트에서 생성 세션은 서버에 생성되기때문에 보안에 더 유리하다.
	// 서버가 클라이언트의 의미있는정보가 있을때 클라이언트쪽에 저장하기위함. => 서버의 메모리를 사용하지 않아도 됨.
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//setCookieExam(req, resp); // 쿠키 설정
		
		//readCookieExam(req, resp); // 쿠키정보 읽기
		
		deleteCookieExam(req, resp); // 쿠키 삭제
		
	}
	
	/**
	 * 쿠키정보 삭제
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/**
		 * 사용중인 쿠키 정보를 삭제하는 방법...
		 * 
		 * 1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
		 * 
		 * 2. 쿠키객체의 최대 지속시간을 0으로 설정한다.
		 * 
		 * 3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
		 * 
		 */
		Cookie cookie = null;
		
		Cookie[] cookies = req.getCookies(); // 현재 도메인에서 사용중인 쿠키정보배열 가져오기
		
		// 응답헤더에 인코딩 및 content-type 성정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 삭제 예제";
		
		out.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head><title>" + title + "</title></head>\n"
				+ "<body>\n"
				);
		
		if(cookies != null) {
			
			out.print("<h2>" + title + "</h2>");
			
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				
				if(cookie.getName().equals("userId")) {
					// 쿠키제거하기
					cookie.setMaxAge(0);
					
					resp.addCookie(cookie);
					out.print("삭제한 쿠키 : " + cookie.getName() + "<br>");
				}
				
				out.print("name : " + cookie.getName() + ", " );
				out.print("value : " + cookie.getValue() + "<br> " );
				
			}
			
		}else {
			out.print("<h2>쿠키정보가 없습니다.<h2>");
		}
		
		out.println("</body>");
		out.println("</html>");
		
	}


	/**
	 * 쿠키정보 읽기
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Cookie cookie = null;
		Cookie[] cookies = req.getCookies(); // 현재 도메인에서 사용중인 쿠키정보배열 가져오기
		
		// 응답헤더에 인코딩 및 content-type 성정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키정보 읽기 예제";
		
		out.println("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head><title>" + title + "</title></head>\n"
				+ "<body>\n"
				);
		if(cookies != null) {
			out.println("<h2>"+ title +"</h2>");
		
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				out.print("name : " + cookie.getName() + "<br>");
				out.print("value : " + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
				out.print("<hr>");
			}
			
		}else {
			out.println("<h2>쿠키정보가 없습니다.</h2>");
		}
	
		out.println("</body>");
		out.println("</html>");

	}

	/**
	 * 쿠키 생성용 메서드
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	/*
		쿠기정보를 설정하는 방법...
		작은 정보를 브라우저에 만들어 놓는것.
		요청할때마다 브라우져와 서버사이에 계속 유지하고 싶은 정보를 쿠키로 저장해놓으면 필요할때 꺼내서사용할수 있다.
		
		1. 쿠키 객체를 생성한다. 사용불가문자( 공백, [] ()=,"/?@:; )
		Cookie cookie = new Cookie("키 값", "value값");
		
		2. 쿠키 최대 지속시간을 설정한다. (초단위) => 지정하지 않으면 웹브라우저를 종료할때 쿠키를 함께 삭제한다.
		cookie.setMaxAge(60*60*24); // 24시간
		
		3. 응답 헤더에 쿠키 객체를 추가한다.
		response.addCookie(cookie);
		
		=> 출력버퍼가 플러시된 이후에는 쿠키를 추가할 수 없다.(응답헤더를 통해서 웹브라우저에 전달하기 때문에...)
		
	*/
		
		// 쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		// 쿠키값에 한글을 사용시 인코딩 처리를 해준다.
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "utf-8"));
		
		// 쿠키소멸시간 설정(초단위)
		userId.setMaxAge(60*60*24); // 1일
		name.setMaxAge(60*60*48); // 2일
		
		// 응답헤더에 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		// 응답헤더에 인코딩 및 Content type 설정
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "쿠키설정 예제";
		
		out.print("<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "<title>"+ title + "</title>"
				+ "<body>\n"
				+ "<h1 align=\"center\">"+ title + "</h1>\n"
				+ "<ul>\n"
				+ "<li><b>ID</b>: "
				+ req.getParameter("userId") + "\n" 
				+ "<li><b>이름</b>:"
				+ req.getParameter("name") + "\n" 
				+ "</ul>\n"
				+ "</body>\n"
				+ "</html>");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

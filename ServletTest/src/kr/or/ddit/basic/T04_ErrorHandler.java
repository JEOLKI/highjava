package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//web.xml에서 error page 설정
public class T04_ErrorHandler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 서블릿 예외 처리하기  // tomcat이 에러발생시 저절로 셋팅해줌
		Throwable throwable = 
				(Throwable) req.getAttribute("javax.servlet.error.exception"); // 예외정보
		Integer statusCode = 
				(Integer) req.getAttribute("javax.servlet.error.status_code"); // 에러상태코드
		String servletName = 
				(String) req.getAttribute("javax.servlet.error.servlet_name"); // 에러발생한 서블릿 이름
		
		if (servletName == null) {
			servletName = "알수없는 서블릿";
		}
		
		String requestUri = 
				(String) req.getAttribute("javax.servlet.error.request_uri"); // 에러발생 url정보
		
		if (requestUri == null) {
			requestUri = "알수없는 URI";
		}
		
		resp.setCharacterEncoding("UTF-8");
		// 응답 content-type 설정하기
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "Error/Exception 정보";
		
		out.println("<!DOCTYPE html>\n" 
				+ "<html>\n" 
				+ "<head><title>" + title + "</title></head>\n"
				+ "<body>\n");
		
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러정보없음<h2>");
			out.println("홈페이지로 돌아가기: <a href=\""
					  + "http://localhost/"
					  + "\">홈페이지</a>");
		}else if (statusCode != null) {
			out.println("상태코드 : " + statusCode);
		}else {
			out.println("<h2>에러 정보<h2>");
			out.println("서블릿이름 : " + servletName + "<br><br>");
			out.println("예외 타입 :" + throwable.getClass().getName() + "<br><br>");
			out.println("요청 URI : " + requestUri + "<br><br>");
			out.println("예외 메시지 : " + throwable.getMessage());
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}

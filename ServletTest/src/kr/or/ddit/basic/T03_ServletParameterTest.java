package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03_ServletParameterTest extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("T03_ServletParameterTest 초기화 중...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*
		 요청 객체로부터 파라미터 데이터 가져오는 방법
		 
		 - getParameter() : 파라미터 값이 한개인 경우에 가져올때 사용함.
		 - getParameterValues() : 파라미터값이 여러개인 경우에 사용함. ex) Checkbox 
		 - getParameterNames() : request에 존재하는 모든 파라미터 정보를 가져올때 사용함.
	*/
		req.setCharacterEncoding("UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		//String hoddy = req.getParameter("hobby"); // 다중선택시 첫번째꺼만 들어옴 
		String[] hobby = req.getParameterValues("hobby");
		String rlgn = req.getParameter("rlgn");
		String[] food = req.getParameterValues("food");
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html"); // 보내줄 타입이 html이라고 알려준다.
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<p>username : " + username +"</p>");
		out.println("<p>password : " + password +"</p>");
		out.println("<p>gender : " + gender +"</p>");
		
		if (hobby != null) {
			for (String s : hobby) {
				out.println("<p>hoddy : " + s +"</p>");
			}
		}
		
		out.println("<p>religion : " + rlgn +"</p>");
		
		if (food != null) {
			for (String s : food) {
				out.print("<p>food : "+ s + "</p>");
			}
		}
		
		Enumeration<String> params = req.getParameterNames(); // iterator처럼 사용
		
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			out.println("<p>파라미터 이름 : " + param + "</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}

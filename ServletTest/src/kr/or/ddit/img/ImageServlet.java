package kr.or.ddit.img;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿을 이용한 이미지 출력 예제
 * (ServletOutputStream을 이용한 이미지 출력 예제)
 */
public class ImageServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("image/jpeg"); // 컨텐츠 타입 설정
		
		ServletOutputStream out = resp.getOutputStream();
		FileInputStream fis = new FileInputStream("d:/D_Other/aaaa.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int bytes = 0;
		while ((bytes = bis.read()) != -1) {
			bos.write(bytes);
		}
		
		bis.close();
		bos.close();
		
	}
	
}

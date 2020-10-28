package kr.or.ddit.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3.0 부터 지원하는 Part 인터페이스를 이용한 파일 업로드 예제
 * 
 * form데이터를 part로 나눠서 보낸다는 개념
 */
@WebServlet(name="uploadServlet2", urlPatterns= {"/upload3"})
@MultipartConfig(fileSizeThreshold = 1024*1024, //메모리 쓰레드홀드와 같은개념 
					maxFileSize = 1024*1024*5, 
						maxRequestSize = 1024*1024*5*5)
public class UploadServlet2 extends HttpServlet{
	private static final String UPLOAD_DIRECTORY = "upload_files";
	private static final String DEFAULT_FILENAME = "default_file";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uploadPath = "D://D_Other";
		//String uploadPath = getServletContext().getRealPath("")+ File.separator + UPLOAD_DIRECTORY;
		
		File uploadDir = new File(uploadPath);
		
		if(!uploadDir.exists()) { // 경로가 존재하지 않으면
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			for(Part part : req.getParts()) {
				fileName = getFileName(part); // 파일이름가져오는 메소드
				part.write(uploadPath + File.separator + fileName); // 파일 저장
			}
			
			req.setAttribute("message", "파일명 : " + fileName + " 업로드 성공!");
			
		} catch (FileNotFoundException ex) {
			req.setAttribute("message", "에러발생 : " + ex.getMessage());
		}
		
		resp.setContentType("text/html");
		resp.getWriter().print("업로드 완료...!!!");
		
	}
	
	/**
	 * 파일 이름 추출하기
	 * @param part
	 * @return
	 */
	private String getFileName(Part part) {
		
		//파일이름이 있으면 가져오고 아니면 디폴트값이 전송되도록 만들어놓음
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf("=") + 2, content.length() -1);
			}
		}
		return DEFAULT_FILENAME;
	}
	
}

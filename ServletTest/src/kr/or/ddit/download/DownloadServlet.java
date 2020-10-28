package kr.or.ddit.download;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(value= "/DownloadServlet")
//@WebServlet(urlPatterns= {"/DownloadServlet"})
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet{

	// 파일경로(변수)
	private static final String DOWNLOAD_DIR = "d:/D_Other/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일명(변수)
		String fileName = "개념적설계_8팀(최윤지, 최민준, 홍정기).pptx"; // 실제 파일명
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // 다운로드 받을때의 파일명
						// 헤더이름 					벨류값	추가정보
						// 파일을 첨부할것이다 라는 의미 = > 다운받을것이다
						// 실제 파일명은 다를 수도 있다 서버에 다른이름으로 저장해놓은것이 아니라 실제파일명을 가져와야함 DB에서
		
		FileInputStream fis = new FileInputStream(DOWNLOAD_DIR + fileName);
		// 버퍼 등을 이용하여 속도를 향상시킬수 있다.

		ServletOutputStream out = resp.getOutputStream();
		
		
		
		int c;
		while ((c = fis.read()) != -1) {
			out.write(c);
		}
		fis.close();
		
	}
	
}

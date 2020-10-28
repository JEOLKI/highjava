package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 아파치 자카르타 프로젝트의 fileupload 모듈을 이용한 파일 업로드 예제
 * 
 * commons-io-2.6.jar 사용
 * commons-fileupload-1.3.3.jar
 * 
 */
public class UploadServlet extends HttpServlet{
	
	private static final String UPLOAD_DIRECTORY = "upload_files";
	
	// 메모리 임계크기 (이크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THREASHOLD = 1024*1024*3; // 쓰레드홀드 : 
	
	
	// 서버자체가 사용하는 메모리를 브라우저가 연결해주는..
	// 메모리로 로딩이 됨 메모리가 많이 사용되면 서버가 꺼질수도 있다. 파일을 처리할때 3메가까지는 그냥 메모리로 갓다가 3메가보다 더큰 파일이면 임시로 템프디렉토리로 저장해줌..
	// 3메가까지는 메모리로 넘어가면 파일로 저장하겟다.라는 뜻
	
	private static final long MAX_FILE_SIZE = 1024*1024*40; 	// 파일 1개당 최대 크기
	private static final long MAX_REQUEST_SIZE = 1024*1024*50;  // 요청 파일 최대 크기

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// form 타입은 post전송타입을 해줘야한다.
		if(ServletFileUpload.isMultipartContent(req)) { // req가 인코딩 타입이 multipart/form-data인 경우... //디폴트.. 따따따..=> 폼데이터 보내는 방식(아이디 비번등)
			DiskFileItemFactory factory = new DiskFileItemFactory(); // 파일아이템을 생성하는 factory
			factory.setSizeThreshold(MEMORY_THREASHOLD); // factory에 정보를 세팅해준다.
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			// 관리하는 시스템정보중 java.io.tmpdir가 필요 템프디렉토리의 경로를 알수 있다 사용하겟다.(임의의 D:/Other 등을 사용해도됨)
			
			ServletFileUpload upload = new ServletFileUpload(factory); // 업로드 처리를 위한 객체생성
			upload.setFileSizeMax(MAX_FILE_SIZE); // 세팅값 설정
			upload.setSizeMax(MAX_REQUEST_SIZE);
		
			// 웹애플리케이션 루트 디렉토리 기준으로 업로드 경로 설정하기 
			// 디어더등으로 하드코딩해도 된다.// 웹어플리케이션의 웹컨텐츠 아래에 나의 프로젝트안의 컨텍스트루트 밑으로 파일을 저장하게 할수 있다. 
			// 장점은 웹애플기준으로 모여있어서 관리가 쉽다. 일반적인 방법은 아니다.// 예제에서는 웹에플리케이션루트디렉토리를 접근하기위한 예제이다.
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
			// 서블릿 컨텍스트안에있는 . 실제경로 . 경로.경로
			
			File uploadDir = new File(uploadPath);
		
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			
			try {
				
				// req객체를 분석해서 파일아이템목록을 반환한다. 모든게다 파일아이템으로 저장되었엇음
				List<FileItem> formItems = upload.parseRequest(req);
				
				if(formItems != null && formItems.size() > 0) { // 파일일수도있도 form 데이타일수도 있다.
					for(FileItem item : formItems) {
						if(!item.isFormField()) { // 파일인 경우... // 아이템이 form일수도 아닐수도 있기 때문에 확인
							String fileName = new File(item.getName()).getName(); // 파일명만 추출
							String filePath = uploadPath + File.separator + fileName;
							File storeFile = new File(filePath);
							item.write(storeFile); // 업로드 파일 저장
							req.setAttribute("message", "업로드 완료됨. => 파일명 : " + fileName);
						} else { // 폼데이터인 경우...
							System.out.println("파라미터명 : " + item.getFieldName());
							System.out.println("파라미터값 : " + item.getString());
						}
					}
				}
				
			} catch (Exception e) {

				req.setAttribute("message", "예외발생 : " + e.getMessage());
			
			}

			resp.setContentType("text/html");
			resp.getWriter().print("업로드 완료됨.!!@@!@");
			
		}

	}
	
}

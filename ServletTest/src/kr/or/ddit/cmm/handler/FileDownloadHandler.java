package kr.or.ddit.cmm.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.member.handler.CommandHandler;

public class FileDownloadHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		// 파일명(변수)
		int fileId = Integer.parseInt(req.getParameter("fileId"));
		
		FileVO fileVO =  fileService.select(fileId); // 파일정보 조회
		
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileVO.getOrignlFileNm(), "utf-8").replaceAll("\\+", "%20") + "\""); // 다운로드 받을때의 파일명
						// 헤더이름 					벨류값	추가정보
						// 파일을 첨부할것이다 라는 의미 = > 다운받을것이다
						// 실제 파일명은 다를 수도 있다 서버에 다른이름으로 저장해놓은것이 아니라 실제파일명을 가져와야함 DB에서
		
		BufferedInputStream  bis = new BufferedInputStream(new FileInputStream(fileVO.getFileStrePath()));
		// 버퍼 등을 이용하여 속도를 향상시킬수 있다.

		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int c;
		while ((c = bis.read()) != -1) {
			bos.write(c);
		}
		bis.close();
		bos.close();
		
		return null;
	}

}

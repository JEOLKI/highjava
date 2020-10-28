package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class InsertMemberCommandHandler implements CommandHandler {
	
	private static final String VIEW_PAGE = "/WEB-INF/view/member/insertForm.jsp";
	
	private IAtchFileService fileService = AtchFileServiceImpl.getInstance();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equals("GET")) {
			
			return VIEW_PAGE;
			
		}else if(req.getMethod().equals("POST")) {
			
			 FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFile"); // FileItem 추출
			 FileVO atchFileVO = new FileVO();
			 if(item != null && !item.getName().equals("")) {
				 try {
					 atchFileVO = fileService.saveAtchFile(item);
					 
				 }catch(Exception ex) {
					 ex.printStackTrace();
				 }
			 }
			System.out.println("첨부파일 아이디: " + atchFileVO.getAtchFileId());
			// 1. 요청파라미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			// 2.서비스 객체 생성하기
			IMemberService memService = MemberServiceImpl.getInstance();
			
			// 3. 회원정보 등록
			MemberVO mv = new MemberVO();
			mv.setMem_id(memId);
			mv.setMem_name(memName);
			mv.setMem_tel(memTel);
			mv.setMem_addr(memAddr);
			
			if (atchFileVO != null) {
				mv.setAtch_file_id(atchFileVO.getAtchFileId());
			}
			
			int cnt = memService.insertMember(mv); // 회원등록
			
			String msg = "";
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			// 4. 목록 조회화면으로 이동
			String redirectUrl = req.getContextPath() + "/selectAllMember?msg="
					+ URLEncoder.encode(msg, "utf-8");
			
			return "redirect:" + redirectUrl;
		}
		
		return null;
	}

}

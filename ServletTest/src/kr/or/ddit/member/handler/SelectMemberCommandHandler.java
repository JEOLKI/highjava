package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.cmm.service.AtchFileServiceImpl;
import kr.or.ddit.cmm.service.IAtchFileService;
import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class SelectMemberCommandHandler implements CommandHandler {
	
	private static final String VIEW_PAGE = "/WEB-INF/view/member/select.jsp";
	
	private IMemberService memberService = MemberServiceImpl.getInstance();
	
	private IAtchFileService fileService = AtchFileServiceImpl.getInstance();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String memId = req.getParameter("memId");
		
		// 2. 회원정보 조회
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		List<MemberVO> memList = memberService.getSearchMember(mv);
		
		FileVO fileVO = fileService.select(memList.get(0).getAtch_file_id());
		
		// 3. request객체에 회원정보 저장
		req.setAttribute("memVO", memList.get(0));
		req.setAttribute("fileVO", fileVO);
		
		
		return VIEW_PAGE;
	}

}

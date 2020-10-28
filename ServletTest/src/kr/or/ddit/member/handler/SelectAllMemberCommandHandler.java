package kr.or.ddit.member.handler;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class SelectAllMemberCommandHandler implements CommandHandler {
	
	private static final String VIEW_PAGE = "/WEB-INF/view/member/list.jsp"; 
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String msg = req.getParameter("msg") == null ? "" : req.getParameter("msg");
		
		// 1. 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 2. 회원 정보 조회
		List<MemberVO> memList = memberService.getAllMember();
		
		// 3. 결과를 화면(브라우져)에 출력하기
		req.setAttribute("msg", msg);
		req.setAttribute("memList", memList);
		
		return VIEW_PAGE;
	}

}

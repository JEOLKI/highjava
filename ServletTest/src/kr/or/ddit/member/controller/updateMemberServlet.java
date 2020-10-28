package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


@WebServlet("/updateMember")
public class updateMemberServlet extends HttpServlet{

	IMemberService memberService = MemberServiceImpl.getInstance();
	
	//일반적으로 데이터를 전송할때에는 post 방식을 이용하여 전달한다. get은 길이의 제한이 있음
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		
		// 1. 서비스 객체 생성하기
		//memberService = MemberServiceImpl.getInstance();
		
		// 2. 회원 정보 조회.
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		List<MemberVO> memList = memberService.getSearchMember(mv);
		
		// 3. request객체에 회원 정보 저장
		req.setAttribute("memVO", memList.get(0));
		
		// 4. VIEW화면으로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/member/updateForm.jsp");
		dispatcher.forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// form에서 post로 전송했을때
		
		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		// 2. 서비스 객체 생성하기
		
		// 3. 회원정보 수정
		MemberVO mv = new MemberVO();
		mv.setMem_id(memId);
		mv.setMem_name(memName);
		mv.setMem_tel(memTel);
		mv.setMem_addr(memAddr);
		
		int cnt = memberService.updateMember(mv); // 회원정보 수정
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		
		// 4. 목록 조회 화면으로 이동
		String redirectUrl = req.getContextPath() + "/selectAllMember?msg="+ URLEncoder.encode(msg, "utf-8");
		
		resp.sendRedirect(redirectUrl); // 목록 조회화면으로 리다이렉트 처리하기.
		
		
		
		
	}
	
	
}

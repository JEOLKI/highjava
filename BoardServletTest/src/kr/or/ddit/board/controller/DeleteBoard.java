package kr.or.ddit.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;


@WebServlet("/deleteBoard")
public class DeleteBoard extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 변수 받아오기
		int board_no = Integer.parseInt(req.getParameter("boardNo"));
		
		// 2. 서비스객체 호출
		IBoardService service = BoardServiceImpl.getInstance();
		
		// 3. 메소드 호출
		int cnt = service.deleteBoard(board_no);
		
		// 4. request에 결과 저장하기
		req.setAttribute("result", cnt);
		
		// 5. jsp로 forward
		req.getRequestDispatcher("board/deleteBoard.jsp").forward(req, resp);
		
	}
		
}

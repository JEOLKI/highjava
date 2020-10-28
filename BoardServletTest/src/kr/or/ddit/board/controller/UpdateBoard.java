package kr.or.ddit.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class UpdateBoard
 */
@WebServlet("/UpdateBoard")
public class UpdateBoard extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청파라미터 정보 가져오기
		String no = request.getParameter("num");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. 서비스 객체 생성하기
		IBoardService service = BoardServiceImpl.getInstance();
		
		// 3. Board정보 등록
		BoardVO board = new BoardVO();
		board.setBoard_content(content);
		board.setBoard_no(no);
		board.setBoard_title(title);
		
		
		// 메소드호출
		int cnt = service.updateBoard(board);
		
		request.setAttribute("count", cnt);

		request.getRequestDispatcher("board/updateBoard.jsp").forward(request, response);
		
	}

}

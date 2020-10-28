package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * 게시글을 작성하기 위한 서블릿
 */
@WebServlet("/InsertBoard")
public class InsertBoard extends HttpServlet {

	public InsertBoard() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 변수를 받아오기
		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");
		String board_writer = request.getParameter("board_writer");
		BoardVO board = new BoardVO();
		
		board.setBoard_title(board_title);
		board.setBoard_content(board_content);
		board.setBoard_writer(board_writer);
		
		//2. service 객체가져오기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//3. 메서드 사용하기
		int result = service.insertBoard(board);
		
		//4. request에 담기
		request.setAttribute("result", result);
	
		//5. jsp로 forward한다.
		request.getRequestDispatcher("board/insertBoard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

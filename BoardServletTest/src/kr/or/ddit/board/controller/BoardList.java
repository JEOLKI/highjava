package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Board의 글목록을 출력하기 위한 서블릿
 */
public class BoardList extends HttpServlet {
       
    public BoardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. service 객체 가져오기
		IBoardService service = (BoardServiceImpl) BoardServiceImpl.getInstance();
		
		//2. service 메소드 사용하기
		List<BoardVO> boardList = service.dispalyAllBoard();
	
		//3. request에 저장하기
		request.setAttribute("boardList", boardList);
		
		//4. jsp로 forward한다.
		request.getRequestDispatcher("board/boardList.jsp").forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

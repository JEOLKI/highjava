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
 * Servlet implementation class SearchBoard
 */
@WebServlet("/SearchBoard")
public class SearchBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//0. parameter 가져오기
    	String searchOption = request.getParameter("Option");
    	String searchKeyWord = request.getParameter("KeyWord");
    	
    	//1. service 객체 가져오기
    	IBoardService service = (BoardServiceImpl) BoardServiceImpl.getInstance();
    			
    	BoardVO board = new BoardVO();
    			
    	if (searchOption.equals("글제목만")) {
    		board.setBoard_title(searchKeyWord);
    	}else if (searchOption.equals("글내용만")) {
    		board.setBoard_content(searchKeyWord);
    	}else if (searchOption.equals("글제목 + 내용")) {
    		board.setBoard_title(searchKeyWord);
    		board.setBoard_content(searchKeyWord);
    	}else if (searchOption.equals("작성자")) {
    		board.setBoard_writer(searchKeyWord);
    	}
    			
    	//2. service 메소드 사용하기
    	List<BoardVO> boardList = service.getSearchBoard(board);
    				
    	//3. request에 저장하기
    	request.setAttribute("boardList", boardList);
    					
    	//4. jsp로 forward한다.
    	request.getRequestDispatcher("board/boardList.jsp").forward(request, response);
    }
    

}

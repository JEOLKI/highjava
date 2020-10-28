package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private static IBoardService serviceSingle;
	
	private IBoardDao boardDao;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		
		if(serviceSingle == null) {
			serviceSingle = new BoardServiceImpl();
		}
		return serviceSingle;
	}
	
	@Override
	public int insertBoard(BoardVO board) {
		return boardDao.insertBoard(board);
	}

	@Override
	public List<BoardVO> dispalyAllBoard() {
		return boardDao.dispalyAllBoard();
	}

	@Override
	public int updateBoard(BoardVO board) {
		return boardDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO board) {
		return boardDao.getSearchBoard(board);
	}
	

}

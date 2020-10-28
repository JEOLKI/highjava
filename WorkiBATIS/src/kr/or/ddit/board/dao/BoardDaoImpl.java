package kr.or.ddit.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	
	private SqlMapClient smc;
	
	private static IBoardDao daoSingle;
	
	private BoardDaoImpl() {
		Reader rd;
		
		try {
			// 1. xml 문서읽어오기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 2. Reader객체 이용하여  실제 작업객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
			
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패~");
			e.printStackTrace();
		}
	}
	
	public static IBoardDao getInstance() {
		
		if(daoSingle == null) {
			daoSingle = new BoardDaoImpl();
		}
		
		return daoSingle;
	}

	@Override
	public int insertBoard(BoardVO board) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("board.insertBoard", board);
			
			if (obj == null) cnt = 1; // 0보다 크기만하면댐
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return cnt;
	}

	@Override
	public List<BoardVO> dispalyAllBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = smc.queryForList("board.displayAllBoard");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO board) {
		int cnt = 0;
		
		try {
			cnt = smc.update("board.updateBoard", board);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0; 
		
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO board) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = smc.queryForList("board.getSearchBoard", board);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

}

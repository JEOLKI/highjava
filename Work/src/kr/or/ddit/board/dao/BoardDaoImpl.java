package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil;


public class BoardDaoImpl implements IBoardDao {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IBoardDao daoSingle;
	
	private BoardDaoImpl() {

	}
	
	public static IBoardDao getInstance() {
		
		if(daoSingle == null) {
			daoSingle = new BoardDaoImpl();
		}
		
		return daoSingle;
	}
	
	
	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		
	}

	@Override
	public int insertBoard(BoardVO board) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO JDBC_BOARD ( BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT ) "
						+"VALUES (board_seq.nextVal, ?, ?, sysdate, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_writer());
			pstmt.setString(3, board.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return cnt;
	}

	@Override
	public List<BoardVO> dispalyAllBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM JDBC_BOARD";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				
				BoardVO board = new BoardVO();
				board.setBoard_no(rs.getString("board_no"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_writer(rs.getString("board_writer"));
				board.setBoard_date(rs.getString("board_date"));
				board.setBoard_content(rs.getNString("board_content"));
				
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO board) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "UPDATE JDBC_BOARD " + 
						 "SET BOARD_TITLE = ? , " + 
					     "	  BOARD_DATE = sysdate , " + 
					     "	  BOARD_CONTENT = ? " + 
					     "WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setString(3, board.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0; 
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM JDBC_BOARD WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(BoardVO board) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " SELECT * FROM JDBC_BOARD WHERE 1=1 "; 
			if(board.getBoard_no() != null && !board.getBoard_no().equals("") ) {
				sql += " AND BOARD_NO = ? ";
			}
			if(board.getBoard_title() != null && !board.getBoard_title().equals("")) {
				sql += " AND BOARD_TITLE LIKE '%' || ? || '%' ";
			}
			if(board.getBoard_writer() != null && !board.getBoard_writer().equals("")) {
				sql += " AND BOARD_WRITER = ? ";
			}
			if(board.getBoard_date() != null && !board.getBoard_date().equals("")) {
				sql += " AND BOARD_DATE LIKE '%' || ? || '%' ";
			}
			if(board.getBoard_content() != null && !board.getBoard_content().equals("")) {
				sql += " AND BOARD_CONTENT LIKE '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			
			if(board.getBoard_no() != null && !board.getBoard_no().equals("") ) {
				pstmt.setString(index++, board.getBoard_no());
			}
			if(board.getBoard_title() != null && !board.getBoard_title().equals("")) {
				pstmt.setString(index++, board.getBoard_title());
			}
			if(board.getBoard_writer() != null && !board.getBoard_writer().equals("")) {
				pstmt.setString(index++, board.getBoard_writer());
			}
			if(board.getBoard_date() != null && !board.getBoard_date().equals("")) {
				pstmt.setString(index++, board.getBoard_date());
			}
			if(board.getBoard_content() != null && !board.getBoard_content().equals("")) {
				pstmt.setString(index++, board.getBoard_content());
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO board2 = new BoardVO();
				board2.setBoard_no(rs.getString("board_no"));
				board2.setBoard_title(rs.getString("board_title"));
				board2.setBoard_writer(rs.getString("board_writer"));
				board2.setBoard_date(rs.getString("board_date"));
				board2.setBoard_content(rs.getNString("board_content"));
				
				boardList.add(board2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return boardList;
	}

}

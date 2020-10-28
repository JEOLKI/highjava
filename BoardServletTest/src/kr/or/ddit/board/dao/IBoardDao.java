package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여
 * 서비스에 전달하는 DAO Interface
 * @author PC-01
 */
public interface IBoardDao {
	
	/**
	 * NoticeVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param notice DB에 insert할 자료가 저장된 NoticeVO 객체
	 * @return insert에 성공하면 1, 실패하면 0 반환
	 */
	public int insertBoard(BoardVO board);
	
	/**
	 * DB의 board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * @return BoardVO객체를 담고 있는 List객체
	 */
	public List<BoardVO> dispalyAllBoard();

	/**
	 * 하나의 BoardVO 자료를 이용하여 DB를 update하는 메서드
	 * @param board update할 게시글의 정보가 들어 있는 BoardVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateBoard(BoardVO board);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 그 게시글을 삭제하는 메서드
	 * @param boardNo 삭제할 게시글의 번호
	 * @return 작업성공 : 1, 작업실패 : 0;
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * BoardVO에 담긴 자료를 이용하여 게시글을 검색하는 메서드
	 * @param board 검색할 자료가 들어있는 BoardVO 객체
	 * @return 검색된 결과를 담은 List객체
	 */
	public List<BoardVO> getSearchBoard(BoardVO board);
	
	
	
}

package kr.or.ddit.board.vo;

/**
 * DB 테이블에 있는 컬럼을 기준으로 데이터를 객체화한 클래스이다.
 */
public class BoardVO {
	private String board_no; // 게시글 번호
	private String board_title; // 제목
	private String board_writer; // 작성자
	private String board_date; // 작성날짜
	private String board_content; // 내용
	
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	@Override
	public String toString() {
		return board_no + "\t" + board_title + "\t" + board_writer
				+ "\t" + board_date +"\t" + board_content;
	}
	
}

package kr.or.ddit.member.vo;

import java.util.Date;

public class BoardVO {
	private int boardSeq;
	private String boardTitle;
	private String boardContent;
	private String memId;
	private Date boardDate;
	private int atchSeq;
	private int roomSeq;
	
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getAtchSeq() {
		return atchSeq;
	}
	public void setAtchSeq(int atchSeq) {
		this.atchSeq = atchSeq;
	}
	public int getRoomSeq() {
		return roomSeq;
	}
	public void setRoomSeq(int roomSeq) {
		this.roomSeq = roomSeq;
	}
	
	@Override
	public String toString() {
		return boardContent;
	}
	
	
}

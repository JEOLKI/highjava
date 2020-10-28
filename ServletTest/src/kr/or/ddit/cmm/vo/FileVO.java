package kr.or.ddit.cmm.vo;

import java.util.Date;

public class FileVO {
	
	private int atchFileId;          // 첨부파일ID
	private String orignlFileNm;     // 원본파일명
	private long fileSize;           // 파일 사이즈
	private String fileExtsn;        // 파일 확장자
	private String fileStrePath;     // 파일저장경로
	private Date regDate;            // 등록일자

	public int getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(int atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileStrePath() {
		return fileStrePath;
	}
	public void setFileStrePath(String fileStrePath) {
		this.fileStrePath = fileStrePath;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}

package kr.or.ddit.cmm.service;

import java.sql.SQLException;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.cmm.vo.FileVO;

public interface IAtchFileService {
	/**
	 * 첨부파일을 저장하기 위한 메서드
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public FileVO saveAtchFile(FileItem item) throws Exception;
	
	/**
	 * 첨부파일 아이디로 첨부파일 정보 조회하기
	 * @param atchFileId
	 * @return
	 * @throws SQLException
	 */
	public FileVO select(int atchFileId) throws SQLException;
	
}

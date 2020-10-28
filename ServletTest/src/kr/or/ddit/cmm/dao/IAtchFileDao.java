package kr.or.ddit.cmm.dao;

import java.sql.SQLException;

import kr.or.ddit.cmm.vo.FileVO;

public interface IAtchFileDao {
	
	public int insertAtchFile(FileVO fileVO) throws SQLException;
	
	public FileVO selectAtchFile(int atchFileId) throws SQLException;
	
}

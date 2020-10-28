package kr.or.ddit.cmm.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.cmm.vo.FileVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class AtchFileDaoImpl implements IAtchFileDao {
	
	private static IAtchFileDao fileDao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IAtchFileDao getInstance() {
		if(fileDao == null) {
			fileDao = new AtchFileDaoImpl();
		}
		
		return fileDao;
	}
	
	
	@Override
	public int insertAtchFile(FileVO fileVO) throws SQLException {
		
		Object obj = smc.insert("atchFile.insertAtchFile", fileVO);
		
		int cnt = 0;
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public FileVO selectAtchFile(int atchFileId) throws SQLException {
		
		FileVO atchFileVO = 
				(FileVO) smc.queryForObject("atchFile.selectAtchFile", atchFileId);
		
		return atchFileVO;
	}

}

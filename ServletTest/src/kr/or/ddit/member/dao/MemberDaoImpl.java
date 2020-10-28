package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	
	private SqlMapClient smc;
	
	private static IMemberDao daoSingle; 
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static IMemberDao getInstance() {
		
		if(daoSingle == null) {
			daoSingle = new MemberDaoImpl();
		}
		
		return daoSingle;
	}

	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			Object obj = smc.insert("member.insertMember", mv); //성공하면 null 반환
			
			if(obj == null) cnt = 1; // 성공여부 판단위해서
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		
		try {
			int cnt = (int) smc.queryForObject("member.getMember", memId);
			
			if(cnt > 0) chk = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			
			memList = smc.queryForList("member.getMemberAll");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember", mv);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0; 
		
		try {
			cnt = smc.delete("member.deleteMember", memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			memList = smc.queryForList("member.getSearchMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

}

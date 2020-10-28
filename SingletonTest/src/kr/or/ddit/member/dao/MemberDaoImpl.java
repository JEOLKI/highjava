package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 1. 자기자신을 참조할 맴버변수 선언 
	private static IMemberDao daoSingle; 
	
	// 2. 외부에서 해당객체를 생성하지 못하도록 생성자를 private로 선언해준다.
	private MemberDaoImpl() {
		
	}
	
	// 3. 객체를 제공하기 위한 메서드를 만든다.
	public static IMemberDao getInstance() {
		
		if(daoSingle == null) {
			daoSingle = new MemberDaoImpl();
		}
		
		return daoSingle;
	}
			
			
			
	/**
	 * 자원반납용 메서드
	 */
	private void disconnect() {
		// 6. 종료(사용했던 자원을 모두 반납한다.)
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		
	}

	@Override
	public int insertMember(MemberVO mv) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER ( MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR ) "
						+"VALUES (?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_id());
			pstmt.setString(2, mv.getMem_name());
			pstmt.setString(3, mv.getMem_tel());
			pstmt.setString(4, mv.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return cnt;
	}

	@Override
	public boolean getMember(String memId) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "SELECT COUNT(*) AS CNT FROM MYMEMBER "
			 			+"WHERE MEM_ID = ? "; // ?을 이용하여 쿼리의 포맷만 정의한후 나중에 쿼리를 보내는 시점에 하나씩 값을 줄것이다. 쿼리를 재사용 할 수 있다.
			
			pstmt = conn.prepareStatement(sql); // prepareStatement는 sql문을 미리 준비해야한다.
			pstmt.setString(1, memId); // ? 에 값을 넣어주는것 문자열형식
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("CNT");
			}
			
			if (cnt > 0 ) {
				chk = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		} finally {
			disconnect();
		}
		
		return chk;
	}

	@Override
	public List<MemberVO> getAllMember() {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			// 반복문 안에서 가져온 레코드 하나하나를  VO에 맵핑하고 이 VO를 List에 추가한다.
			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv = new MemberVO();
				mv.setMem_id(memId);
				mv.setMem_name(memName);
				mv.setMem_tel(memTel);
				mv.setMem_addr( memAddr);
				
				memList.add(mv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "UPDATE MYMEMBER " + 
						 "SET MEM_NAME = ? , " + 
					     "	  MEM_TEL = ? , " + 
					     "	  MEM_ADDR = ? " + 
					     "WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMem_name());
			pstmt.setString(2, mv.getMem_tel());
			pstmt.setString(3, mv.getMem_addr());
			pstmt.setString(4, mv.getMem_id ());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0; 
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			
			// 의미없는 1=1을 쓰는 이유 뒤에 조건들을 AND로 시작하기위해서 (동적으로만들수 있다)
			String sql = " SELECT * FROM MYMEMBER WHERE 1=1 "; 
			if(mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				sql += " AND MEM_ID = ? ";
			}
			if(mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				sql += " AND MEM_NAME = ? ";
			}
			if(mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				sql += " AND MEM_TEL = ? ";
			}
			if(mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				sql += " AND MEM_ADDR LIKE '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			
			if(mv.getMem_id() != null && !mv.getMem_id().equals("")) {
				pstmt.setString(index++, mv.getMem_id());
			}
			if(mv.getMem_name() != null && !mv.getMem_name().equals("")) {
				pstmt.setString(index++, mv.getMem_name());
			}
			if(mv.getMem_tel() != null && !mv.getMem_tel().equals("")) {
				pstmt.setString(index++, mv.getMem_tel());
			}
			if(mv.getMem_addr() != null && !mv.getMem_addr().equals("")) {
				pstmt.setString(index++, mv.getMem_addr());
			}
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				MemberVO mv2 = new MemberVO();
				mv2.setMem_id(memId);
				mv2.setMem_name(memName);
				mv2.setMem_tel(memTel);
				mv2.setMem_addr( memAddr);
				
				memList.add(mv2);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return memList;
	}

}

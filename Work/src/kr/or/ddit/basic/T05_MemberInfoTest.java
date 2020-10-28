package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.JDBCUtil2;
import kr.or.ddit.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력			---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class T05_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/**
	 * 회원 정보를 삭제하기 위한 메서드 (입력받은 회원ID를 이용하여 삭제한다.)
	 */
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원ID를 입력하세요 >> ");
		String memId = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(memId + "회원정보 삭제 성공!");
			} else {
				System.out.println(memId + "회원정보 삭제 실패!");
			}
			
		} catch (SQLException e) {
			System.out.println(memId + "회원정보 삭제 실패!");
			e.printStackTrace();
		} finally {
			diconnect();
		}
		
		
	}

	/**
	 * 회원 정보를 수정하기 위한 메서드
	 */
	private void updateMember() {
		System.out.println();
		
		String memId = "";
		boolean chk = true;
		
		do {
			System.out.println("수정할 회원ID를 입력하세요 >> ");
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk == false) {
				System.out.println(memId + "회원은 없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력해 주세요.");
			}
		} while (chk == false);

		System.out.println("수정할 내용을 입력하세요");
		System.out.println("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("새로운 회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
	
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "UPDATE MYMEMBER " + 
						 "SET MEM_NAME = ? , " + 
					     "	  MEM_TEL = ? , " + 
					     "	  MEM_ADDR = ? " + 
					     "WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(memId + "회원의 정보를 수정했습니다.");
			} else {
				System.out.println(memId + "회원의 정보 수정 실패!!!");
			}
			
		} catch (SQLException e) {
			System.out.println(memId + "회원의 정보 수정 실패!!!");
			e.printStackTrace();
		} finally {
			diconnect();
		}
	
	}

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println(" ID\t이 름\t전화번호\t\t주  소");
		System.out.println("--------------------------------------------------");

		try {
			conn = JDBCUtil.getConnection();
			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t\t" + memAddr);
				
				
			}
			System.out.println("--------------------------------------------------");
			System.out.println("출력 작업 끝.");
			
		} catch (SQLException e) {
			System.out.println("회원 자료 가저오기 실패!!");
			e.printStackTrace();
		} finally {
			diconnect();
		}
		
		
	}

	/**
	 * 회원을 추가하는 메서드
	 */
	private void insertMember() {

		boolean chk = false;
		
		String memId;
		
		//등록할 사람이 존재하는지 판단하는것. 존재하면 새정보를 받는다.
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.println("회원  ID >> ");
			
			memId = scan.next();
			
			chk = getMember(memId);
			
			if(chk) {
				System.out.println("회원ID가 "+memId + "인 회원이 이미 존재합니다.");
				System.out.println("다시 입력해 주세요.");
			}
			
		} while (chk == true);
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		System.out.println("회원 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력 버퍼 비우기
		System.out.println("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil3.getConnection();
			String sql = "INSERT INTO MYMEMBER ( MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR ) "
						+"VALUES (?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공!");
			} else {
				System.out.println(memId + "회원 추가 작업 실패!");
			}
			
			
		} catch (SQLException e) {
			System.out.println(memId + "회원 추가 작업 실패!");
			e.printStackTrace();
		} finally {
			diconnect();
		}
		
	}
	
	/**
	 * 회원 ID를 이용하여 회원이 존재하는지를 알려주는 메서드
	 * @param memId
	 * @return true : 이미 존재함, false : 존재하지 않음.
	 */
	private boolean getMember(String memId) {
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
			diconnect();
		}
		
		return chk;
	}
	
	/**
	 * 자원반납용 메서드
	 */
	private void diconnect() {
		// 6. 종료(사용했던 자원을 모두 반납한다.)
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		
	}

	public static void main(String[] args) {
		T05_MemberInfoTest memObj = new T05_MemberInfoTest();
		memObj.start();
	}

}


















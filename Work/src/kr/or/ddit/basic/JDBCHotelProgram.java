package kr.or.ddit.basic;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

/*

호텔 운영을 관리하는 프로그램 작성.(DB 이용)
 - 키값은 방번호 
 
*/

public class JDBCHotelProgram {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	public JDBCHotelProgram() {
		scan = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) {
		JDBCHotelProgram jdbchp = new JDBCHotelProgram();
		jdbchp.roomCheckInStart();
	}
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	private static void dispalyMenu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.println("메뉴선택 =>");
	}
	
	/**
	 * 프로그램을 시작하는 메서드
	 */
	private void roomCheckInStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		
		while (true) {
			
			dispalyMenu();
			
			int input;
			try {
				input= scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[ERROR : 숫자만 입력해 주세요]");
				scan.nextLine();
				continue;
			}
			
			switch (input) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태
				roomCondition();
				break;
			case 4: // 업무종료
				hotelClose();
				return;
			default:
				System.out.println("메뉴에 해당하는 번호만 입력해주세요");
				break;
			}
			
		}
	}
	
	/**
	 * 호텔 문을 닫는 메서드
	 */
	private void hotelClose() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
		
	}


	/**
	 * 호텔 객실을 체크아웃하기 위한 메서드
	 */
	private void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		
		String roomNum = scan.next();
		
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			System.out.println("체크아웃 되었습니다.");
	}


	/**
	 * 호텔의 객실의 상태를 보기위한 메서드
	 */
	private void roomCondition() {
		
			System.out.println("체크인 한 방이 없습니다.");
	}
	
	
	private void roomCheck() {
		
		
		
	}
	
	
	/**
	 * 호텔에 체크인하기 위한 메서드
	 */
	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		String roomNum = scan.next();
		
		System.out.println(roomNum+"방에는 이미 사람이 있습니다.");
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("이름 입력 =>");
		
		String guestName = scan.next();
		
		
		System.out.println("체크인 되었습니다.");
		
	}
	
	
}



/**
 * 호텔 방의 정보
 */
class RoomVO implements Serializable {
	
	private String roomNum; // 방번호
	private String guestName; // 투숙객 이름
	
	public RoomVO(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.guestName = name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return guestName;
	}

	public void setName(String name) {
		this.guestName = name;
	}

	@Override
	public String toString() {
		return "방번호 : " + roomNum + ", 투숙객 : " + guestName ;
	}
}

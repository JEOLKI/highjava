package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * 문제)

호텔 운영을 관리하는 프로그램 작성.(Map이용)
 - 키값은 방번호 
 
실행 예시)

**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************

 */
public class HotelProgram {
	
	private Scanner scan;
	private Map<String, Room> roomMap;
	
	public HotelProgram() {
		scan = new Scanner(System.in);
		roomMap= new HashMap<String, Room>();
	}
	
	
	public static void main(String[] args) {
		HotelProgram hp = new HotelProgram();
		hp.roomCheckInStart();
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
				scan.nextInt();
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
				close();
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
	private void close() {
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
		
		if(roomMap.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}
	}


	/**
	 * 호텔의 객실의 상태를 보기위한 메서드
	 */
	private void roomCondition() {
		Set<String> keySet = roomMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("체크인 한 방이 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while (it.hasNext()) {
				cnt++;
				String roomNum = it.next();
				Room room = roomMap.get(roomNum);
				System.out.println("방번호 : " + room.getRoomNum()+ ", 투숙객 :" + room.getName() );
			}
		}
	}


	/**
	 * 호텔에 체크인하기 위한 메서드
	 */
	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		
		Scanner scan = new Scanner(System.in);
		String roomNum = scan.next();
		
		if(roomMap.get(roomNum) != null) {
			System.out.println(roomNum+"방에는 이미 사람이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("이름 입력 =>");
		
		String name = scan.next();
		
		roomMap.put(roomNum, new Room(roomNum, name));
		
		System.out.println("체크인 되었습니다.");
		
		
	}

}

class Room {
	
	private String roomNum;
	private String name;
	
	public Room(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
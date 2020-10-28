package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class HotelIOProgram {
	
	private Scanner scan;
	private Map<String, RoomInfo> roomMap; // 룸의 정보를 담은 Map
	
	public HotelIOProgram() {
		scan = new Scanner(System.in);
		roomMap= new HashMap<String, RoomInfo>();
	}
	
	public static void main(String[] args) {
		HotelIOProgram hio = new HotelIOProgram();
		hio.roomCheckInStart();
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
	 * 기존의 자료를 읽어오는 메서드
	 */
	private void input() {

		// 입력용 스트림 객체 생성
		try {
			ObjectInputStream ois = 
					new ObjectInputStream( // 보조
							new BufferedInputStream( 
									// 보조 : 버퍼의크기를 지정하지 않으면 기본크기 8192bytes로 됨 -> flush()는 close()시 자동호출
									new FileInputStream("d:/D_Other/hotelObj.bin"),1024)); // 기본

			Object obj = null;

			try {
				while ((obj = ois.readObject()) != null) {
					//읽어온 데이터를 roomMap으로 변환후 대입
					roomMap = (Map<String, RoomInfo>)obj;
				}

				ois.close();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			//e.printStackTrace(); // 더이상 읽어올 객체가 없으면 예외 발생함.
		}
	}
	
	/**
	 * 프로그램을 시작하는 메서드
	 */
	private void roomCheckInStart() {
		input(); // 기존 자료 가져오기
		
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
	 * roomMap을 출력하기 위한 메서드
	 */
	private void outPut() {
		// roomMap 출력용 스트림
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream( // 보조
							new BufferedOutputStream( // 보조
									new FileOutputStream("d:/D_Other/hotelObj.bin"))); // 기본

			// roomMap 출력
			oos.writeObject(roomMap); 

			oos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 호텔 문을 닫는 메서드
	 */
	private void hotelClose() {
		outPut(); // 자료 저장하는 메서드
		
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
			while (it.hasNext()) {
				String roomNum = it.next();
				RoomInfo roomInfo = roomMap.get(roomNum);
				System.out.println(roomInfo);
			}
		}
	}


	/**
	 * 호텔에 체크인하기 위한 메서드
	 */
	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		
		String roomNum = scan.next();
		
		if(roomMap.get(roomNum) != null) {
			System.out.println(roomNum+"방에는 이미 사람이 있습니다.");
			return;
		}
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.println("이름 입력 =>");
		
		String guestName = scan.next();
		
		RoomInfo ri = new RoomInfo(roomNum, guestName);
		
		roomMap.put(roomNum, ri);
		
		System.out.println("체크인 되었습니다.");
		
	}
}

/**
 * 호텔 방의 정보
 */
class RoomInfo implements Serializable {// 구현했는지 안했는지 체크하기위한 인터페이스이다. // 직렬화(Serialization)
	
	// 직렬화가 되지 않을 멤버변수는 transient 와 static으로 지정 (참조형 변수 :null, 숫자형 변수 : 0)
	
	private String roomNum; // 방번호
	private String guestName; // 투숙객 이름
	
	public RoomInfo(String roomNum, String name) {
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
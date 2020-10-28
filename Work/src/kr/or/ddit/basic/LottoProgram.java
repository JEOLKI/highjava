package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
      출력한다.)

	==========================
         Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			
	받은 금액은 2500원이고 거스름돈은 500원입니다.
			
   	 ==========================
         Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다
 */
public class LottoProgram {
	
	Scanner sc = new Scanner(System.in);
	
	int[] lottoNum = new int[6];
	
	public static void main(String[] args) {
		new LottoProgram().start();
	}
	
	/**
	 * Lotto 프로그램을 시작하는 메서드
	 */
	public void start() {
		while(true) {
			System.out.println("==========================");
			System.out.println("     Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println(" 1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("==========================");
			
			
			int input;
			try {
				input= sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("[ERROR : 숫자만 입력해 주세요]");
				sc.nextLine();
				continue;
			}
			
			switch (input) {
			case 1:
				buyLotto();
				break;
			case 2:
				System.out.println("감사합니다.");
				return;
			default:
				System.out.println("1~2번에 해당하는 번호를 입력해주세요");
				continue;
			}
		}
	}
	
	/**
	 * 로또를 구입하는 메서드
	 * 구매금액을 돌려받아 횟수만큼 로또를 판매하고 거스름돈을 준다.
	 */
	private void buyLotto() {
		
		System.out.println(" Lotto 구입 시작");
		System.out.println(" 1000원에 로또번호 하나입니다.");
		
		int money;
		while (true) {
			System.out.println(" 금액 입력");
			try {
				money= sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("[ERROR : 숫자만 입력해 주세요]");
				sc.nextLine();
				continue;
			}
		}
		createLotto(money);
	}
	
	/**
	 * 로또를 만드는 메서드
	 * @param money 구입금액
	 */
	private void createLotto(int money) {
		
		int count = money/1000;
		int change = money%1000;
		
		for (int i = 0; i < count; i++) {
			getLottoNum();
			System.out.println("로또번호"+(i+1)+" : " +lottoNum[0]+","+lottoNum[1]+","+lottoNum[2]+","+lottoNum[3]+","+lottoNum[4]+","+lottoNum[5]);
		}
		
		System.out.println("받은금액은"+money+"원이고 거스름돈은"+change+"원 입니다.");
	}
	
	/**
	 * 로또번호를 랜덤하게 만드는 메서드
	 */
	private void getLottoNum() {
		Set<Integer> lottoNumSet = new HashSet<>();
		
		//로또번호 6자리를 랜덤으로 만들기
		while(lottoNumSet.size() != 6) {
			lottoNumSet.add((int)(Math.random()*45+1));
		}
		
		List<Integer> lottoNumList = new ArrayList<Integer>(lottoNumSet);
		
		Collections.sort(lottoNumList);
		
		//로또번호 6자리를 배열에 저장하기
		Iterator<Integer> it = lottoNumList.iterator();
		
		int i = 0;
		while (it.hasNext()) {
			lottoNum[i++] = it.next().intValue(); 
		}
	}
}


package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초 안에 입력이 완료되면  승패를 출력한다.
 * 
 * 결과예시)
 * === 결과  ===
 * 컴퓨터 : 가위
 * 당   신 : 바위
 * 결   과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {

	public static boolean inputCheck = false; 
	
	/**
	 * 승리자를 정하는 메서드
	 * @param you
	 * @param com
	 */
	public void whoIsWinner(int you, int com) {
		System.out.print("결   과 : ");
		if(you == com) {
			System.out.println("비겼습니다.");
		} else if(you==0 & com==1) {
			System.out.println("컴퓨터가 이겼습니다.");
		} else if(you==1 & com==0) {
			System.out.println("당신이 이겼습니다.");
		} else if(you==1 & com==2) {
			System.out.println("컴퓨터가 이겼습니다.");
		} else if(you==2 & com==1) {
			System.out.println("당신이 이겼습니다.");
		} else if(you==2 & com==0) {
			System.out.println("컴퓨터가 이겼습니다.");
		} else if(you==0 & com==2){
			System.out.println("당신이 이겼습니다.");
		} 
	}
	
	public static void main(String[] args) {
		RockPaperScissorsInput rpsi = new RockPaperScissorsInput();
		Thread th2 = new CountDown5s();
		
		rpsi.start();
		th2.start();
		
		try {
			rpsi.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("=== 결과 ===");
		RockPaperScissorsComputer rps = new RockPaperScissorsComputer();
		rps.computer();
		rpsi.getStr();
	
		T07_ThreadGame tg = new T07_ThreadGame();
		tg.whoIsWinner(rpsi.you(), rps.com());
		
	}
}

/**
 * 가위바위보 클래스
 */
class RockPaperScissors {
	
	List<String> RockPaperScissors = new ArrayList<>();
	
	public RockPaperScissors() {
		RockPaperScissors.add("바위");
		RockPaperScissors.add("보");
		RockPaperScissors.add("가위");
	}
}

/**
 * 컴퓨터의 가위바위보 클래스
 */
class RockPaperScissorsComputer {
	
	RockPaperScissors rps = new RockPaperScissors();
	int random;
	
	public void computer() {
		random = (int)(Math.random()*3);
		
		System.out.println("컴퓨터 : " + rps.RockPaperScissors.get(random));
	}
	
	public int com() {
		return random;
	}
}


/**
 *	당신의 가위바위보 클래스
 */
class RockPaperScissorsInput extends Thread {
	
	public String str;
	public RockPaperScissors rps = new RockPaperScissors();
	
	@Override
	public void run() {
		str = JOptionPane.showInputDialog("가위 바위 보!");
		
		T07_ThreadGame.inputCheck = true;
	}
	
	public void getStr() {
		System.out.println("당   신 : " + str);
	}
	
	public int you() {
		return rps.RockPaperScissors.indexOf(str);
	}
}


/**
 *	5초 카운트 다운 클래스
 */
class CountDown5s extends Thread {
	
	@Override
	public void run() {
		
		for (int i = 5; i >= 1 ; i--) {
		
			if (T07_ThreadGame.inputCheck) { // 입력체크
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램 종료
	}
}

/*
package basic;
import javax.swing.JOptionPane;

public class T07_ThreadGame {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용하여 컴퓨터의 가위 바위 보를 정한다.
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random()*3); // 0~2사이의 난수 만들기
		String com = data[index];
		
		// 사용자로 부터 가위, 바위, 보 입력 받기
		String man = null;   // 사용자의 가위바위보가 저장될 변수
		
		// 카운트 다운 쓰레드 실행
		gt.start();
		
		do{
			man = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요");
		}while(!man.equals("가위") && !man.equals("바위") && !man.equals("보"));
		
		inputCheck = true;  // 입력이 완료됨을 알려주는 변수값을 변경한다.
		
		// 결과 판정하기
		String result = "";
		if( man.equals(com) ){
			result = "비겼습니다.";
		}else if( (man.equals("가위") && com.equals("보"))
				 || (man.equals("바위") && com.equals("가위"))
				 || (man.equals("보") && com.equals("바위")) ){
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}
		
		// 결과 출력
		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당  신 : " + man);
		System.out.println("결  과 : " + result);
	}

}

class GameTimer extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1; i--){
			if(T07_ThreadGame.inputCheck==true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
		
	}
}

*/
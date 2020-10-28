package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
	10마리의 말들이 경주하는 경마 프로그램 작성하기

	말은 Horse라는 이름의 클래스로 구성하고,
	이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
	그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
	기능이 있다.( Comparable 인터페이스 구현)

	경기 구간은 1~50구간으로 되어 있다.

	경기 중 중간중간에 각 말들의 위치를 나타내시오.
	예)
	1번말 --->------------------------------------
	2번말 ----->----------------------------------
	...

	경기가 끝나면 등수 순으로 출력한다.

 */
public class HorseRacing {
	
	static List<Horse> horseList = new ArrayList<>();
	static String strRank = "";
	
	public static void main(String[] args) {
		
		horseList.add(new Horse("1번말", 0));
		horseList.add(new Horse("2번말", 0));
		horseList.add(new Horse("3번말", 0));
		horseList.add(new Horse("4번말", 0));
		horseList.add(new Horse("5번말", 0));
		horseList.add(new Horse("6번말", 0));
		horseList.add(new Horse("7번말", 0));
		horseList.add(new Horse("8번말", 0));
		horseList.add(new Horse("9번말", 0));
		horseList.add(new Horse("10번말", 0));
		
		
		for (int i = 0; i < horseList.size(); i++) {
			horseList.get(i).start();
		}
		
		for (int i = 0; i < horseList.size(); i++) {
			try {
				horseList.get(i).join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("경기 끝!!!!");
		System.out.println("------------------------------");
		System.out.println();
		
		Collections.sort(horseList);
		System.out.println("  [경기 결과]");
		for(Horse horse : horseList) {
			System.out.println(horse);
		}
	}
}


/**
 * 말 
 */
class Horse extends Thread implements Comparable<Horse>{
	private String horseName;
	private int rank;
	
	String[] track = new String[50];
	
	public void track() {
		for (int i = 0; i < track.length; i++) {
			track[i] = "-";
		}
	}
	
	public Horse(String horseName , int rank) {
		this.horseName = horseName;
		this.rank = rank;
	}

	public String getHorseName() {
		return horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return rank +"등\t" + horseName ;
	}

	@Override
	public void run() {
		track();
		for (int i = 0; i < track.length; i++) {
			
			if (i==0) {
				track[i] = ">";				
			} else {
				track[i-1] = "-";
				track[i] = ">";
			}
			String t = "";
			for (int j = 0; j < track.length; j++) {
				t += track[j];
			}
			System.out.println(horseName+"\t"+t);
			
			try {
				Thread.sleep((int)(Math.random()*301 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Rank r = new Rank();
		r.start();
		rank = r.rank;
		
		for (int i = 0; i < HorseRacing.horseList.size(); i++) {
			if(HorseRacing.horseList.get(i).getHorseName().equals(horseName)) {
				HorseRacing.horseList.get(i).setRank(rank);
			}
		}
	}
	
	@Override
	public int compareTo(Horse horse) {
		if(getRank()== horse.getRank()) {
			return 0;
		}else if(getRank() > horse.getRank()) {
			return 1;
		}else {
			return -1;
		}
	}
}


/**
 * 등수 쓰레드
 */
class Rank extends Thread {
	static int rank=1;
	
	@Override
	public void run() {
		rank++;
	}
}
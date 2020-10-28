package kr.or.ddit.basic;

public class T01_ThreadTest {
	public static void main(String[] args) { // 실행하는 순간 메인 쓰레드 한개생성
		// 싱글 쓰레드 프로그램
		for (int i = 0; i < 200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i = 0; i < 200; i++) {
			System.out.print("$");
		}
		
	}
}

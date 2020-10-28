package kr.or.ddit.basic;

public class TestClass {
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	
	private static final int LION = 0;
	private static final int TIGER = 1;
	
	
	public static void main(String[] args) {
		if (LION == 0) {  // 에러가 나지않지만 올바르지 않음 
			System.out.println("ZERO 입니다.");
		}
		
		
	}
}

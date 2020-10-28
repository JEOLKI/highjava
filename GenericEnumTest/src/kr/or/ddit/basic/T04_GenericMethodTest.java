package kr.or.ddit.basic;


class Util2 {
	public static <T extends Number> int compare(T t1, T t2) { // 넘버와 넘버를 상속받은 모든 타입은 가능하다.
		
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
				
		return Double.compare(v1, v2); // 크기비교를 반환 1, -1
	}
}



/**
 * 제한된 타입 파라미터(Bounded Type Parameter) 예제
 */
public class T04_GenericMethodTest {
	public static void main(String[] args) {
		
		int result1 = Util2.compare(10, 20); // AutoBoxing
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3); // AutoBoxing
		System.out.println(result2);
		
		//Util2.compare("c", "java"); // 에러발생  : String이기 때문에 Number를 상속받은것이 아니다.
		
	}
}

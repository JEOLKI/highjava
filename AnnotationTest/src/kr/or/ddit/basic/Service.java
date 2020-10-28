package kr.or.ddit.basic;

/**
 * 애너테이션 사용 예제
 */
public class Service {
	
	// 타겟이 메서드이기때문에 메서드에 사용해도 문제없다. 기본값인 -와 20으로 됨
	@PrintAnnotation 
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	// value="%" value한개만 있다면 생략할수 있다.
	@PrintAnnotation("%") 
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	// count도 있기때문에 value생략 불가
	@PrintAnnotation(value="#", count=25) 
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
	
}

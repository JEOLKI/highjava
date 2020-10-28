package kr.or.ddit.basic;


/**
 * 
 * 제너릭 클래스를 만드는 방법
 * 형식)
 *   class 클래스명<제너릭타입글자>{
 *   	제너릭타입글자 변수명; // 변수선언에 제너릭을 사용할 경우
 *   	...
 *   	제너릭 타입글자 메서드명(){ // 반환값이 있는 메서드에서 사용
 *   		...
 *   		return 값;
 *   	}
 *   	...
 *   }
 *   
 *  --- 제너릭 타입 글자 ---
 *  T => Type
 *  K => key
 *  V => value
 *  E => Element (자료구조에 들어가는 것들을 나타낼때 사용)
 *  
 */
public class T02_GenericTest {
	public static void main(String[] args) {
		
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String)ng1.getVal();
		System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("정수 반환값 irtnNg2 => " + irtnNg2);
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal();
		irtnNg2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : "+ rtnNg1);
		System.out.println("제너릭 정수형 반환값 : "+ irtnNg2);
	}
}


class NonGenericClass { // 캐스팅의 단점 // 원하지않은 객체가 들어올수도 있다.
	private Object obj;

	public Object getVal() {
		return obj;
	}

	public void setVal(Object obj) {
		this.obj = obj;
	}
}


class MyGeneric<T> { // 컴파일시에 타입이 저장되면 그 타입만 들어올 수 있다. // 타입안전한 코딩이 가능
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
}











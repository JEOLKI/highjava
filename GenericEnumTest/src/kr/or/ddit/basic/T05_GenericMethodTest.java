package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 	* 와일드 카드(Wild card) 예제
	<? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능
	<? super T>   => 와일드 카드의 하한 제한. T와 그 조상들만 가능
	<?>			  => 모든 타입 가능 <? extends Object>와 동일
	
	* 와일드 카드와 제한된 타입 파라미터 비교 :
	1. 동일한 파라미터 타입으로 강제하고 싶은 경우.
	   (타입 파라미터가 한개만 사용될 경우에는 둘 중 아무거나 사용해도 동일함.)
	   ex) public static <T extends Number> void copy(List<T> dest, List<T> src)
	   ex) public static <T extends Number, S extends Number>
	   			void copy(List<T> dest, List<S> src)
	       => 메서드의 파라미터타입을 동일한 타입으로 강제함. // 메서드 정의 클래스 설계 등 선언할때 정의할때 사용하는 문법
	       public static void copy(List<? extends Number> dest,
	     						   List<? extends Number> src)
	       => 동일 타입으로 강제하지 않음 // 해당 제너릭한 객체들을 지칭할때, 해당 타입을 정의할 때
	       // 사용시점에는 ? 가 있어도된다. 제너릭한 객체가 들어올 수 있다. 어떤타입이 들어올지 모를때 ?를 사용 // 제너릭객체를 지칭하기 위한 문법
	       // 사용시점에는 T 등이 정의 되어야 컴파일이 되니까 있으면 안된다. 
	    
	2. Type parameters는 하한 제한만 가능 (와일드 카드는 상한, 하한 가능)
	   ex) public void print(List<? super Integer> list) // OK
	       public <T super Integer> void print(List<T> list) // 에러

*/
public class T05_GenericMethodTest {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>(); // 과일상자
		FruitBox<Apple> appleBox = new FruitBox<>(); // 사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple()); // 사과 밖에 담지 못한다.
		
		//Juicer.makeJuice(fruitBox); // 과일상자인 경우에는 아무 문제 발생 하지 않는다.
		Juicer.makeJuice(appleBox);
		
	}
}

class Juicer {
	//static void makeJuice(FruitBox<Fruit> box) {

	// 과일상자 뿐만아니라 사과상자도 가능하게 해주기 위해서 제한된 타입 파라미터를 이용
	// 타입문자를 사용해서 선언한것 나중에 타입을 알려줘야한다.
	// 제한된 타입 파라미터 문법으로 해결.
	//static <T extends Fruit> void makeJuice(FruitBox<T> box) { 
	
	// 어떤타입이 오든지 가능하기 때문에 extends로 범위를 정해준다.
	//static <T> void makeJuice(FruitBox<T> box) { 
	
	// 객체를 와일드 카드로 지정해서 해결할수 있다.
	// 여러 객체가 들어올 수 있다. Fruit에 상속된 객체 타입이 들어올수 있다.
	static void makeJuice(FruitBox<? extends Fruit> box) { 
		
		String fruitListStr = "";	// 과일 목록
		
		int cnt = 0;
		for (Fruit f : box.getFruitList()) {
			if (cnt == 0) {
				fruitListStr += f;
			} else {
				fruitListStr += "," + f;
			}
			
			cnt++;
			
		}
		
		System.out.println(fruitListStr + "=> 쥬스 완성 !");
	}
}

/**
 * 과일
 */
class Fruit {
	private String name; // 과일이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 [이름 =" + name + "]";
	}
}

/**
 * 사과
 */
class Apple extends Fruit {
	
	public Apple() {
		super("사과");
	}
}

/**
 * 포도
 */
class Grape extends  Fruit {
	public Grape() {
		super("포도");
	}
}

/**
 * 과일 상자
 * @param <T>
 */
class FruitBox <T> {
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}

	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
	
}


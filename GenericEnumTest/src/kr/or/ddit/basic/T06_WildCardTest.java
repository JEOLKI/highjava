package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 제너릭 클래스를 이용한 객체생성 예제 
 */
public class T06_WildCardTest {
	public static void main(String[] args) {
		
		// FruitBox2<? extends Fruit> fruitBox1 = new FruitBox2<Fruit>();
		FruitBox2<?> fruitBox1 = new FruitBox2(); // 클래스 타입으로 올 수 있는것이 정해져있다.
		FruitBox2<?> fruitBox2 = new FruitBox2<>(); // 위와 동일. // 타입을 쓰지 않아도 저절로 Fruit로 된다.
		
		//FruitBox2<?>는 FruitBox2<? extends Fruit>를 의미함
//		FruitBox2<?> fruitBox3 = new FruitBox2<Object>(); // ?는 Fruit를 상속된것만 가능하기때문에 Object는 불가하다 
		
		// 두 타입(Object, Fruit)이  일치하지 않음
//		FruitBox2<Object> fruitBox4 = new FruitBox2<Fruit>(); 
		
		FruitBox2<?> friutBox5 = new FruitBox2<Fruit>();
		
		FruitBox2<? extends Fruit> friutBox6 = new  FruitBox2<Apple>(); // Apple은 상속받아서 가능하다.
		
		// new 연산자는 타입이 명확해야 객체생성을 할 수 있다. (와일드 카드 사용불가)
//		FruitBox2<? extends Object> fruitBox7 = new FruitBox2<? extends Object>();
	}
}


/**
 * 과일상자2
 * (제한된 타입파라미터를 이용한 제너릭 클래스 예제)
 * @param <T>
 */
class FruitBox2 <T extends Fruit> {
	
	List<T> itemList = new ArrayList<T>();

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
	
	private void addItem(T item) {
		this.itemList.add(item);
	}
	
}






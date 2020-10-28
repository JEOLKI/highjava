package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16_NoneSerializablePraentTest {
/*
	 부모클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
	 부모개체의 필드값 처리방법
	 
	1. 부모클래스가 Serializable인터페이스를 구현하도록 해야 한다.
	2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여 
		부모 객체의 필드값을 처리 할 수 있도록 직접 구현한다. 
*/
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializableTest.bin");
		
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		
		oos.writeObject(child); // 직렬화
		oos.flush(); // 생략 가능
		oos.close();
		
		FileInputStream  fis = new FileInputStream("d:/D_Other/nonSerializableTest.bin");
		
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Child child2 = (Child) ois.readObject(); // 역직렬화
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
	}
}

/**
 * Serializable을 구현하지 않은 부모클래스
 */
class Parent {
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}

/**
 * Serializable을 구현한 자식 클래스
 */
class Child extends Parent implements Serializable {

	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	/**
	 * 직렬화 될 때 자동으로 호출된다.
	 * (접근제한자가  private이 아니면 자동 호출 되지 않음)
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException { // 무조건 private 형식
		// ObjectOutputStream객체의 메서드를 이용하여 부모객체 필드값 처리.
		out.writeUTF(getParentName());; // 부모필드도 직렬화 대상으로 넣어서 하기위한 수동 기능
		
		out.defaultWriteObject(); // 기본기능
	}
	
	/**
	 * 역직렬화 될 때 자동으로 호출됨.
	 * (접근제한자가 private이 아니면 자동 호출되지 않음.)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		// ObjectInputStream객체의 메서드를 이용하여 부모객체 필드값 처리.
		setParentName(in.readUTF());
		
		in.defaultReadObject(); // 기본기능
	}
	
	
	
	
	
	
	
	
}
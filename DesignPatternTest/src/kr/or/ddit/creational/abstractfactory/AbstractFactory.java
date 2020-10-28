package kr.or.ddit.creational.abstractfactory;

// 추상메서드를 가지고 있기때문에 추상클래스
// 추상클래스는 객체생성을 못한다.
public abstract class AbstractFactory { 
	
	abstract Shape getShape(String shapeType);
	
}

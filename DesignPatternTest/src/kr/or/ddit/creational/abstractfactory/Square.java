package kr.or.ddit.creational.abstractfactory;

public class Square implements Shape{

	@Override
	public void draw() {
		System.out.println("정사각형을 그리고 있음");
	}

}

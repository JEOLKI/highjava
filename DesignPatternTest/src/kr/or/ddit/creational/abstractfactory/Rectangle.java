package kr.or.ddit.creational.abstractfactory;

public class Rectangle implements Shape{

	@Override
	public void draw() {
		System.out.println("직사각형을 그리고 있음");
	}

}

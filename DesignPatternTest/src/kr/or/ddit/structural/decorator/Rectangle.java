package kr.or.ddit.structural.decorator;

public class Rectangle implements Shape{

	@Override
	public void draw() {
		System.out.println("사각형을 그리고 있음.");
	}

}

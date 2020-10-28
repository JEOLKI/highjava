package kr.or.ddit.creational.factory;

public class Circle implements Shape{
	
	
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}
	
	
	@Override
	public void draw() {
		System.out.println("원을 만들고 있음");

	}

}
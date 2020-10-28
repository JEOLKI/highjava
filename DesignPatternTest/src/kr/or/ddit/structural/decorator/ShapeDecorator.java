package kr.or.ddit.structural.decorator;

public abstract class ShapeDecorator implements Shape{

	protected Shape decoratedShape;
	
	public ShapeDecorator(Shape decoratedShape) {
		this.decoratedShape = decoratedShape;
	}
	
/*	// 추상클래스 이기때문에 안해도됨 어짜피 오버라이드 해서 사용할것이기 때문에 //없어야 구현시 바로추가가능
	@Override
	public void draw() {
		decoratedShape.draw();
	}*/

}

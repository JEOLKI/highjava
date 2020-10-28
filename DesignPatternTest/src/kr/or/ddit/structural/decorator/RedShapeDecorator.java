package kr.or.ddit.structural.decorator;

public class RedShapeDecorator extends ShapeDecorator{

	public RedShapeDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {
		decoratedShape.draw(); // 원래 기능 호출
		setRedBoder(decoratedShape);
		
	}

	private void setRedBoder(Shape decoratedShape) {
		System.out.println("경계색을 빨간색으로 칠하고 있음.");
	}
	
}

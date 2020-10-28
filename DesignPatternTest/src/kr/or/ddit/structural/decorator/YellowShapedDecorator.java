package kr.or.ddit.structural.decorator;

public class YellowShapedDecorator extends ShapeDecorator{

	public YellowShapedDecorator(Shape decoratedShape) {
		super(decoratedShape);
	}

	@Override
	public void draw() {
		decoratedShape.draw();
		
		System.out.println("노란색 경계선으로 칠하고 있음.");
	}
	
}

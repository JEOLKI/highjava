package kr.or.ddit.structural.decorator;

public class DecoratorPattrenDemo {
	public static void main(String[] args) {
		
		Shape circle = new Circle();
		
		Shape redCircle = new RedShapeDecorator(new Circle());
		
		Shape redRectangle = new RedShapeDecorator(new Rectangle());
		
		// 기본원 그리기
		circle.draw();
		
		// 빨간색 경계선을 가진 원 그리기
		redCircle.draw();
		
		// 빨간색 경계선을 가진 사각형 그리기
		redRectangle.draw();
		
		// 기존소스를 바꾸지 않고 클래스를 추가하여 이용해야 유지보수에 좋다.
		Shape yellowcircle = new YellowShapedDecorator(new Circle());
		yellowcircle.draw();
		
	}
}

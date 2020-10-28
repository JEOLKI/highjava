package kr.or.ddit.creational.abstractfactory;

public class AbstractFactoryPatternDemo {
	public static void main(String[] args) {
		
		// shapeFactory 가져오기
		AbstractFactory shapeFactory = FactoryProducer.getFactory(false);
		
		Shape shape1 = shapeFactory.getShape("RECTANGLE");
		shape1.draw();
		
		Shape shape2 = shapeFactory.getShape("SQUARE");
		shape2.draw();
		
		// RoundedShapeFactory 가져오기
		AbstractFactory roundedFactory = FactoryProducer.getFactory(true);
		
		shape1 = roundedFactory.getShape("RECTANGLE");
		shape1.draw();
		
		shape2 = roundedFactory.getShape("SQUARE");
		shape2.draw();
		
		
		
	}
}

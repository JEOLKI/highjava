package kr.or.ddit.creational.factory;

public class ShapeFactory {
	
	public Shape getShape(String shapeType) {
		if(shapeType == null) {
			return null;
		}
		
		if(shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle(3); // 만 변경해주면 유지보수가 쉽게 가능하다.
		}else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		}else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		
		return null;
		
		
	}
	
}

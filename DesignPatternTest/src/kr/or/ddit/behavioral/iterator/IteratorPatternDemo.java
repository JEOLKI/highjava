package kr.or.ddit.behavioral.iterator;

public class IteratorPatternDemo {
	public static void main(String[] args) {
		
		NameRepository nemeRepository = new NameRepository();
		
		for(Iterator iter = nemeRepository.getIterator(); iter.hasNext();) {
			String name = (String) iter.next();
			System.out.println("이름 : " + name);
		}
		
	}
}

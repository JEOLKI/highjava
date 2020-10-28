package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*

	Java Reflection에 대하여...
	
	1. 리플렉션은 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
	
	2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.

	3. java.lang.Class의 주요 메서드
	 - getName(), getSuperclass(), getInterface(), getModifiers() 등
	
	4. java.lang.reflect 패키지의 주요 클래스
	 - Field, Method, Constructor, Modifier 등

*/
public class AnnotationTest {
	public static void main(String[] args) 
	throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(PrintAnnotation.id); // 상수값 출력
		
		// reflection 기능을 이용한 메서드 실행하기
		// 선언된 메서드 목록 가져오기
		Method[] declaredMethods = Service.class.getDeclaredMethods(); 
		// 서비스클래스에 대한 정보를 담은 객체를 얻어오기 
		// 클래스정보를 가진 객체에서 getDeclaredMethod => 선언한 메서드정보를 담은 메서드 객체 리턴
		// 메서드 정보를 담기위한 메서드클래스를 미리 정의해놓은것에 대입한다. => Java Reflection
		
		for(Method m : declaredMethods) {
			System.out.println(m.getName()); // 메서드명 출력
			PrintAnnotation pa = m.getDeclaredAnnotation(PrintAnnotation.class); 
			// 메서드내에 Annotation이 있다면 가져오기 타입 어노테이션클래스타입의
			
			for(int i=0; i<pa.count(); i++) { // count값만큼
				System.out.print(pa.value()); // value값 출력
			}
			System.out.println(); // 줄바꿈

			// 방법1) new 이용한 객체 생성
			//m.invoke(new Service());
			
			// 방법2) reflection이용한 객체 생성 // 가급적이면 필요하지않으면 사용하지 않는다.
			Class<Service> clazz = Service.class; 
			// java reflection을 이용해서 클래스정보를 가져왔다. 서비스타입의 객체정보를 갖는

			try {
				Service service = (Service) clazz.newInstance(); //객체를 만드는것.
				m.invoke(service); // 메서드를 실행할수 있다.
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}
	}
}

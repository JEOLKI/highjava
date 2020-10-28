package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.Collections;

/**
 * 와일드 카드 타입 예제
 */
public class T07_WildCardTest {
	/**
	 * 모든 과정 등록
	 */
//	public static <T> void registerCourse(Course<T> course) { // 제너릭 객체가 들어온다. 제너릭타입 선언사용
	public static void registerCourse(Course<?> course) { // 제너릭 객체가 들어온다. 와일드카드 사용.. 의미는 동일하다.
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생 과정 등록
	 * @param course
	 */
	public static void registerCourseStudent(Course<? extends Student> course) { // Student, highStudent
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 직장인 과정 등록
	 */
	public static void registerCourseWorker(Course<? super Worker> course) { // Worker, Person
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course<>("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workCourse = new Course<>("직장인과정", 5);
		workCourse.add(new Worker("직장인1"));
		
		Course<Student> studentCourse = new Course<>("학생과정", 5);
		studentCourse.add(new Student("학생1"));
		studentCourse.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCourse = new Course<>("고등학생과정", 5);
		highStudentCourse.add(new HighStudent("고등학생1"));
		
		registerCourse(personCourse); // 일반인과정
		registerCourse(workCourse);   // 직장인과정
		registerCourse(studentCourse); // 학생과정
		registerCourse(highStudentCourse); // 고등학생과정
		System.out.println("-----------------------------------");
		
//		registerCourseStudent(personCourse); // 일반인과정 => 에러
//		registerCourseStudent(workCourse);   // 직장인과정 => 에러
		registerCourseStudent(studentCourse); // 학생과정
		registerCourseStudent(highStudentCourse); // 고등학생과정
		System.out.println("-----------------------------------");
	
		registerCourseWorker(personCourse); // 일반인과정
		registerCourseWorker(workCourse);   // 직장인과정
//		registerCourseWorker(studentCourse); // 학생과정 => 에러
//		registerCourseWorker(highStudentCourse); // 고등학생과정 => 에러
		
		
	}
	
}

/**
 * 사람
 */
class Person {
	String name;
	
	public Person(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "이름 : " + name;
	}
}

/**
 * 근로자
 */
class Worker extends Person {
	public Worker(String name) {
		super(name);
	}
}

/**
 * 학생
 */
class Student extends Person {
	public Student(String name) {
		super(name);
	}
}

/**
 * 고등학생 
 */
class HighStudent extends Student {
	public HighStudent(String name) {
		super(name);
	}
}

/**
 * 수강 코스
 * @param <T>
 */
class Course<T> {
	private String name; // 코스명
	private T[] students; // 수강생(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;
		
		 // 제너릭 배열 생성실패 (new 연산자는 생성할 객체의 타입이 명확히 정의 되어야 객체 생성 가능.)
//		students = new T[capacity];

		// 타입 파라미터로 배열을 생성시 오브젝트 배열을 생성후, 타입 파라미터 배열로 캐스팅 처리 해야함.
		students = (T[])(new Object[capacity]);
	}
	
	/**
	 * 코스명 조회
	 */
	public String getName() {return name;}
	
	/**
	 * 수강생 조회
	 */
	public T[] getStudents() {return students;}
	
	/**
	 * 수강생 등록
	 */
	public void add(T t) {
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null) { // 아직 등록되지 않은 빈자리인지..
				students[i] = t;
				break;
			}
		}
	}
}


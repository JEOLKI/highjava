package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는  Student클래스를 만든다.
 * 		생성자는  학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 
 * 		이 Student객체들은 List에 저장하여 관리한다.
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 하시오.)
 * 		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 
 * 		  총점 정렬기준은 외부 클래스에서 제공하도록 한다.
 */
public class T08_StudentTest {
	public static void main(String[] args) {
		
		List<Student> studList = new ArrayList<>();
		studList.add(new Student("1", "홍길동", 100, 20, 30));
		studList.add(new Student("4", "변학도", 10, 20, 30));
		studList.add(new Student("3", "성춘향", 10, 40, 30));
		studList.add(new Student("2", "이순신", 80, 100, 30));
		studList.add(new Student("6", "강감찬", 100, 30, 20));
		studList.add(new Student("5", "일지매", 30, 20, 30));

		//등수구하기
//		for (int i = 0; i < studList.size(); i++) {
//		    int rank = 1;
//			for (int j = 0; j < studList.size(); j++) {
//				if(studList.get(i).getSumScore() < studList.get(j).getSumScore()) {
//					rank++;
//				}
//			}
//			studList.get(i).setRank(rank);
//		}
		
		setRanking(studList); // 순위정보 설정
		
		System.out.println("==== 정렬전 ====");
		for(Student s : studList) {
			System.out.println(s);
		}
		System.out.println();
		
		Collections.sort(studList); // 학번 정렬
		System.out.println("==== 학번의 오름차순 정렬 ====");
		for(Student s : studList) {
			System.out.println(s);
		}
		System.out.println();
		
		Collections.sort(studList, new SumScoreSortDesc()); // 총점 정렬후 학번 정렬
		System.out.println("==== 총점 내림차순 정렬후 동일의 경우 학번의 내림차순 정렬 ====");
		for(Student s : studList) {
			System.out.println(s);
		}
		System.out.println();
		
	}
	
	/**
	 * 순위 정보를 입력해주는 메서드
	 */
	public static void setRanking(List<Student> studList) {
		for(Student std : studList) { // 등수를 구할 자료(기준자료)
			int rank = 1;
			for(Student std2 : studList) { // 비교할 자료
				if(std.getSumScore() < std2.getSumScore()) {
					rank++;
				}
			}
			std.setRank(rank);
		}
	}
}


/**
 * 총점의 역순으로 정렬하는데 총점이 같으면 학번의 내림차순으로 정렬되도록 한다.
 */
class SumScoreSortDesc implements Comparator<Student> {

	@Override
	public int compare(Student stu1, Student stu2) {
		
		/*
		if(stu1.getSumScore() > stu2.getSumScore()) {
			return 1;
		} else if (stu1.getSumScore() == stu2.getSumScore()) {
			return stu1.getStudId().compareTo(stu2.getStudId()) * -1;
		} else {
			return -1;
		}
		*/
		
		if(stu1.getSumScore() == stu2.getSumScore()) {
			return stu1.getStudId().compareTo(stu2.getStudId()) * -1;
		} else {
			return new Integer(stu1.getSumScore()).compareTo(stu2.getSumScore()) * -1;
		}
		
	}

	
}

/**
 * 학생정보를 저장할 VO
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬되도록 한다.
 */
class Student implements Comparable<Student> {
	private String studId;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int sumScore;
	private int rank;
	
	public Student(String studId, String name, int korScore, int engScore, int mathScore) {
		super();
		this.studId = studId;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.sumScore = korScore + engScore + mathScore; //총점의 정보를 입력하는 것
	}

	public String getStudId() {
		return studId;
	}

	public void setStudId(String studId) {
		this.studId = studId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getSumScore() {
		return sumScore;
	}

	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [studId=" + studId + ", name=" + name + ", korScore=" + korScore + ", engScore=" + engScore
				+ ", mathScore=" + mathScore + ", sumScore=" + sumScore + ", rank=" + rank + "]";
	}
	
	//이름을 기준으로 오름차순 정렬이 되도록 설정한다.
	@Override
	public int compareTo(Student s) {
		return getStudId().compareTo(s.getStudId());
	}
	
	
	
	
}
package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class T04_ArrayListTest {
	public static void main(String[] args) {
		// 문제 1) 5명의 별명을 입력하여 ArrayList에 저장하고
		//		 별명의 길이가 제일 긴 별명을 출력하시오.
		//      (단, 각 별명의 길이는 모드 다르게 입력한다.)
		
		
		// 문제2) 문제1에서 별명의 길이가 같은것을 여러개 입력했을때에도 처리하시오
		
		Scanner sc = new Scanner(System.in);
		
		List<String> nicknameList = new ArrayList<>();
		
		System.out.println("5명의 별명을 입력해주세요");
		for (int i = 0; i < 5; i++) {
			System.out.println((i+1)+ "째 별명을 입력해주세요");
			String nickname = sc.next();
			nicknameList.add(nickname);
		}
		

		//제일큰사이즈를 구하고 그 사이즈의 이름을 출력해주면 된다.
		int max = nicknameList.get(0).length();
		
		for (int i = 0; i < nicknameList.size(); i++) {
			if(max < nicknameList.get(i).length()){
				max = nicknameList.get(i).length();
			} 
		}

		System.out.println("제일긴 별명은?");
		for (int i = 0; i < nicknameList.size(); i++) {
			if(max == nicknameList.get(i).length()) {
				System.out.println(nicknameList.get(i));
			}
		}
	}
}

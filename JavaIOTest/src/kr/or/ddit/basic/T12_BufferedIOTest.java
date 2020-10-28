package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * 성능향상을 위한 보조스트림 예제
 * (문자기반의 Buffered스트림 사용 예제)
 */
public class T12_BufferedIOTest {
	public static void main(String[] args) {
		
		// 문자 기반의 Buffered스트림 사용 예제
		try {
			// 이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는
			// 해당 '프로젝트폴더'가 기본 위치가 된다. => 상대 경로
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
			// 절대경로 => "D:\\A_TeachingMaterial\\3.HighJava\\workspace\\JavaIOTest\\src\\kr\\or\\ddit\\basic\\T11_BufferedIOTest.java"

//			int c;
//			
//			while ((c=fr.read()) != -1 )  {
//				System.out.print((char)c);
//			}
			
			
			// 한줄씩 읽을 수 있도록 해주는 readLine을 이용하기 위해 BufferedReader 사용.
			BufferedReader br = new BufferedReader(fr);
			String temp = "";
			for (int i = 1; (temp = br.readLine()) != null ; i++) { // 한줄씩가져와서 null 인지 아닌지 체크한다.
				System.out.printf("%4d : %s\n", i, temp);
			}
			
			br.close(); // 보조 스트림만 닫아도 됨.
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

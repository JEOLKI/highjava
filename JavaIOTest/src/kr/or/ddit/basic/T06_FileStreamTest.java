package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 */
public class T06_FileStreamTest {
	public static void main(String[] args) {
		// 파일에 출력하기 // 바이트 기반
		FileOutputStream fos = null;
		
		try {
			// 출력용 OutputStream객체 생성
			fos = new FileOutputStream("d:/D_Other/out.txt"); 
			for (char ch = 'a';  ch < 'z'; ch++) {
				fos.write(ch); // 바이트 기반
			}
			System.out.println("파일에 쓰기 작업 완료...");
			
			// 쓰기 작업 완료 후 스트림 닫기
			fos.close();
			//================================
			FileInputStream fis = new FileInputStream("d:/D_Other/out.txt");
			
			int c;
			while ((c=fis.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

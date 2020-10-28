package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
	public static void main(String[] args) {
		// 입출력 성능 향상을 위해서 버퍼를 이용하는 보조 스트림.
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가 8192bytes(8kb)로 설정된다.
			
			// 버퍼 크기가 5인 보조스트림 객체 생성 => 클수록 메모리 할당이 커진다.
			bos = new BufferedOutputStream(fos, 5); // 기본스트림 fos
			// 숫자 자체를 문자로 저장하기 위해 ''사용함
			for (int i = '1'; i <= '9'; i++) {
				bos.write(i); 
			// 1~5까지 버퍼가 차면 파일에 write후 비어지고 나머지도 6~9까지 차있으면 1개가 비어있으므로 기다리게된다.
			// 기다리기때문에 flush()메서드를이용하여 버퍼에 있는것을 모두 비우며 강제적으로 write 시킨다.
			}
			 
			bos.flush(); // 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
						 // close시 자동으로 호출됨 => 명시적으로 사용하지 않고 close만 해도 저절로 된다.
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9}; // 모든데이터는 바이트
		byte[] outSrc = null;
		
		// 스트림 선언 및 객체 생성 // 스트림은 단방향 처리만 되기 때문에 input용과 output용을 각각 만들어 줘야한다.
		ByteArrayInputStream bais = null; // 스트림 선언 (input) : 입력받을 때 사용 (프로그램기준)
		bais = new ByteArrayInputStream(inSrc); // 객체 생성 : 생성자로 바이트 배열데이터를 알려준다.
												// 읽어야할 데이터를 생성자로 입력받는다.
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // (output):출력할 때 사용
		// 바이트 배열을 스트림자신에게 출력
		
		int data; // 읽어온 자료를 저장할 변수
		// -1까지 처리하기 위해서는 int값이 필요하다. 
		
		
		// read()메서드 => byte단위로 자료를 읽어와 int형으로 반환한다.
		//		     => 더 이상 읽을 자료가 없으면 -1을 반환한다.
		while ((data = bais.read()) != -1) { // read => 1바이트씩 읽은 데이터가 리턴이된다.
			baos.write(data); // 출력하기 : 아웃풋스트림안에 모아지고 있다.
		}
		
		// 출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		try {
			// 스트림 객체 닫아주기 
			bais.close();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

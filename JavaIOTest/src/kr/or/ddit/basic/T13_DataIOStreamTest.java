package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 기본타입 입출력 보조 스트림 예제
 */
public class T13_DataIOStreamTest {
	public static void main(String[] args) {
		
		try {
			FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
			
			// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
			DataOutputStream dos = new DataOutputStream(fos); // fos에 데이터를 출력(저장)하기위함
			
			dos.writeUTF("홍길동");   	// 문자열 데이터 출력(UTF-8)
			dos.writeInt(17);	    	// 정수형으로 데이터 출력
			dos.writeFloat(3.14f); 		// 실수형(float)으로 출력
			dos.writeDouble(3.14);  	// 실수형(Double)으로 출력
			dos.writeBoolean(true); 	// 논리형으로 출력
			System.out.println("출력 완료...");
			
			dos.close();
			//=============================================
			// 출력한 자료 읽어오기
			FileInputStream fis = new FileInputStream("d:/D_Other/test.dat");
			
			DataInputStream dis = new DataInputStream(fis); 
			
			// 타입별로 바이트 사이즈가 다르기 때문에 메서드가 나눠있다.
			System.out.println("문자열 자료 : " + dis.readUTF());
			System.out.println("정수형 자료 : " + dis.readInt());
			System.out.println("실수형(Float) 자료 : " + dis.readFloat());
			System.out.println("실수형(Double) 자료 : " + dis.readDouble());
			System.out.println("논리형 자료 : " + dis.readBoolean());
			
			// 데이터 저장시 바이트가 순서대로 입력되기 때문에 읽을때도 순서대로 하지않으면 문제가 생기기때문에
			// 읽을때는 저장한 순서대로 읽어 주어야한다.
			
			dis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc;
		
		byte[] temp = new byte[4]; // 자료를 읽을때 사용할 배열 : 4byte씩 처리
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {

			// available() => 읽어올 수 있는  byte수를 반환
			while (bais.available() > 0) { // 0보다 크면 앞으로 읽을 데이터가 남아있다.
				
				
				/*
				bais.read(temp); // temp배열 크기만큼 자료를 읽어와 temp배열에 저장한다.
				baos.write(temp); // temp배열의 내용을 출력한다.
				// 이전의 남아 있는 쓰레기 데이터가 남아있다.
				*/
				
				/* console창
				temp : [0, 1, 2, 3]
				temp : [4, 5, 6, 7]
				temp : [8, 9, 6, 7]
				
				inSrc => [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
				outSrc => [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7]
				
				 */
				
				// 실제 읽어온 byte수를 반환한다.
				int len = bais.read(temp); // 4byte 씩읽다가 3번째 돌대는  8 9 만 읽고 len값이 2가된다.
				
				// temp 배열의 내용 중에서 0번째 부터 len개수만큼 출력하낟.
				baos.write(temp, 0, len); // (temp, offset:어디서 부터 읽을것인지, 읽을 개수);
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			
			System.out.println();
			outSrc = baos.toByteArray();
			System.out.println("inSrc => " + Arrays.toString(inSrc));
			System.out.println("outSrc => " + Arrays.toString(outSrc));
			
			bais.close();
			baos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}

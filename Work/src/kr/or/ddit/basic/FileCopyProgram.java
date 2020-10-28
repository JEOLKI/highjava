package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 'd:/D_Other/'에 있는 'Tulips.jpg'파일을
 *
 *	'복사본_Tulips.jpg'로 복사하는 프로그램을
 *
 *	작성하시오.
 */
public class FileCopyProgram {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			int data;
			
			while ((data = fis.read()) != -1) { 
				fos.write(data);
			}
			
			System.out.println("작업 완료...");
			
			fis.close();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

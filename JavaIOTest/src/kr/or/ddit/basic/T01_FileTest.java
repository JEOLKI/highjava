package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {
	public static void main(String[] args) throws IOException {
		// File객체 만들기 연습
		
		
		// 1. File(String 파일 또는 경로명)
		//    => 디렉토리와 디렉토리 사이 또는 디렉토리와 피일명 사이의 구분문자는 '\'를 사용하거나 '/'를 사용할 수 있다.
		
		File file = new File("d:\\D_Other\\test.txt"); // 파일의 객체를 만든것. 파일을 만든것이 아니다.
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile()); // 경로에 실제 파일이 있는지 판단한다.
		System.out.println("디렉토리(폴더)여부 : " + file.isDirectory()); // 폴더인지 판단한다.
		System.out.println("---------------------------------------");
		
		
		File file2 = new File("d:/D_Other");
		//File file2 = new File("d:/D_Other/test.txt");
		System.out.print(file2.getName() + "은 ");
		if (file2.isFile()) {
			System.out.println("파일");
		}else if (file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("---------------------------------------");
		
		
		// 2. new File(file parent, String child)
		//    => 'parent'디렉토리 안에있는 'child'파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt");
		System.out.println(file3.getName() + "의 용량 크기 : " + file3.length() + "bytes");
		
		
		// 3. new File(String parent, String child)
		File file4 = new File("\\D_Other\\test\\..", "test.txt"); // . => 현재위치(자신) .. => 하나 상위폴더
		System.out.println("절대경로 : " + file4.getAbsolutePath()); // 어떤파일의 위치를 절대적을 지정 // 단점 : 경로를 바꿀때마다 수정해 줘야한다.
		System.out.println("경로(생성자에 설정해 준 경로) : " + file4.getPath()); // 비교할수 있는 기준으로 어디에있는지
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		String path = T01_FileTest.class.getResource("").getPath(); // URL정보를 리턴해주는 메서드
		System.out.println(path); // 현재 클래스의 절대경로를 가져온다.
		
		
		/*
			디렉토리(폴더) 만들기
			 1. mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다. // make directory
			 			=> 중간의 경로가 모두 미리 만들어져 있어야 한다.
			 2. mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든후 마지막 위치의 디렉토리를 만들어 준다.
			 
			 => 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false 반환함.
			 
			 // 해당 폴더에 대한 생성할 수 있는 권한이 있어야한다.
		*/
		File file5 = new File("d:/D_Other/연습용"); // 다루기위한 파일 객체만 생성
		if (file5.mkdir()) { // 실질적을 디렉토리를 만들어 주는 메서드
			System.out.println(file5.getName() + " 만들기 성공 ! ");
		} else {
			System.out.println(file5.getName() + " 만들기 실패 ! ");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if (file6.mkdirs()) {
			System.out.println(file6.getName() + " 만들기 성공 ! ");
		} else {
			System.out.println(file6.getName() + " 만들기 실패 ! ");
		}
		System.out.println();
	
		
		
		
		
		
		
		
		
		
		
	}
}

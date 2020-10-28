package rmi.client;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import rmi.inf.RemoteInterface;
import rmi.vo.FileInfoVO;
import rmi.vo.TestVO;

public class RemoteClient {
/*
	클라이언트쪽의 프로젝트에도 서버의 패키지와 같은 구조로 Interface와 VO파일이 있어야 한다.
*/	
	public static void main(String[] args) {
		// Registry서버에 등록된 객체를 구한다.
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후 사용한 객체를 불러온다.
			// 해당 아이피와 구성요소들이 완전 똑같이 맞춰줘야한다.
			Registry reg = LocateRegistry.getRegistry("192.168.44.2", 8888); 
			// 등록한 레지스트리를 lookup해서 찾아서 사용한다.
			RemoteInterface clientInf = (RemoteInterface) reg.lookup("server"); 
			
			// 이제부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = clientInf.doRemotePrint("안녕하세요");
			System.out.println("반환값 : " + a);
			System.out.println("--------------------------------------");
		
			List<String> list = new ArrayList<String>();
			list.add("대전");
			list.add("대구");
			list.add("광주");
			list.add("인천");
			clientInf.doPrintList(list);
			System.out.println("List 호출 끝 ...");
			System.out.println("-------------------------------------");
			
			TestVO vo = new TestVO();
			vo.setTestId("홍길동");
			vo.setTestNum(123);
			clientInf.doPrintVo(vo);
			System.out.println("VO출력 메서드 호출 끝...");
			System.out.println("--------------------------------------");
			
			// 파일 전송하기
			File[] files = new File[2];
			files[0] = new File("d:/D_Other/이순신.jpg");
			files[1] = new File("d:/D_Other/ccc.jpg");
			
			FileInfoVO[] fInfo = new FileInfoVO[files.length];
			
			// 2의 파일을 읽어서 byte[]에 담아서 서버측 메서드에 전달하면 된다.
			FileInputStream fis;
			for (int i = 0; i < fInfo.length; i++) {
				int len = (int)files[i].length(); // 파일사이즈 구하기
				fis = new FileInputStream(files[i]);
				byte[] data = new byte[len]; // 파일사이즈를 구했기 때문에 한번에 가능
				
				fis.read(data); // 파일의 내용을 한번에 읽어서 byte배열에 저장
				
				fInfo[i] = new FileInfoVO();
				fInfo[i].setFileName(files[i].getName()); // 파일 이름 저장
				fInfo[i].setFileData(data); // 파일 데이터 저장
			}
			
			clientInf.setFiles(fInfo); // 서버의 파일 저장하는 메서드 호출
			System.out.println("파일 전송 작업 끝...");
			System.out.println("------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

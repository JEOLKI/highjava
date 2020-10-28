package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Tcp 방식은 연결이 되어야 한다.
public class TcpServer {
	public static void main(String[] args) throws IOException {
		
		// TCP 소켓 통신을 하기 위해 ServerSocket 객체 생성
		ServerSocket server = new ServerSocket(7777); // 7777 포트
		System.out.println("서버가 접속을 기다립니다...");
		
		// accept()메서드는 Client에서 연결요청이 올 때까지 계속 기다린다.!!!
		// 연결 요청이 오면 Socket객체를 생성해서  Client의 Socket과 연결한다. return
		Socket socket = server.accept(); 
		
		//------------------------------------------------
		// 이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : " + socket.getInetAddress());
		
		// Client에 메시지 보내기
		
		// OutputStream객체를 구성하여 전송한다.
		// 접속한 Socket의 getOutputStream()메서드를 이용하여 구한다.
		OutputStream out = socket.getOutputStream(); // 소켓을 통하여 스트림객체를 이용해서 데이터를 주고 받는다.
		DataOutputStream dos = new DataOutputStream(out); // 문자열을 쉽게 사용하기 위한 보조스트림 
		dos.writeUTF("어서오세요!!"); // 메세지 보내기
		System.out.println("메세지를 보냈습니다.");
		
		dos.close();
		server.close();

	}
}

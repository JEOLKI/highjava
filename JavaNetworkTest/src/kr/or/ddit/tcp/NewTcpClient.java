package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NewTcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("192.168.44.46", 7777); // ip주소를 변경하면 접속가능
		System.out.println("서버에 연결되었습니다...");
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
		
	}
}

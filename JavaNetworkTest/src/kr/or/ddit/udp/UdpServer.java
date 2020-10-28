package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

// 클라이언트에게 서버의 시간을 던져주기위함
public class UdpServer {
	private DatagramSocket socket;
	
	public void start() throws IOException {
		socket = new DatagramSocket(8888); // 포트 8888번을 사용하는 소켓을 생성한다.
		DatagramPacket inPacket, outPacket; // 패킷 송수신을 위한 객체변수 선언
		
		byte[] inMsg = new byte[1]; // 패킷 수신을 위한 바이트 배열 선언
		byte[] outMsg;				// 패킷 송신을 위한 바이트 배열 선언
		
		while (true) {
			// 데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			System.out.println("패킷 수신 대기 중...");
			
			// 패킷을 통해 데이터를 수신(receive)한다.
			socket.receive(inPacket);
			
			System.out.println("패킷 수신 완료.");
			
			// 수신한 패킷으로부터 client의 IP주소와 port번호를 가져온다. : 상대방에게 던져줄것임
			// 받는지 아닌지는 관심이 없다.
			InetAddress address = inPacket.getAddress();
			int port = inPacket.getPort();
			
			// 서버의 현재 시간을 시분초 형태([hh:mm:ss])로 변환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			outMsg = time.getBytes(); // 시간 문자열을  byte배열로 변환한다. //스트링을 바이트로 변환해주는 메서드
			
			// 패킷을 생성해서  client에게 전송(send)한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, address, port);
			socket.send(outPacket); // 전송 시작
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
	
}

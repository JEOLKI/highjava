package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	public static final int DEFAULT_BUFFER_SIZE = 10000;
	
	public static void main(String[] args) throws InterruptedException {
		String serverIp = "127.0.0.1";
		int port = 8888;
		
		File file = new File("d:/D_Other/복사본_Tulips.jpg");
		
		DatagramSocket ds = null;
		
		if (!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}
		
		long fileSize = file.length();
		long totalReadBytes = 0;
		
		double startTime = 0;
		
		try {
			ds = new DatagramSocket();
			InetAddress serverAddr = InetAddress.getByName(serverIp);
			startTime = System.currentTimeMillis();
			String str = "start"; // 전송 시작을 알려주기 위한 문자열 // 연결기반이 아니기때문에 사용자에게 시작을 알려주기위해 사용
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			ds.send(dp);
			
			FileInputStream fis = new FileInputStream(file);
			
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			
			str = String.valueOf(fileSize); // 파일 사이즈 정보를 알려주기 위해서...
			
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			ds.send(dp);
			
			while (true) {
				Thread.sleep(10); // 패킷 전송간의 시간 간격을 주기 위해서...
				int readBytes = fis.read(buffer, 0, buffer.length);
				
				if (readBytes == -1) {
					break;
				}
				
				// 읽어온 파일 내용 패킷에 담기
				dp = new DatagramPacket(buffer, readBytes, serverAddr, port);
				ds.send(dp);
				
				totalReadBytes += readBytes;
				System.out.println("진행중 : "+ totalReadBytes + "/" + fileSize + "Bytes ("
									+ (totalReadBytes * 100 / fileSize) + " %)");
			}
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime)/1000;
			double transferSpeed = (fileSize / 1000)/ diffTime;
			
			System.out.println("time : " + diffTime + " 초");
			System.out.println("평균 전송 속도 : " + transferSpeed + " KB/s");
			
//			str = "end"; // 전송이 완료 되었음을 알리기 위한 문자열
//			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
//			ds.send(dp);
			
			System.out.println("전송 완료...");
			fis.close();
			ds.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}

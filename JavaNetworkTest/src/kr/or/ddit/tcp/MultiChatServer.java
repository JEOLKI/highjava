package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// 서버소켓은 메인이 계속 만들어 주고 그 소켓을 기반으로한 아이오쓰레드가 별도로 작동함. 계속적으로 만들어진 소켓을 클라이언트 맵에 저장을 해놓고 
// 클라이언트가 메세지를 치면 서버에 들어와 서버는 그것을 다른 클라이언트에게 뿌려주는 역할을 한다.

public class MultiChatServer {
	// 대화명, 클라이언트의 Socket을 저장하기 위한 Map변수 선언
	Map<String, Socket> clients;
	
	// 생성자
	public MultiChatServer() {
		// 동기화 처리가 가능하도록 Map 객체 생성 // 동기화 synchronizedMap 메서드사용
		clients = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	// 비지니스 로직을 처리하는 메서드(서버 시작)
	public void serverStart() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while (true) { // 무한루프
				// 클라이언트 접속을 대기한다. // 접속을하면 소켓이 하나 만들어진다
				socket = serverSocket.accept();
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort()
									+ "] 에서 접속하였습니다.");
				
				// 메시지 전송 처리를 하는 쓰레드 생성 및 실행 // 해당 소켓에 해당하는 쓰레드 생성
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start(); // 실행 후 다시 무한루프
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 서버 소켓 닫기
			if (serverSocket != null) {
				try { serverSocket.close();} catch (IOException e) { }
				
			}
		}
	}
	
	// 대화방 즉, Map에 저장된 전체 유저에게 메시지를 전송하는 메서드
	public void sendToAll(String msg) {

		// Map에 저장된 유저의 대화명 리스트 추출(key값 구하기)
		Iterator<String> it = clients.keySet().iterator();
		
		while (it.hasNext()) {
			try {
				String name = it.next(); // 대화명(key값) 구하기
				
				// 대화명에 해당하는 Socket의 OutputStream객체 구하기
				DataOutputStream dos = new DataOutputStream(clients.get(name).getOutputStream());
				
				dos.writeUTF(msg); // 메세지 보내기
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	// 서버에서 클라이언트로 메시지를 전송할 Thread 클래스를 inner클래스로 정의
	// inner클래스에서는 부모 클래스의 멤버들을 직접 사용할 수 있다.
	// 외부에서 내부클래스를 사용할 일이없다. 내부에서만 사용하기때문에 불필요하게 외부에 도출할 필요가없다.
	class ServerReceiver extends Thread{ 
		Socket socket;
		DataInputStream dis; //UTF-8문자로 받기위해서 보조스트림사용 : readUTF()
		
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			
			try {
				dis = new DataInputStream(socket.getInputStream());  
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				// 서버에서는 클라이언트가 보내는 최초의 메시지 즉, 대화명을 수신해야 한다.
				
				name = dis.readUTF(); // 상대방이 writeUTF로 문자를 보내기전까지 블락된상태이며 쓰여지면 읽는다.
				// 클라이언트에서  접속하면 대화명을 보내주도록 설정할것임.
				
				// 대화명을 받아서 다른 모든 클라이언트 에게 대화방 참여 메시지를 보낸다.
				sendToAll("#" + name + "님이 입장했습니다.");
				
				// 대화명과 소켓정보를 Map에 저장한다. 처음접속시.
				clients.put(name, socket);
				System.out.println("현재 서버 접속자 수는  " + clients.size() + "명 입니다.");
				
				// 이 후의 메시지 처리는 반복문으로 처리한다.
				// 한 클라이언트가 보낸 메시지를 다른 모든 클라이언트에게 보내준다.
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				// 이 finally 영역이 실행된다는 것은 클라이언트의 접속이 종료되었다는 의미이다.
				sendToAll(name + "님이 나가셨습니다.");
				
				// Map에서 해당 대화명을 삭제한다.
				clients.remove(name);
				
				System.out.println("[" + socket.getInetAddress() +" : " + socket.getPort() +"] 에서 접속을 종료 했습니다.");
				
				System.out.println("현재 접속자 수는 " + clients.size() + "명 입니다.");
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		new MultiChatServer().serverStart(); //시작하는 메서드
	}
	
}

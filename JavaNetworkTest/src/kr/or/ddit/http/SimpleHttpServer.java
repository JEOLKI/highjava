package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleHttpServer {

	private static final Logger LOGGER = Logger.getLogger("SimpleHTTPServer");
	
	private final byte[] content;
	private final byte[] header;
	private final int port;
	private final String encoding;
	
	public SimpleHttpServer(String data , String encoding, String mimeType, int port) throws UnsupportedEncodingException {
	
		this(data.getBytes(encoding), encoding, mimeType, port);
		
	}

	// 웹브라우져에서 요청을 하면 헤더 정보를 미리 만드는것.
	public SimpleHttpServer(byte[] data , String encoding, String mimeType, int port) {
		this.content = data;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n"
				+ "Server: TestServer 1.0\r\n"
				+ "Content-length: "+ this.content.length + "\r\n"
				+ "Content-type: " + mimeType + "; charset=" + encoding + "\r\n\r\n";
		this.header = header.getBytes(Charset.forName("US-ASCII"));
	}
	
	
	// 실제적으로 실행 시작 // Tcp 방식
	public void start() {
		
		//ServerSocket server = null;
		
		// try-with-resource 문법 : 반납해야하는 자원을 ()안에 선언하여 finally를 통한 close작업을 생략함.
		try(ServerSocket server = new ServerSocket(this.port)){ 
			
			//server = new ServerSocket(this.port)
					
			LOGGER.info("접속 요청 대기 중... : 포트번호  => " + server.getLocalPort());
			LOGGER.info("전송 데이터 : ");
			LOGGER.info(new String(this.content, encoding));
			
			while (true) {
				try {
					Socket socket = server.accept();
					HttpHandler handler = new HttpHandler(socket); 
					new Thread(handler).start();
					
				} catch (IOException e) {
					LOGGER.log(Level.WARNING, "접속 대기중 예외발생함.", e);
				} catch (RuntimeException e) {
					LOGGER.log(Level.SEVERE, "알수없는 에러발생", e);
				}
			}
		
		
		}catch (IOException e) {
			LOGGER.log(Level.SEVERE, "서버 구동 실패!!!", e );
		}
	}
	
	private class HttpHandler implements Runnable {
		private final Socket socket; 
		
		// 소켓정보 가져오기
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			try {
				OutputStream out = new BufferedOutputStream(socket.getOutputStream());
				BufferedReader br = new BufferedReader( // 문자열을 위해 버퍼
										new InputStreamReader(
												socket.getInputStream()));
				
				// StringBulider : 스트링 들을 합칠때 사용한다. (+ 보다 속도가 빠르다.)
				StringBuilder request = new StringBuilder();

				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break; // 빈줄이 포함되었으면 중지.
					
					request.append(str + "\n");
				}
				
				System.out.println("요청헤더정보:\n" + request.toString());
				
				
				// HTTP1.0 이나 그 이후의 버전을 지원할 경우 MIME 헤더를 전송한다.
				// HTTP 1.0과 1.1에 맞는 프로토콜의 형태
				if(request.toString().indexOf("HTTP/") != -1) {
					out.write(header); // 브라우저한테 소켓이용해서 write해줌
				}
				
				System.out.println("응답헤더:\n" + new String(header));
				
				out.write(content); // body를 write 보냄
				out.flush();
				
			}catch (IOException e) {
				LOGGER.log(Level.WARNING, "클라이언트에 전송중 에러발생", e);
				
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				} // 소켓 닫기
			}
				
		}
		
	}

	public static void main(String[] args) {
		// 대기(listen)할 포트번호를 설정한다.
		int port;
		try {
			
			port = Integer.parseInt(args[1]);
			
			if(port < 1 || port > 65535) port = 80; // 인트타입의 범위에서 사용
			
		} catch (RuntimeException e) {
			port = 80;
		}
		
		String encoding = "UTF-8";
		
		if(args.length > 0) encoding = args[2];
		
		FileInputStream fis = null;
		
		try {
			File file = new File(args[0]); //filename
			byte[] data = new byte[(int) file.length()];
			fis = new FileInputStream(file);
			fis.read(data);
			
			// MIME 타입을 가져옴
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
			SimpleHttpServer server = new SimpleHttpServer(data, encoding, contentType, port);
			// mimeType => 컨텐츠 타입을 구분을 표현하는 형식 // html은 마임타입 : text/html
  			server.start(); // 서버시작
			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage : java SimpleHTTPServer filename port encoding");
									 //  자바       클래스:프로그램이름     args[0] args[1] args[2] 
		
		} catch (IOException e) {
			LOGGER.severe("IO 에러: " + e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
}

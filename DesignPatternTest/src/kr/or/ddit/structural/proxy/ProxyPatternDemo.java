package kr.or.ddit.structural.proxy;

public class ProxyPatternDemo {
	public static void main(String[] args) {
		
		Image image = new ProxyImage("100메가.jpg");
		
		// 디스크로부터 이미지 로딩하기.
		image.dispaly();
		System.out.println("---------------------------");
		
		// 디스크로부터 이미지 로딩하지 않고 보여주기 
		image.dispaly();
		
		
		
	}
}

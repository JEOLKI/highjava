package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램 방식
		
		
		// 방법1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후 이 인스턴스의 start()메서드를 호출한다.
		// Thread 객체는 run을 먼저 찾아서 실행시킨다.
		MyThread1 th1 = new MyThread1(); // Thread 객체가 된다.
//		th1.start();  // start를 해야 동작을한다.
		
		
		// 방법2 : Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후 이 인스턴스를 
		//        Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		//        이때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1); // Thread(Runnable)
//		th2.start();
		
		
		// 방법3 : 익명클래스를 이용하는 방법
		// Runnable인터페이스를 구현한 익명클래스를 Thread인스턴스를 생성할 때 매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					System.out.print("*");
					
					try {
						//Thread.sleep(시간); => 주어진 시간동안 작업을 잠시 멈춤.
						// 시간은 밀리세컨드 단위를 사용한다.
						// 즉, 1000mx은 1초를 의미한다.
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		// 새로운 콜스텍이 만들어지고 해당객체로 하여금  run부분을 호출하여 실행된다 메인까지 총 4개의 스텍이 생성됨
		// 생성된 각자의 스텍이 각자의 일을 하게되고
		// 메인은 스택을 생성후 마지막 출력문을 찍고 종료가 되게된다.
		th1.start(); // start를 해주면 별도의 스레드를위한 스텍이 만들어진다.
		th2.start();
		th3.start();
		
		// 메인에서만 계속적으로 실행된다.
//		th1.run(); 
//		th2.run();
//		th3.run();
		
		
		System.out.println("main메서드 작업 끝...");
		
		// Thread 총 4개
	
	}
}


class MyThread1 extends Thread {

	@Override
	public void run() { // MyThread1를 실행시 기능할 코딩

		for (int i = 0; i < 200; i++) {
			System.out.print("*");

			try {
				//Thread.sleep(시간); => 주어진 시간동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000mx은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


class MyThread2 implements Runnable { // run메서드를 추상메서드로 가진 인터페이스

	@Override
	public void run() {
		for (int i = 0; i < 200; i++) {
			System.out.print("$");

			try {
				//Thread.sleep(시간); => 주어진 시간동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000mx은 1초를 의미한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
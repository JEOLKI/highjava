package kr.or.ddit.basic;


/*
	wait()메서드 => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별로 존재)으로 이동시킨다.
	notify() 또는 notifyAll()메서드 => Wait-Set영역에 있는 쓰레드를 깨워서
	(notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 깨운다.)
	
	=> wait()와 notify(), notifyAll()메서드는 공기화 영역에서만 실행할 수 있고,
	   Object클래스에서 제공하는 메서드이다.
*/
public class T19_WaitNotifyTest {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
	}
}


// 공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA()메서드 작업 중...");
		
		notify(); // 대기중인 쓰레드를 깨운다.
		
		try {
			wait(); // Waiting 상태
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

// WorkObject의 methodA()만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10 ; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

// WorkObject의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}

















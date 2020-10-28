package kr.or.ddit.basic;

public class T15_SyncThreadTest {
	
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번쓰레드", sObj);
		WorkerThread th2 = new WorkerThread("2번쓰레드", sObj);
		
		th1.start();
		th2.start();
		
	}
}



// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	/*
	// 동기화 하는 방법1: 메서드 자체에 동기화 설정하기
	// 동기화하고자 하는 메서드에 synchronized를 붙여준다. // 단점 : 느려진다, 프로그램성능 저하
	synchronized public void add() { // 메서드 구간 동기화 끝나야 다른것들이 접근가능 1번쓰레드가 다 돌아야 2번쓰레드가 들어간다.
		for (int i = 0; i < 1000000000 ; i++) {	} // 동기화 처리전까지 시간벌기용
		
		int n = sum;
		n += 10; // 10증가
		sum = n;
		
		System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
	}
	*/
	
	// 동기화 하는 방법2 : 동기화 블럭으로 설정하기
	public void add() { 
		synchronized (this) { // 동기화 블럭을 설정해 주기 . 블럭 내에서만 동기화처리 밖에있으면 안된다. 내부만 접근불가
			for (int i = 0; i < 1000000000 ; i++) {	} // 동기화 처리전까지 시간벌기용
			
			int n = sum;
			n += 10; // 10증가
			sum = n;
			
			// 현재 실행하고있는 쓰레드의 정보의 이름
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
			
			
		}
	}
	
	public int getSum() {
		return sum;
	}
}

// 작업을 수행하는 쓰레드
class WorkerThread extends Thread {
	ShareObject sObj;
	
	public WorkerThread(String name, ShareObject sObj) {
		super(name); // 쓰레드 자체의 이름을 저장
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			sObj.add();
		}
	}
}


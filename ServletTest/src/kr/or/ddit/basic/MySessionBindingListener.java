package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MySessionBindingListener implements HttpSessionBindingListener{

	
	/**
	 * Http세션 영역내에 HttpSessionBindingListener를 구현한 객체가 바인딩 되면 호출됨.
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("[MySessionBindingListener]" + this.hashCode() + "객체가 "
							+ event.getName() + "으로 바인딩 됨.");
	}

	/**
	 * Http세션 영역내에 HttpSessionBindingListener를 구현한 객체가 언바인딩 되면 호출됨.
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("[MySessionBindingListener]" + this.hashCode() + "객체가 "
				+ event.getName() + "으로 언바인딩 됨.");
	}

}

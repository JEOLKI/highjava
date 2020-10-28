package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * HTTP세션 영역내에 속성값이 추가되거나 삭제 또는 대체 될때 수행되는 리스너 클래스
 */
public class MySessionAttributeListener implements HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("[MySessionAttributeListener]" + event.getName() + "추가됨.");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("[MySessionAttributeListener]" + event.getName() + "삭제됨.");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("[MySessionAttributeListener]" + event.getName() + "변경됨.");
	}

}

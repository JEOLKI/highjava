package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 리스너 : 특정한 이벤트가 발생하면 해당하는 기능이 자동으로 호출됨
 */
public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener{

	/**
	 * 생성자 
	 */
	public MyServletContextListener() {
		System.out.println("[MyServletContextListener] 생성자 호출됨.");
	}
	
	/**
	 * 서블릿 컨텍스트가 제거 되었을때 호출됨
	 * @param sce
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] 서블릿 컨텍스트가 제거 되었음.");
	}

	/**
	 * 서블릿 컨텍스트가 초기화 되었을때 호출됨
	 * @param sce
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] 서블릿 컨텍스트가 초기화 되었음.");
	}

	
	/**
	 * 서블릿 컨텍스트에 속성이 추가되었을때 호출됨
	 * @param scae
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] "
				+ "서블릿 컨텍스트에 속성이 추가 되었음 => " + scae.getName());
	}

	
	/**
	 * 서블릿 컨텍스트에 속성이 제거되었을때 호출됨
	 * @param scae
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] "
				+ "서블릿 컨텍스트에 속성이 제거 되었음 => " + scae.getName());
	}

	/**
	 * 서블릿 컨텍스트에 속성이 변경되었을때 호출됨
	 * @param scae
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] "
				+ "서블릿 컨텍스트에 속성이 변경 되었음 => " + scae.getName());
	}

}

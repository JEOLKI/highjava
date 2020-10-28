package kr.or.ddit.member.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import kr.or.ddit.member.handler.CommandHandler;
import kr.or.ddit.member.handler.NullCommandHandler;
import kr.or.ddit.util.FileUploadRequestWrapper;

/**
 * 사용자 요청을 받아서 대응되는 핸들러를 호출하도록 함.
 */
public class FrontController extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(FrontController.class);
	
	// 핸들러 객체 저장용 맵
	private Map<String, CommandHandler> cmdHanderMap = new HashMap<>(); 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		// 설정파일을 읽어서 대응되는 핸들러 개체를 생성하여 맵에 등록하기
		String configFileRealPath = 
				config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			
			handlerProp.load(fr);
			
		}catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
		for(Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				CommandHandler handlerInstance =
						(CommandHandler) klass.newInstance();
				cmdHanderMap.put(command, handlerInstance);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		logger.info("cmdHanderMap : " + cmdHanderMap);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			FileUploadRequestWrapper requestWrapper = new FileUploadRequestWrapper(req);
			process(requestWrapper, resp);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String command = req.getRequestURI();
		if(command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
		}
		
		logger.info("command : " + command);
		logger.info("cmdHanderMap : " + cmdHanderMap);
		
		CommandHandler handler = cmdHanderMap.get(command);
		
		if(handler == null) {
			handler = new NullCommandHandler(); // 404 에러 출력
		}
		
		String viewPage = "";
		
		try {
			viewPage = handler.process(req, resp); // 커맨드 객체 처리
		}catch(Throwable ex) {
			//throw new ServletException();
			ex.getStackTrace();
		}
		
		logger.info("viewPage : " + viewPage);
		
		if(viewPage != null) {
			if(viewPage.startsWith("redirect:")) {
				resp.sendRedirect(viewPage.substring(9));
			}else {
				
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
	}
}

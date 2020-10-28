<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	int result = (Integer) request.getAttribute("result");
	
	if(result > 0){
%>    
		{ "sw" : "삭제에 성공하였습니다." }	
		
<%
	} else {
%>    
		{ "sw" : "삭제에 실패하였습니다." }	
<%
	}
%>    


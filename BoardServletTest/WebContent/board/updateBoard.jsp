<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	int count = (Integer) request.getAttribute("count");
	
	if(count >0){
%>		
	{ "sw" : "성공" }		
		
<%
	} else {
%>

	{ "sw" : "실패" }		

<%
	}
%>

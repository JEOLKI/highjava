<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");

%>

[
<%
	for(int i = 0 ; i<boardList.size(); i++){
		BoardVO board = boardList.get(i);
		if(i>0) out.print(", ");
%>
		{
			"no" : "<%= board.getBoard_no() %>",
			"title" : "<%= board.getBoard_title() %>",
			"writer" : "<%= board.getBoard_writer() %>",
			"date" : "<%= board.getBoard_date() %>",
			"content" : "<%= board.getBoard_content() %>"
		}
<%
	}

%>

]
<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");
	String msg = (String) request.getAttribute("msg");

%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원목록</title>
</head>
<body>
<table border="1">
 <tr>
 	<td colspan="4"><a href="<%=request.getContextPath() %>/member/insert.do">[회원등록]</a></td>
 </tr>
 <tr>
 	<td>ID</td>
 	<td>이름</td>
 	<td>전화번호</td>
 	<td>주소</td>
 </tr>
<%
	int memSize = memList.size();
	if(memSize > 0){
		for(int i=0; i<memSize; i++){
%>			
	<tr>
		<td><%= memList.get(i).getMem_id() %></td>
		<td><a href="<%=request.getContextPath() %>/member/select.do?memId=<%=memList.get(i).getMem_id() %>"> 
		<%= memList.get(i).getMem_name() %></a></td>
		<td><%= memList.get(i).getMem_tel() %></td>
		<td><%= memList.get(i).getMem_addr() %></td>
	</tr>	
<% 			
		}
	}else{ // 회원정보가 존재하지 않을 경우...
  %>
	<tr>
		<td colspan="4">회원정보가 존재하지 않습니다.</td>
	</tr>
<%	
	}
%> 	
</table>
 
 <%
 	if(msg.equals("성공")){
 %>	
 	<script>
 		alert('정상적으로 처리되었습니다.');
 	</script>
 <% 	
 	}	
 %>
</body>
</html>
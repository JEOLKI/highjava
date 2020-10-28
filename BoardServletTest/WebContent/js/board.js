/**
 * 
 */


var searchBoard = function(searchElement) {
	
	$.ajax({
	
		url : '/BoardServletTest/SearchBoard',
		data : searchElement,
		type : 'get',
		dataType : 'json',
		success : function(res) {
			
			$('#tab').empty();
			
			code =  "<table border='1'>";
			code += "<tr>";
			code += "<td>번호</td>";
			code += "<td>제목</td>";
			code += "<td>내용</td>";
			code += "<td>작성자</td>";
			code += "<td>작성일</td>";
			code += "<td colspan='2'>button</td>";
			code += "</tr>";
			
			$.each(res, function(i,v) {
				
				code += "<tr class='trtab'>";
				code += "<td>" + v.no + "</td>\n";
				code += "<td class='title' id='"+ v.no +"'>" + v.title + "</td>\n";
				code += "<td class='content1'>" + v.content + "</td>\n";
				code += "<td>" + v.writer + "</td>\n";
				code += "<td>" + v.date + "</td>\n";
				code += "<td><button idx="+ v.no +" id='updateBoard' type='button'> 글수정 </button></td>\n"
				code += "<td><button idx="+ v.no +" id='deleteBoard' type='button'> 글삭제 </button></td>\n"
				code += "</tr>\n";
				
			})
			
			code += "</table>"
			
			$('#tab').append(code);
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status)
		}
	
	})
	
}


var updateBoard = function(update) {
	
	$.ajax({
		url : '/BoardServletTest/UpdateBoard',
		data : update,
		type : 'post',
		dataType : 'json',
		success : function(res) {
			alert(res.sw);
			
			location.href="boardMain.html";
			
			
		},
		error : function(xhr) {
			alert("상태 : " + xhr.status);
		}
		
	})
	
	
	
}
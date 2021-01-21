<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:forEach items="${requestScope.pList }" var="personVo">
			<table border="1">
				<tr>
					<td>이름(name)</td>
					<td>${personVo.name }</td>
				</tr>
				<tr>
					<td>핸드폰(hp)</td>
					<td>${personVo.hp }</td>
				</tr>
				<tr>
					<td>회사(company)</td>
					<td>${personVo.company }</td>
				</tr>
				<tr>
				
					<td><a href="/${pageContext.request.contextPath }/phone/modifyForm?personId=${personVo.personId }">수정 </a></td>
					<td><a href="/phonebook4/phone/delete/${personVo.personId }">삭제</a></td>
										
				</tr>
			</table>
			<br>
		</c:forEach>
		
		
	<a href="/phonebook3/phone/writeForm">추가 번호 등록</a>
</body>
</html>
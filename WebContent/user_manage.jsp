<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,entity.User"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户管理</title>
</head>
<body>
	<table>
		<tr>
			<td>用户号</td>
			<td>用户名称</td>
		</tr>
		<tr>
			<td>${userlist[0].id }</td>
			<td>${userlist[0].name }</td>
		</tr>
		<tr>
			<td>${userlist[1].id }</td>
			<td>${userlist[1].name }</td>
		</tr>
		<tr>
			<td>
				<a href="manageUserAct?page=1">首页</a>
				<c:choose>
					<c:when test="${page==1 }">上一页</c:when>
					<c:otherwise><a href="manageUserAct?page=${page-1 }">上一页</a></c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${page==pagecount }">下一页</c:when>
					<c:otherwise><a href="manageUserAct?page=${page+1 }">下一页</a></c:otherwise>
				</c:choose>
				<a href="manageUserAct?page=${pagecount }">尾页</a>
			</td>
		</tr>
	</table>
	<a href="index.html">主页</a>
</body>
</html>
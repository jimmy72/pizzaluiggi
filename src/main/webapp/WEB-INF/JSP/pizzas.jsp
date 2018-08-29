<%@page contentType="text/html" pageEncoding="UTF-8" session="false"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Pizza's"></c:param>
</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"></c:import>
	<h1>Pizza's
	<c:forEach begin="1" end="5">
		&#9733;
	</c:forEach>
	</h1>
	<ul class='zebra'>
		<c:forEach var='entry' items='${pizzas}'>
			<li>${entry.key}:&nbsp;<c:out value="${entry.value.naam}"></c:out>&nbsp;${entry.value.prijs}&euro;
				<c:choose>
					<c:when test='${entry.value.pikant}'>pikant</c:when>
					<c:otherwise>niet pikant</c:otherwise>
				</c:choose>
			<c:url value="/pizzas" var="url">
				<c:param name="id" value="${entry.key}"></c:param>
			</c:url>
			&nbsp;<a href="${url}">detail</a>
			</li>
		</c:forEach>
	</ul>
</body>
</html>
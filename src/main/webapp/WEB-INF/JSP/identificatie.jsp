<%@page language="java" contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name='title' value="Identificatie"/>
</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"></c:import>
	<h1>Identificatie</h1>
	<c:url value='/identificatie' var='url'/>
	<form:form action='${url}' modelAttribute='identificatie' method='post' id='identificatieForm'>
		<form:label path="emailAdres">Emailadres:
		<form:errors path="emailAdres"></form:errors></form:label>
		<form:input path="emailAdres" autofocus="autofocus" required="required"/>
		<input type="submit" value="OK" id="okKnop">
	</form:form>
<script>
	document.getElementById('identificatieForm').onsubmit = function() {
		document.getElementById('okKnop').disabled = true;
	};
</script>
</body>
</html>
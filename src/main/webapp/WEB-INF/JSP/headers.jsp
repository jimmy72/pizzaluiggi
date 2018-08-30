<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="nl">
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name='title' value="Headers"/>
</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"></c:import>
	<h1>Headers</h1>
	<p>Je browser wordt uitgevoerd op:</p>
	<p>${opWindows ? "Windows" : "een niet-Windows besturingssysteem"} </p>
</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<c:import url="/WEB-INF/JSP/head.jsp">
	<c:param name="title" value="Pizza Luiggi"></c:param>
</c:import>
</head>
<body>
	<c:import url="/WEB-INF/JSP/menu.jsp"></c:import>
	<h1>Pizza Luigi</h1>
	<img src='images/pizza.jpg' alt='pizza' class='fullwidth'>
	<h2>${message}</h2>
	<h2>De zaakvoerder</h2>
	<dl>
		<dt>Naam</dt>
		<dd>${zaakvoerder.naam}</dd>
		<dt>Aantal kinderen</dt>
		<dd>${zaakvoerder.aantalKinderen}</dd>
		<dt>Gehuwd</dt>
		<dd>${zaakvoerder.gehuwd ? 'Ja' : 'Nee'}</dd>
		<dt>Adres</dt>
		<dd>${zaakvoerder.adres.straat}&nbsp;${zaakvoerder.adres.huisNr}<br>
		${zaakvoerder.adres.postcode}&nbsp;${zaakvoerder.adres.gemeente}</dd>
	</dl>
	<c:if test="${not empty laatstBezocht}">
		<p>Je bezocht deze website laatste op ${laatstBezocht}</p>
	</c:if>
	<p>Deze pagina werd ${numberOfViews} bekeken.</p>
	<p>${identificatie.emailAdres}</p>
</body>
</html>
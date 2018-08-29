<%@page contentType="text/html" pageEncoding="UTF-8" session="false"
	trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<title>Pizza's</title>
<link rel='icon' href='images/pizza.ico' type='image/x-icon'>
<meta name='viewport' content='width=device-width,initial-scale=1'>
<link rel='stylesheet' href='css/pizzaluiggi.css'>
</head>
<body>
	<h1>Pizza's</h1>
	<ul class='zebra'>
		<c:forEach var='entry' items='${pizzas}'>
			<li>${entry.key}:&nbsp;${entry.value.naam}&nbsp; ${entry.value.prijs}&euro;</li>
		</c:forEach>
	</ul>
</body>
</html>
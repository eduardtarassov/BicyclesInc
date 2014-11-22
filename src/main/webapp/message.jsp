
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>BicyclesInc - Message</title>
<link rel="stylesheet" type="text/css" href="/BicyclesInc/Styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header>
		<h2>BicyclesInc - Message</h2>
	</header>
	<%
		String message = (String) request.getAttribute("message");
	%>
	<h1><%=message%></h1>
	<h3>You will be redirected automatically just right now.</h3>
	<meta http-equiv="Refresh" content="5;index.jsp">
</body>
</html>

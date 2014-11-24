<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="containers.Material"%>

<!DOCTYPE html>
<html>
	<head>
	    <title>BicyclesInc - Material</title>
<link rel="stylesheet" type="text/css" href="shop-styles.css" />	    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
<body>
	<header>
	    <h2>BicyclesInc - Material</h2>
	</header>
	
	
	
	
	<div class="search">
    <form method="POST" action="search">
        <table border="0" width="300">
            <tr><td colspan=2 style="font-size:12pt;" align="center">
                <h3>Search User</h3></td></tr>
            <tr><td ><b>User Name</b></td>
                <td>: <input  type="text" name="username" id="username">
                </td></tr>
            <tr><td colspan=2 align="center">
                <input  type="submit" name="submit" value="Search"></td></tr>
        </table>
    </form>
</div>
	
	
	
	
<nav>
	<ul class="nav">	
		<li><a href="/BicyclesInc/index.jsp">Home</a></li>
		<%                        
            Material material = (Material) request.getAttribute("MaterialInfo");
%>
	
	</ul>
</nav>
<article>
<p>
Material ID: <%=material.getMaterial_id()%></br>
Material: <%=material.getMaterial()%></br>
Description: <%=material.getDescription()%></br>
Unit Price: <%=material.getUnit_price()%></br>
Units: <%=material.getUnits()%></br>
Amount: <%=material.getAmount()%></br>


</article>
<footer>

</footer>
</body>
</html>

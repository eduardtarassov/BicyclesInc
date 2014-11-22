<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="containers.StockItem"%>

<!DOCTYPE html>
<html>
	<head>
	    <title>BicyclesInc - Stock Item</title>
	    <link rel="stylesheet" type="text/css" href="/BicyclesInc/Styles.css"/>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
<body>
	<header>
	    <h2>BicyclesInc - Stock Item</h2>
	</header>
	
	
	
<nav>
	<ul class="nav">	
		<li><a href="/BicyclesInc/index.jsp">Home</a></li>
		<%                        
            StockItem stockItem = (StockItem) request.getAttribute("StockItemInfo");
%>
	
	</ul>
</nav>
<article>
<p>
StockItem ID: <%=stockItem.getProduct_id()%></br>
StockItem: <%=stockItem.getDisplayed_name()%></br>
Description: <%=stockItem.getDescription()%></br>
Price: <%=stockItem.getPrice()%></br>
Available number: <%=stockItem.getAvailable_number()%></br>
Number Sold: <%=stockItem.getNumber_sold()%></br>
Discount: <%=stockItem.getDiscount()%></br>
<a href="/BicyclesInc/update_stock_item.jsp">Update your material information</a>

</article>
<footer>

</footer>
</body>
</html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.*"%>
<%@ page import="containers.*"%>

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
<%LoginState lg = (LoginState) session.getAttribute("LoggedIn");%>	
	
	
<nav>
	<ul class="nav">	
		<li><a href="index.jsp">Home</a></li>
		<%                        
             if ((lg == null) || (lg.getLoginState() == false)) {
         %>
         <li><a href="register.jsp">Register</a></li>
	     <li><a href="login.jsp">Login</a></li>
         <%}else{ 
         if ((lg.getAccess() == 1) || (lg.getAccess() == 0)){ %>
         <li><a href="register_by_admin.jsp">Register Staff</a></li>
         <li><a href="add_material.jsp">Add Material</a></li>  
         <li><a href="/BicyclesInc/AddProduct">Add Product</a>  
         <li><a href="/BicyclesInc/show_materials">Check Materials</a></li>
         <li><a href="region.jsp">Demographics</a></li>
	     <%}
         else{%>
         <li><a href="/BicyclesInc/profile/<%=lg.getUsername()%>">Profile</a></li>    
	     <%}%>
	     <li><a href="/BicyclesInc/show_stock_items">Online Shop</a></li>
	     <li><a href="/BicyclesInc/logout">Logout</a></li>
        <% } %>
	
	</ul>
</nav>
<article>
<%                        
	StockItem stockItem = (StockItem) request.getAttribute("StockItemInfo");
%>
<p>
StockItem ID: <%=stockItem.getProduct_id()%></br>
StockItem: <%=stockItem.getDisplayed_name()%></br>
Description: <%=stockItem.getDescription()%></br>
Price: <%=stockItem.getPrice()%></br>
Available number: <%=stockItem.getAvailable_number()%></br>
Number Sold: <%=stockItem.getNumber_sold()%></br>
Discount: <%=stockItem.getDiscount()%></br>

</article>
<footer>

</footer>
</body>
</html>
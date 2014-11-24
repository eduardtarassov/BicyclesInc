<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="containers.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Styles.css" />
        <title>BicyclesInc - Add Material</title>
    </head>
    <body>
      <header>
		<h2>BicyclesInc - Add Material</h2>
	</header>
	<%LoginState lg = (LoginState) session.getAttribute("LoggedIn");
	%>
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
		<form action="AddMaterial" method="POST">
			<br>Material
			<input id="material" name="material" type="text">
			<br>Amount
			<input id="amount" name="amount" type="text">
			<select id="units" name="units">
				<option value="kg">kg</option>
				<option value="tons">tons</option>
			</select>
			<br>Price Per Unit
			<input id="unitPrice" name="unit_price" type="text">
			Calculated Value: 
			<p id="valueParagraph" style="display:inline"></p>
			<br>Description
			<textarea id="description" name="description"></textarea><br>
			<input type="submit" value="Submit">
		</form>
		<script src="scripts/add_material.js"></script>
		</article>
    </body>
</html>
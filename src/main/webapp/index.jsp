<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="containers.*" %>

<!DOCTYPE html>
<html>
	<head>
	    <title>BicyclesInc - Home</title>
	    <link rel="stylesheet" type="text/css" href="Styles.css"/>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
<body>
	<header>
	    <h2>BicyclesInc - Home</h2>
	</header>
	
	
	
	<%LoginState lg = (LoginState) session.getAttribute("LoggedIn");
	if (lg != null){
	if (lg.getAccess() == 0){%>
	<div class="search">
    <form method="POST" action="search">
        <table border="0" width="300">           
            <tr><td colspan=2 align="right">Search User:&nbsp;<input  type="text" name="username" id="username">
                </td></tr>
            <tr><td colspan=2 align="right">
                <input  type="submit" name="submit" value="Search"></td></tr>
        </table>
    </form>
</div>
<%}} %>
	
	
	
	
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
<h2>Welcome to Bicycles Inc</h2>
<p>Here at BicyclesInc, we are proud of our company values and strive to give you the best experience while shopping to stock up your shops. Hopefully the experience inspires you to make BicyclesInc your number one supplier!
</p>
</article>
<footer>

</footer>
</body>
</html>

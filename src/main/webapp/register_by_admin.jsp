<%--
  Created by User: Luke
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="containers.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>BicyclesInc - Register Staff</title>
    <link rel="stylesheet" type="text/css" href="Styles.css" />
</head>
<body>
<header>
    <h2>BicyclesInc - Register Staff</h2>
</header>
<%LoginState lg = (LoginState) session.getAttribute("LoggedIn");%>
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

<div class="register">
	<article>
            <h3>Register a new Staff Member</h3>
            <div class="register">
            <form method="POST" action="register_staff">
                <ul>
                <fieldset>
                    <li>User Name <input type="text" name="id"></li></br>
                    <li>Password <input type="password" name="password"></li></br>
                    <!--<li>Repeat Password <input type="password" name="password_repeat"></li>-->
                    <li>First Name <input type="text" name="first_name"></li></br>
                    <li>Last Name <input type="text" name="last_name"></li></br>
                    <li>Secret Question<input type="text" name="secret_question"></li></br>
                    <li>Secret Answer<input type="text" name="secret_answer"></li></br>
              		<li>Email address<input type="text" name="email"></li></br>
              		<li>DOB (DD/MM/YYYY)<input type="text" name="date_of_birth"></li></br>
              		<li>Your company<input type="text" name="company_affiliation"></li></br>
              		<li>Department <input type="text" name="department"></li></br>
              		</fieldset>
                	<input type="submit" value="Register">
                </ul>       
            </form>  
            </div>     
	</article>
</div>
<footer>
    <ul>
        
    </ul>
</footer>
</body>
</html>
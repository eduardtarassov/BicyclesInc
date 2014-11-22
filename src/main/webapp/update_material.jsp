<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="containers.*" %>
<!DOCTYPE html>
<html>
	<head>
	    <title>BicyclesInc - Profile</title>
	    <link rel="stylesheet" type="text/css" href="/BicyclesInc/Styles.css"/>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
<body>
	<header>
	    <h2>BicyclesInc - Profile</h2>
	</header>
<nav>
	<ul class="nav">	
		<li><a href="/BicyclesInc/index.jsp">Home</a></li>
		<%                        
             LoginState lg = (LoginState) session.getAttribute("LoggedIn");
             if (lg == null) {
         %>
         <li><a href="/BicyclesInc/register.jsp">Register</a></li>
	     <li><a href="/BicyclesInc/login.jsp">Login</a></li>
         <%}else{ 
         if (lg.getAccess() == 0){ %>
         <li><a href="/BicyclesInc/register_by_admin.jsp">Register new staff member</a></li>
	     <li><a href="/BicyclesInc/logout">Logout</a></li>
	     <%}
         else{%>
         <li><a href="/BicyclesInc/profile/<%=lg.getUsername()%>">Profile</a></li>
	     <li><a href="/BicyclesInc/logout">Logout</a></li>
	     <%}} %>
	
	</ul>
</nav>
<article>
    <h3>You can update all your information except your username.</h3>
    <form method="POST" action="/BicyclesInc/update">
        <ul>
        	<input type="text" name="id" value="<%=lg.getUsername()%>" style="visibility:hidden">
            <li>Password: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password"></li>
            <!--<li>Repeat Password <input type="password" name="password_repeat"></li>--><br/>
            <li>First Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="first_name"></li><br/>
            <li>Last Name: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="last_name"></li><br/>
            <li>Email Address: <input type="email" name="email"></li><br/>
            <li>Secret Question:<input type="text" name="secret_question"></li><br/>
            <li>Secret Answer:&nbsp;&nbsp;<input type="text" name="secret_answer"></li><br/>
       		<li>DOB (DD/MM/YYYY):<input type="date" name="date_of_birth"></li><br/>
       		<li>Your company: &nbsp;<input type="text" name="company_affiliation"></li>	
       		<input type="submit" value="Update">
       		
        </ul>        
    </form>
	<form method="POST" action="/BicyclesInc/delete-users">
       			<input type="text" name="column" value="id" style="visibility:hidden">
       			<input type="text" name="value" value="<%=lg.getUsername()%>" style="visibility:hidden">
       			<input type="submit" value="Delete Profile">
   	</form>
</article>

</body>
</html>
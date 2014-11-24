<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="containers.*"%>

<!DOCTYPE html>
<html>
<head>
<title>BicyclesInc - Profile</title>
<link rel="stylesheet" type="text/css" href="/BicyclesInc/Styles.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<header>
		<h2>Your Profile</h2>
	</header>




	<div class="search">
		<form method="POST" action="search">
			<table border="0" width="300">
				<tr>
					<td colspan=2 align="right">Search User:&nbsp;<input
						type="text" name="username" id="username">
					</td>
				</tr>
				<tr>
					<td colspan=2 align="right"><input type="submit" name="submit"
						value="Search"></td>
				</tr>
			</table>
		</form>
	</div>




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
			<li><a href="/BicyclesInc/register_by_admin.jsp">Register
					new staff member</a></li>
			<li><a href="/BicyclesInc/logout">Logout</a></li>
			<%}
         else{%>
			<li><a href="/BicyclesInc/logout">Logout</a></li>
			<%}} %>

		</ul>
	</nav>
	<article class="profile">
		<%ProfileInfo pInf = (ProfileInfo) request.getAttribute("ProfileInfo"); 
%>
		<p>
		<ul>
			<li><b>User: </b><%=pInf.getUsername()%></li>
			</br>
			<li><b>First Name: </b><%=pInf.getFirstname()%></li>
			</br>
			<li><b>Last Name: </b><%=pInf.getLastname()%></li>
			</br>
			<li><b>Email: </b><%=pInf.getEmail()%></li>
			</br>
			<li><b>Date of Birth: </b> <%=pInf.getDOB()%></li>
			</br>
			<li><b>Company: </b><%=pInf.getCompany()%></li>
			</br>
			</h3>
			<li><a href="/BicyclesInc/update_profile.jsp">Update profile</a>
			
		</ul>
		</p>
	</article>
	<footer> </footer>
</body>
</html>

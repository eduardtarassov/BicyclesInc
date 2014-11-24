<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="containers.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Styles.css" />
<title>Delete Stock Item by finding it specifying column and
	value to search!</title>
</head>
<body>
<header>
		<h2>BicyclesInc - Delete Item</h2>
	</header>

<nav>
			<ul class="nav">
			<li><a href='index.jsp'>Home</a></li>
				<%
					LoginState lg = (LoginState) session.getAttribute("LoggedIn");
					if (lg == null) {
						
					} else {
						if (lg.getAccess() == 0) {
				%>
				<li><a href="add_stock_item.jsp">Add item</a></li>
				<li><a href="delete_stock_item.jsp">Delete item</a></li>
				<%} else {%>
				<li><a href="/BicyclesInc/profile/<%=lg.getUsername()%>">Profile</a></li>
				<%}%>
				<li class='active '><a href='/BicyclesInc/show_stock_items'><span>Shop Home</span></a></li>				
				<%}%>
				</ul>
			</nav>

<article>
	<h3>Delete Stock Item by finding it specifying column and value to
		search!</h3>
	<form action="delete-stock_items" method="POST">
		<ul>
			<li><select name="column">
				<option value="product_id">ID</option>
				<option value="displayed_name">Name</option>
				<option value="price">Price</option>
			</select></li>
			<li>Search value<input type="text" name="value"></li>
			<br>
			<input type="submit" value="Delete">
		</ul>
	</form>
	</article>
</body>
</html>
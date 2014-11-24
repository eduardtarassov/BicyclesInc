<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="containers.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BicyclesInc - Add Stock Item</title>
<link rel="stylesheet" type="text/css" href="Styles.css" />
</head>
<body>
	<header>
		<h2>BicyclesInc - Add Stock Item</h2>
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
			
	<div class="register">
		<article>
			<h3>Add new stock item</h3>
			<form method="POST" action="add_stock_item">
				<ul>
					<fieldset>
						<legend>
							<b>Main Information</b>
						</legend>
						<li>Item name <input type="text" name="displayed_name"></li></br>
						<li>Description <input type="text" name="description"></li></br>
					</fieldset>
					<fieldset>
						<legend>
							<b>Counts</b>
						</legend>
						<li>Price <input type="text" name="price"></li></br>
						<li>Available Number <input type="text"	name="available_number"></li></br>
						<li>Number Sold <input type="text" name="number_sold"></li></br>
						<li>Discount <input type="text" name="discount"></li></br>
						<li>Image ID<input type="text" name="image_id"></li></br>
					</fieldset>
					<input type="submit" value="Add item">
				</ul>


			</form>

		</article>
	</div>
	<footer>	</footer>
</body>
</html>
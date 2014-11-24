<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="containers.StockItem"%>
<%@page import="containers.LoginState" %>
<!DOCTYPE html>
<%@ page language="java" import="java.sql.*" errorPage=""%>

<html>
<head>
<title>BicyclesInc - Online Shopping</title>
<link rel="stylesheet" type="text/css" href="shop-styles.css" />
</head>
<body>
<header>
		<h2>BicyclesInc - Online Shop</h2>
	</header>
	<div align="center">
		<div class='cssmenu'>

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
				<%
					} else {
				%>
				<li><a href="/BicyclesInc/profile/<%=lg.getUsername()%>">Profile</a></li>
				<%
					}
				%>
				<li class='active '><a href='/BicyclesInc/show_stock_items'><span>Shop Home</span></a></li>
				
				<%
					}
				%>

				</ul>
			</nav>
		</div>
	</div>
	<div align="center">
		<h2>View Products from Database</h2>
		<table width="700px" align="center" style="border: 1px solid #000000;">
			<tr>
				<td colspan=4 align="center" style="background-color: teal"><b>User
						Record</b></td>
			</tr>
			<tr style="background-color: lightgrey;">
				<td><b>Name</b></td>
				<td><b>Price</b></td>
				<td><b>InStock</b></td>
				<td><b>Sold amount</b></td>
				<td><b>Discount</b></td>
				<td><b>More Information</b></td>
				<td><b></b></td>
			</tr>
			<%
				int count = 0;
				String color = "#F9EBB3";
				if (request.getAttribute("instock_result") != null) {
					LinkedList<StockItem> items = (LinkedList<StockItem>) request
							.getAttribute("instock_result");
					Iterator itr = items.iterator();
					while (itr.hasNext()) {

						if ((count % 2) == 0) {
							color = "#eeffee";
						}
						count++;
						StockItem item = (StockItem) itr.next();
						request.setAttribute("found_stock_item", item);
			%>
			<tr style="background-color:<%=color%>;">
				<td><%=item.getDisplayed_name()%></td>
				<td><%=item.getPrice()%></td>
				<td><%=item.getAvailable_number()%></td>
				<td><%=item.getNumber_sold()%></td>
				<td><%=item.getDiscount()%></td>
				<td><%=item.getProduct_id()%></td>
				<td><a href="StockItem/<%=item.getProduct_id()%>">See More
				</a></td>

			</tr>
			<%
				}
				}
				if (count == 0) {
			%>
			<tr>
				<td colspan=4 align="center" style="background-color: #eeffee"><b>No
						Record Found..</b></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>
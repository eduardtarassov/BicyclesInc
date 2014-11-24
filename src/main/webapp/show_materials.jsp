<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="containers.Material"%>
<%@page import="containers.LoginState"%>
<!DOCTYPE html>
<%@ page language="java" import="java.sql.*" errorPage=""%>

<html>
<head>
<title>BicyclesInc - All Materials in the Store room</title>
<link rel="stylesheet" type="text/css" href="shop-styles.css" />
</head>
<body>
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
				<li class='active '><a href='search_products'><span>Shop Home</span></a></li>
				
				<%
					}
				%>

				</ul>
			</nav>


<article>
	<div align="center">
		<h1>BicyclesInc - All Materials in the Store room</h1>
		<table width="700px" align="center" style="border: 1px solid #000000;">
			<tr>
				<td colspan=4 align="center" style="background-color: teal"><b>User
						Record</b></td>
			</tr>
			<tr style="background-color: lightgrey;">
				<td><b>Material ID</b></td>
				<td><b>Material</b></td>
				<td><b>Unit price</b></td>
				<td><b>Units</b></td>
				<td><b>Amount</b></td>
			</tr>
			<%
				int count = 0;
				String color = "#F9EBB3";
				if (request.getAttribute("inmaterials_result") != null) {
					LinkedList<Material> materials = (LinkedList<Material>) request
							.getAttribute("inmaterials_result");
					Iterator itr = materials.iterator();
					while (itr.hasNext()) {

						if ((count % 2) == 0) {
							color = "#eeffee";
						}
						count++;
						Material material = (Material) itr.next();
			%>
			<tr style="background-color:<%=color%>;">
				<td><%=material.getMaterial_id()%></td>
				<td><%=material.getMaterial()%></td>
				<td><%=material.getUnit_price()%></td>
				<td><%=material.getUnits()%></td>
				<td><%=material.getAmount()%></td>
				<td><a href="Material/<%=material.getMaterial_id()%>">See More
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
</article>
	<footer>
	</footer>
</body>
</html>
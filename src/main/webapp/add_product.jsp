<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="containers.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/BicyclesInc/Styles.css" />
        <title>Add Product</title>
    </head>
    <body>
        <h1>Add Product</h1>
		<form action="AddProduct" method="post">
			<input id="bicycle" name="productType" type="radio" value="bike" checked>Bicycle
			<input id="part" name="productType" type="radio" value="part">Part
			<br>Name
			<input id="productName" name="productName" type="text">
			<br>Height
			<input id="height" name="height" type="number">
			<br>Width
			<input id="width" name="width" type="number">
			<br>Colour
			<input id="colour" name="colour" type="text">
			<br>Cost to Produce
			<input id="productionCost" name="productionCost" type="number">
			<br>Design Properties
			<textarea id="design" name="design"></textarea>
			<div id="bicycleProperties">
				Type
				<select id="bicycleType" name="bicycleType">
					<option value="road">Road</option>
					<option value="mountain">Mountain</option>
				</select>
				<br>Release Date: Day
				<input id="releaseDay" name="releaseDay" type="number" min="1" max="31">
				Month
				<input id="releaseMonth" name="releaseMonth" type="number" min="1" max="12">
				Year
				<input id="releaseYear"	name="releaseYear" type="number" min="2014" max="2020">
				<br>Packaging Type
				<input id="packagingType" name="packagingType" type="text">
				<br>Parts
				<select id="part0" class="part" name="part0">
					<%
						ArrayList<Product> partList = (ArrayList<Product>) request.getAttribute("PartList");
						for(int i = 0; i < partList.size(); i++)
						{
							Product partNameAndID = partList.get(i);
					%>
					<option value=<%=partNameAndID.getID()%>><%=partNameAndID.getName()%></option>
					<%
						}
					%>
				</select>
				Quantity
				<input id="quantity0" class="quantity" name="quantity0" type="text">
				<br>
				<input id="addAnotherPart" type="button" name="addAnotherPart" value="Add Another Part">
				<input id="removeLastPart" type="button" name="removeLastPart" value="Remove Last Part">
			</div>
			<div id="partProperties" style="display:none">
				Type
				<select id="partType" name="partType">
					<option value="wheel">Wheel</option>
					<option value="seat">Seat</option>
					<option value="chain">Chain</option>
				</select>				
				<br>Material(s)
				<select id="material0" class="material" name="material0">
					<%
						LinkedList<String> materialList = (LinkedList<String>) request.getAttribute("MaterialList");
						while(!materialList.isEmpty())
						{
							String material = materialList.pop();
					%>
					<option value=<%=material%>><%=material%></option>
					<%
						}
					%>
				</select>
				<br>
				<input id="addAnotherMaterial" type="button" name="addAnotherMaterial" value="Add Another Material">
				<input id="removeLastMaterial" type="button" name="removeLastMaterial" value="Remove Last Material">
			</div>
			<input type="submit" value="Submit">
		</form>
		<script src="addProduct.js"></script>
    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    </body>
</html>
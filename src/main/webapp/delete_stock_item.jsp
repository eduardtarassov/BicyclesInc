<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Styles.css" />
<title>Delete Stock Item by finding it specifying column and
	value to search!</title>
</head>
<body>
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
</body>
</html>
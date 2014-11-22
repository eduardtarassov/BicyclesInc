<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
	<div class="register">
		<article>
			<h3>Add new stock item</h3>
			<form method="POST" action="add_stock_item">
				<ul>
					<fieldset>
						<legend>
							<b>Main Information</b>
						</legend>
						<li>Item name <input type="text" name="displayed_name"></li>
						<li>Description <input type="text" name="description"></li>
					</fieldset>
					<fieldset>
						<legend>
							<b>Counts</b>
						</legend>
						<li>Price <input type="text" name="price"></li>
						<li>Available Number <input type="text"
							name="available_number"></li>
						<li>Number Sold <input type="text" name="number_sold"></li>
						<li>Discount <input type="text" name="discount"></li>
						<li>Image ID<input type="text" name="image_id"></li>
					</fieldset>
					<input type="submit" value="Add item">
				</ul>


			</form>

		</article>
	</div>
	<footer>
		<ul>
			<li><a href='shop_index.jsp'><span>Home</span></a></li>
			<li><a href='index.jsp'><span>BicyclesInc - Home</span></a></li>
		</ul>
	</footer>
</body>
</html>
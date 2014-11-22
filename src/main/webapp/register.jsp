<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BicyclesInc - Registration</title>
<link rel="stylesheet" type="text/css" href="Styles.css" />
</head>
<body>
	<header>
		<h2>BicyclesInc - Register</h2>
	</header>
	<nav>
		<ul class="nav">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="login.jsp">Login</a></li>

		</ul>
	</nav>	
		<article>
			<h3>Register as customer</h3>
			<div class="register">
			<form method="POST" action="register">
				<ul>
					<fieldset>
						<legend><b>User Credentials</b></legend>
						<li>User Name:&nbsp;<input type="text" name="id"></li></br>
						<li>Password:&nbsp;<input type="password" name="password"></li></br>
					</fieldset>
					<!--<li>Repeat Password <input type="password" name="password_repeat"></li>-->

					<fieldset>
						<legend><b>Personal Information</b></legend>
						<li>First Name:&nbsp;<input type="text" name="first_name"></li></br>
						<li>Last Name:&nbsp;<input type="text" name="last_name"></li></br>
						<li>Email address:&nbsp;<input type="email" name="email"></li></br>
                    	<li>DOB (dd/mm/yyyy):&nbsp;<input type="date" name="date_of_birth"></li></br>
						</fieldset>
						<fieldset>
						<legend><b>Other Information</b></legend>
						<li>Secret Question:&nbsp;<input type="text" name="secret_question"></li></br>
						<li>Secret Answer:&nbsp;<input type="text" name="secret_answer"></li></br>				
						<li>Your company:&nbsp;<input type="text" name="company_affiliation"></li></br>
						</fieldset>
						<input type="submit" value="Register">
				</ul>


			</form>
			</div>
		</article>
	
	<footer>
		
	</footer>
</body>
</html>
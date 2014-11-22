<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BicyclesInc - Login</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
  <body>
      <header>
          <h2>BicyclesInc - Login</h2>
      </header>
      <nav>
	<ul class="nav">	
	    <li><a href="index.jsp">Home</a></li>
	    <li><a href="register.jsp">Register</a></li>

	</ul>
</nav>
      <article>
          
          <div class="login">
          <form method="POST"  action="login"> <!-- Calling the Login Servlet -->
            <ul>
            <fieldset>
            	<legend><b>Login</b></legend>
                <li>User Name:&nbsp;<input type="text" name="username" ></li></br>
                <li>Password:&nbsp;<input type="password" name="password"></li></br>                
                </fieldset>
                <input type="submit" value="login">
            </ul>            
        </form>
        </div>
    </article>
    <footer>
        <ul>
            <!-- <li class="footer"><a href="index.jsp">Home</a></li> -->
        </ul>
    </footer>
</body>
</html>
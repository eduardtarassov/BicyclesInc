<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BicyclesInc - Demographic Search</title>
       <link rel="stylesheet" type="text/css" href="Styles.css"/>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>

        <header>
            <h2>BicyclesInc - Demographic Search</h2>
        </header>
        <div class="search">
    <form method="POST" action="search">
        <table border="0" width="300">           
            <tr><td colspan=2 align="right">Search User:&nbsp;<input  type="text" name="username" id="username">
                </td></tr>
            <tr><td colspan=2 align="right">
                <input  type="submit" name="submit" value="Search"></td></tr>
        </table>
    </form>
</div>

<nav>
		<ul class="nav">
			<li><a href="index.jsp">Home</a></li>
			<li><a href="region.jsp">Demographics</a></li>
			<li><a href="logout">Logout</a></li>
		</ul>
	</nav>
        <article>
      	 <p>This database contains data on population, salary and environmental variables for all regions (provinces) of the Netherlands.</p>
         <p>Select which region you wish to search and for what variable.</p>
        
        <form method="POST" action="region">
        <p>Select region</p>
            <select name="RegionName">
  			<option value="Drenthe">Drenthe</option>
  			<option value="Flevoland">Flevoland</option>
  			<option value="Friesland">Friesland</option>
  			<option value="Gelderland">Gelderland</option>
  			<option value="Groningen">Groningen</option>
  			<option value="Limburg">Limburg</option>
  			<option value="North Brabant">North Brabant</option>
  			<option value="North Holland">North Holland</option>
  			<option value="Overijssel">Overijssel</option>
  			<option value="South Holland">South Holland</option>
  			<option value="Utrecht">Utrecht</option>
  			<option value="Zeeland">Zeeland</option>
			</select>
		<p>Select variables</p>	
			<select name="variable">
  			<option value="Population">Population</option>
  			<option value="DryDaysPerYear">No. of dry days per year</option>
  			<option value="GDP">GDP</option>
  			<option value="AveragePopSalary">Average salary of the Population</option>
			</select>
		
		<input type="submit" value="Submit Query">
		</form>
		<% %>

        </article>
       
    </body>
</html>
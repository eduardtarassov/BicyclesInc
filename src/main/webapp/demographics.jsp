<%@ page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="containers.*" %>
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
            <h2>BicyclesInc - Demographics</h2>
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
        <%DemographicInfo dInfo = (DemographicInfo) request.getAttribute("DemographicInfo");%>
        
</head>
<div class="dem">
<table class="dem" style="width:50%">
  <tr>
    <th>Region</th>
    <th>Population</th>		
    <th>Dry Days Per Year</th>
    <th>GDP</th>
    <th>Average Salary</th>
  </tr>
  <tr>
  	<td><%=dInfo.getRegionName()%></td>
    <td><%=dInfo.getPopulation()%></td>
    <td><%=dInfo.getDryDaysPerYear()%>/360</td>		
    <td>£<%=dInfo.getGdp()%></td>
    <td>£<%=dInfo.getAveragePopSalary()%></td>
  </tr>
</table> 
</div>
 <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Region', 'Average Salary of Population'],
          ['<%=dInfo.getRegionName()%>', <%=dInfo.getAveragePopSalary()%>], 
        ]);

        var options = {
          title: 'Demographics: Average Salary',
          vAxis: {title: 'Region',  titleTextStyle: {color: 'red'}}
        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="chart_div"></div>
  </body>


 <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Region', 'GDP', { role: 'style' }],
          ['<%=dInfo.getRegionName()%>', <%=dInfo.getGdp()%>, 'gold'], 
        ]);

        var options = {
          title: 'Demographics: GDP',
          vAxis: {title: 'Region',  titleTextStyle: {color: 'red'}}
        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div1'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="chart_div1"></div>
  </body>


 <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Region', 'Dry Days Per Year', { role: 'style' }],
          ['<%=dInfo.getRegionName()%>', <%=dInfo.getDryDaysPerYear()%>, 'red'], 
        ]);

        var options = {
          title: 'Demographics: Dry Days Per Year',
          vAxis: {title: 'Region',  titleTextStyle: {color: 'red'}}
        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div2'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="chart_div2"></div>
  </body>
  
   <head>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Region', 'Population', { role: 'style' }],
          ['<%=dInfo.getRegionName()%>', <%=dInfo.getPopulation()%>, 'green'], 
        ]);

        var options = {
          title: 'Demographics: Population',
          vAxis: {title: 'Region',  titleTextStyle: {color: 'red'}}
        };
        var chart = new google.visualization.BarChart(document.getElementById('chart_div3'));
        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="chart_div3"></div>
  </body>
</article>
    </body>
</html>
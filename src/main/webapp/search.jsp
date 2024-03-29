<%@ page import="java.util.*" %>
<%@ page import="containers.ProfileInfo" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>

<html>
<head>
</head>
<body>
<table width="700px" align="center"
       style="border:1px solid #000000;">
    <tr>
        <td colspan=4 align="center"
            style="background-color:teal">
            <b>User Record</b></td>
    </tr>
    <tr style="background-color:lightgrey;">
        <td><b>Username</b></td>
        <td><b>Profile</b></td>
    </tr>
    <%
        int count = 0;
        String color = "#F9EBB3";
        if (request.getAttribute("profiles_found") != null) {
            LinkedList<ProfileInfo> profiles = (LinkedList<ProfileInfo>) request.getAttribute("profiles_found");
            Iterator itr = profiles.iterator();
            while (itr.hasNext()) {

                if ((count % 2) == 0) {
                    color = "#eeffee";
                }
                count++;
                ProfileInfo proInfo = (ProfileInfo) itr.next();
    %>
    <tr style="background-color:<%=color%>;">
        <td><%=proInfo.getUsername()%></td>
        <td><a href="Profile/<%=proInfo.getUsername()%>">Profile</a></td>

    </tr>
    <%
            }
        }
        if (count == 0) {
    %>
    <tr>
        <td colspan=4 align="center"
            style="background-color:#eeffee"><b>No Record Found..</b></td>
    </tr>
    <%            }
    %>
</table>
</body>
</html>
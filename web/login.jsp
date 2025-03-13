<%-- 
    Document   : login
    Created on : Mar 9, 2025, 3:07:49 PM
    Author     : Quoc Bao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <%
        if (request.getAttribute("ERROR") != null) {
    %>
    <h3><%= request.getAttribute("ERROR")%></h3>
    <%
    } else if (request.getAttribute("NOTI") != null) {

    %>
    <h3>${requestScope.NOTI}</h3>
    <%        
        }
    %>
    <body>
        <h1>Login</h1>
        <form action="MainController" method="POST">
            <input type="hidden" name ="action" value ="login"/>
            <input type ="text" name ="txtmail" placeholder="Email address" required=""/><br/>
            <input type ="text" name ="txtpassword" placeholder="Password" required=""/>
            <input type="submit" value="Login"/>
        </form>
    </body>
    <p>or</p>
    <form action="MainController" method ="POST">
        <input type="hidden" name ="action" value="register"/>
        <input type="submit" value="Register"/>
    </form>
</html>

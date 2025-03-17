<%-- 
    Document   : editUser
    Created on : Mar 15, 2025, 8:11:19 PM
    Author     : Quoc Bao
--%>

<%@page import="model.UsersDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <%
        session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("MainController?action=login").forward(request, response);
        } else {
            UsersDTO user = (UsersDTO) session.getAttribute("user");
            String username = user.getUsername();
            int isAdmin = user.getIsAdmin();
            if (isAdmin == 0) {
                request.getRequestDispatcher("MainController?action=login").forward(request, response);
            }

    %>
    <%        
        if (request.getAttribute("ERROR") != null) {
    %>
    <h3><%= request.getAttribute("ERROR")%></h3>
    <%
        }
    %>
    
        <form action="MainController" method="POST">
            <input type="hidden" name="oldEmail" value="${requestScope.userToUpdate.email}"/>
            <input type="hidden" name="action" value="update"/>
            <p>Username: <input type="text" name="username" value ="${requestScope.userToUpdate.username}"/></p>
            <p>Password: <input type="password" name="password" value ="${requestScope.userToUpdate.password}"/></p>
            <p>Confirm password: <input type="password" name="checkPassword" value ="${requestScope.userToUpdate.password}"/></p>
            <p>Email: <input type="text" name="email" value ="${requestScope.userToUpdate.email}"/></p>
            <p>Date of birth: <input type="date" name="date" value ="${requestScope.userToUpdate.dateOfBirth}"/></p>
            <input type="submit" value="Save"/>
        </form>
    </body>
    <%}%>
</html>

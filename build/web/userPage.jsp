<%-- 
    Document   : userPage
    Created on : Mar 15, 2025, 4:38:50 PM
    Author     : Quoc Bao
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.UsersDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
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
            }
        %>

        <table border="1" cellspacing="1">
            <%
                ArrayList<UsersDTO> list = (ArrayList<UsersDTO>) request.getAttribute("listUser");
                if (list == null) {
            %>
            <h3>
                Empty list!
            </h3>
            <%
            } else {
            %>
            <tr>
                <td>Username</td>    
                <td>Email</td>    
                <td>Date of birth</td> 
            </tr>
            <%
                for (UsersDTO users : list) {
                    pageContext.setAttribute("user", users);
            %>
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.dateOfBirth}</td>
                <td>
                    <form action="MainController" method="GET">
                        <input type="hidden" name ="oldEmail" value="${user.email}">
                        <input type="hidden" name="action" value="editUser">
                        <input type="submit" value ="Edit">
                    </form>
                </td>            
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>

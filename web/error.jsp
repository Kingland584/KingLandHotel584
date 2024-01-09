<%-- 
    Document   : error
    Created on : Jan 9, 2024, 7:50:32 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error</h1>
        <p><%= request.getParameter("message") %></p>
        <a href="bookCust.jsp">Back</a>
    </body>
</html>


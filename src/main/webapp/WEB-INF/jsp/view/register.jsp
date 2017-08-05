<%-- 
    Document   : register
    Created on : Mar 27, 2017, 3:04:27 PM
    Author     : s1153307
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <a href="<c:url value="/" />">Return to index</a>
        <h2>Register</h2>
        <form action="register" method="POST">
            <label for="username">Username:</label><br/>
            <input type="text" id="username" name="username" /><br/><br/>
            <label for="password">Password:</label><br/>
            <input type="password" id="password" name="password" /><br/><br/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="roles" value="ROLE_USER"/>
            <input type="submit" value="register"/>
        </form>
    </body>
</html>

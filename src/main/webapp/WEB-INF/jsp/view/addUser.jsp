<%-- 
    Document   : addUser
    Created on : 2017年4月6日, 上午09:48:27
    Author     : TYK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add User</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br /><br />
        <a href="<c:url value="/" />">Return to index</a><br /><br />
        <h2>Create a User</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="forumUser">
            <form:label path="username">Username</form:label><br/>
            <form:input type="text" path="username" /><br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="password" path="password" /><br/><br/>
            <form:label path="roles">Roles</form:label><br/>
            <form:checkbox path="roles" value="ROLE_USER" />ROLE_USER
            <form:checkbox path="roles" value="ROLE_ADMIN" />ROLE_ADMIN
            <br /><br />
            <input type="submit" value="Add User"/>
        </form:form>
    </body>
</html>

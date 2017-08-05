<%-- 
    Document   : editUser
    Created on : Apr 11, 2017, 2:06:59 PM
    Author     : s1158854
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${forumUser.roles} Edit User</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <a href="<c:url value="/" />">Return to index</a>
        <h2>Edit a User</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="forumUser">
            <form:label path="username">Username</form:label><br/>
            ${forumUser.username}<br/><br/>
            <form:label path="password">Password</form:label><br/>
            <form:input type="password" path="password" /><br/><br/>
            <form:label path="roles">Roles</form:label><br/>
            <form:checkbox path="roles" value="ROLE_USER"  />ROLE_USER
            <form:checkbox path="roles" value="ROLE_ADMIN"  />ROLE_ADMIN
            <br /><br />
            <input type="submit" value="Save"/>
        </form:form>
    </body>
</html>
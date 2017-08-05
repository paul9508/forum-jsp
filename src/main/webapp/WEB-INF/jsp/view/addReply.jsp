<%-- 
    Document   : addUser
    Created on : 2017年4月6日, 上午09:48:27
    Author     : TYK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add a Reply</title>
    </head>
    <body>
        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form><br />
        </security:authorize>
        <a href="<c:url value="/" />">Return to index</a><br /><br />
        <security:authorize access="isAnonymous()">
            <a href="<c:url value="/login" />">Login</a><br /><br />
            <a href="<c:url value="/register" />">Register</a><br /><br />
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">Manage User Accounts</a>
        </security:authorize>
    </form>
    <h2>Add reply</h2>
    <form:form method="POST" enctype="multipart/form-data" modelAttribute="reply">
        <form:label path="content">Reply Message Body</form:label><br/>
        <form:textarea path="content" rows="5" cols="30" /><br/><br/>
        <b>Attachments</b><br/>
        <div>
            <input type="file" id="fileselect" name="attachments" multiple="multiple" />
            <div id="filedrag">or drop files here</div>
        </div>
        <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>

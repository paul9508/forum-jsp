<%-- 
    Document   : addPoll
    Created on : 2017/4/9, 上午 12:19:13
    Author     : Gary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New Poll</title>
    </head>
    <body>
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <br />
        <a href="<c:url value="/" />">Return to index</a>
        <h2>Create a Poll</h2>
        <form:form method="POST" enctype="multipart/form-data" modelAttribute="poll">
            <form:label path="question">Question</form:label><br/>
            <form:input type="text" path="question" /><br/><br/>
            <form:label path="answer1">answer1</form:label><br/>
            <form:input type="text" path="answer1" /><br/><br/>
            <form:label path="answer2">answer2</form:label><br/>
            <form:input type="text" path="answer2" /><br/><br/>
            <form:label path="answer3">answer3</form:label><br/>
            <form:input type="text" path="answer3" /><br/><br/>
            <form:label path="answer4">answer4</form:label><br/>
            <form:input type="text" path="answer4" /><br/><br/>
            <br />
            <input type="submit" value="Add Poll"/>
        </form:form>
    </body>
</html>

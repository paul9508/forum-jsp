<%-- 
    Document   : history
    Created on : 2017/4/12, 下午 05:32:01
    Author     : Gary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Poll History</title>
    </head>
    <body>
        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form><br /><br />
        </security:authorize>
        <a href="<c:url value="/" />">Return to index</a><br /><br />
        <security:authorize access="isAnonymous()">
            <a href="<c:url value="/login" />">Login</a><br /><br />
            <a href="<c:url value="/register" />">Register</a><br /><br />
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
            <a href="<c:url value="/poll/create" />">Create Poll</a><br /><br />
        </security:authorize>
        <c:choose>
            <c:when test="${fn:length(poll) == 0}">
                <i>There are no Poll in the system.</i>
            </c:when>
            <c:otherwise>
                <c:forEach items="${poll}" var="polls">
                    <table BORDER="1">
                        <tr>
                            <th>${polls.question}</th>
                            <th>Result</th>
                        </tr>
                        <tr>
                            <td>${polls.answer1}</td>
                            <td>${polls.count1}</td>
                        </tr>
                        <tr>
                            <td>${polls.answer2}</td>
                            <td>${polls.count2}</td>
                        </tr>
                        <tr>
                            <td>${polls.answer3}</td>
                            <td>${polls.count3}</td>
                        </tr>
                        <tr>
                            <td>${polls.answer4}</td>
                            <td>${polls.count4}</td>
                        </tr>
                    </table><br />
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>
</html>

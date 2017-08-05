<%-- 
    Document   : message
    Created on : Mar 26, 2017, 12:22:12 AM
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <style>
            table {
                width:50%;
            }
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 5px;
                text-align: left;
            }
            table#t01 tr:nth-child(even) {
                background-color: #eee;
            }
            table#t01 tr:nth-child(odd) {
                background-color:#fff;
            }
            table#t01 th {
                background-color: black;
                color: white;
            }
        </style>
        <title>${categoryType}</title>
    </head>
    <body>
        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/><br /><br />
            </form>
        </security:authorize>
        <a href="<c:url value="/" />">Return to index</a>
        <security:authorize access="isAnonymous()">
            <br /><br />
            <a href="<c:url value="/login" />">Login</a><br /><br />
            <a href="<c:url value="/register" />">Register</a>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <br /><br />
            <a href="<c:url value="/user" />">Manage User Accounts</a>
        </security:authorize>
         
        <h2>${categoryType} Topic</h2>
        <h3>Number of Topics:${fn:length(threads)}</h3>
        <security:authorize access="isAuthenticated()">
        <a href="<c:url value="/category/${categoryType}/create" />">Create a Thread</a><br /><br />
        </security:authorize>
        <c:if test="${not empty threads}">
        <table id="t01">
            <tr>
            <a href="category.jsp"></a>
            <th>Thread Topic</th>
            <th>User</th> 
            <th>Time</th>
                <security:authorize access="hasRole('ADMIN')">
                <th>Action</th>
                </security:authorize>
        </tr>
        <c:forEach items="${threads}" var="thread">
            <tr>
                <fmt:formatDate value="${thread.time}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss" />
                <td><a href="<c:url value="/category/${categoryType}/${thread.id}" />">${thread.topic}</a></td>
                <td>${thread.user}</td>
                <td>${dateString}</td>
                <security:authorize access="hasRole('ADMIN')">
                    <td>
                        [<a href="<c:url value="/category/${categoryType}/delete/${thread.id}" />">Delete</a>]
                    </td>
                </security:authorize>
            </tr>
        </c:forEach>
    </table>
        </c:if>
</body>

</html>

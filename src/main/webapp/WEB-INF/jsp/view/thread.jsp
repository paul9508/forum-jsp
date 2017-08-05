<%-- 
    Document   : categorythread
    Created on : Mar 26, 2017, 5:28:56 PM
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table, th, td {
                border: 1px solid black;
                border-collapse: collapse;
            }
            th, td {
                padding: 5px;
                text-align: left;
            }
            th {
                width: 20%;
                background-color: #eee;
            }

        </style>
        <title>${categoryType}:${thread.topic}</title>
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
        <h2>Category:${categoryType}</h2>
        <h2>Thread Topic: ${thread.topic}</h2>
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value="/category/${categoryType}/${thread.id}/addreply" />">Reply a Message</a><br /><br /></security:authorize>
            <table style="width:40%">
                <tr>
                    <th rowspan="2">UserName:</th>
                </tr>
                <tr>
                    <td>${thread.user}</td>
            </tr>
            <tr>
                <th rowspan="2">Time:</th>
            </tr>
            <tr>
                <fmt:formatDate value="${thread.time}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss" />
                <td>${dateString}</td>
            </tr>
            <tr>
                <th rowspan="2">Topic:</th>
            </tr>
            <tr>
                <td>${thread.content}</td>
            </tr>
        </table>
        <security:authorize access="hasRole('USER')">
            <c:if test="${not empty thread.attachments}">
                File Attachment:
                <c:forEach items="${thread.attachments}" var="attachment"
                           varStatus="status">
                    <c:if test="${!status.first}">, </c:if>
                    <a href="<c:url value="/category/${thread.type}/${thread.id}/attachment/${attachment.name}" />">
                        <c:out value="${attachment.name}" /></a>
                    </c:forEach>
                </c:if>
            </security:authorize>
                    <h4>Number of Reply:${fn:length(threadreplies)}</h4>
            <c:forEach items="${threadreplies}" var="threadreply" varStatus="status">
            <p>Reply#${status.index +1}</p>
            <table style="width:40%">
                <tr>
                    <th>UserName:</th>
                    <td>${threadreply.user}</td>
                </tr>
                <tr>
                    <th>Time:</th>
                    <td><fmt:formatDate value="${threadreply.time}" var="ReplydateString" pattern="yyyy-MM-dd HH:mm:ss" />${ReplydateString}</td>
                </tr>
                <tr>
                    <th>Message:</th>
                    <td>${threadreply.content}</td>
                </tr>
                <security:authorize access="hasRole('ADMIN')">
                    <tr>
                        <th>Action:</th>
                        <td>
                            [<a href="<c:url value="/category/${categoryType}/${thread.id}/delete/${threadreply.id}" />">Delete</a>]
                        </td>
                    </tr>
                </security:authorize>
            </table>
            <security:authorize access="hasRole('USER')">
                <c:if test="${not empty threadreply.attachments}">
                    File Attachment:
                    <c:forEach items="${threadreply.attachments}" var="attachment"
                               varStatus="status">
                        <c:if test="${!status.first}">, </c:if>
                        <a href="<c:url value="/category/${thread.type}/${thread.id}/${threadreply.id}/attachment/${attachment.name}" />">
                            <c:out value="${attachment.name}" /></a>
                        </c:forEach>
                    </c:if>
                </security:authorize>
            </c:forEach>

    </body>
</html>

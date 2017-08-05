<%-- 
    Document   : test
    Created on : Mar 25, 2017, 10:25:29 PM
    Author     : Paul
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Forum</title>
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
                width: 30%;
                background-color: #eee;
            }
        </style>
    </head>
    <body>
        <security:authorize access="isAuthenticated()">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a href="<c:url value="/login" />">Login</a><br /><br />
            <a href="<c:url value="/register" />">Register</a>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <br />
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
            <a href="<c:url value="/poll/create" />">Create Poll</a>
        </security:authorize>
        <h1>Wellcome to Forum</h1>
        <c:choose>
            <c:when test="${fn:length(poll) == 0}">
                <i>There are no Poll in the system.</i>
            </c:when>
            <c:otherwise>
                <form:form method="POST" modelAttribute="answerpoll">
                    <c:forEach items="${poll}" var="polls"> 
                        <h3>Poll question:${polls.question}</h3>
                    <table BORDER="1">   
                            <tr>
                                <th>Option</th>
                                <th>Result</th>
                            </tr> 
                            <tr>
                                <td id="option"><security:authorize access="isAuthenticated()"><c:if test="${empty pollcheck}" ><input type="checkbox" name="answer" value="count1"/></c:if></security:authorize>${polls.answer1}</td>
                                <td>${polls.count1}</td>
                            </tr>
                            <tr>
                                <td id="option"><security:authorize access="isAuthenticated()"><c:if test="${empty pollcheck}" ><input type="checkbox" name="answer" value="count2"/></c:if></security:authorize>${polls.answer2}</td>
                                <td>${polls.count2}</td>
                            </tr>
                            <tr>
                                <td id="option"><security:authorize access="isAuthenticated()"><c:if test="${empty pollcheck}" ><input type="checkbox" name="answer" value="count3"/></c:if></security:authorize>${polls.answer3}</td>
                                <td>${polls.count3}</td>
                            </tr>
                            <tr>
                                <td id="option"><security:authorize access="isAuthenticated()"><c:if test="${empty pollcheck}" ><input type="checkbox" name="answer" value="count4"/></c:if></security:authorize>${polls.answer4}</td>
                                <td>${polls.count4}</td>
                            </tr>
                            <input type="hidden" name="id" value="${polls.id}"/>
                        </c:forEach>
                        <security:authorize access="isAuthenticated()"><tr>
                                <td colspan="2"><c:choose><c:when test="${empty pollcheck}" ><input type="submit" value="Poll"/></c:when><c:otherwise>You join this poll already!</c:otherwise></c:choose></td>  
                            </tr></security:authorize>
                        </table>
                </form:form>
            </c:otherwise>
        </c:choose>
        <h3>Category List</h3>
        <a href="<c:url value="category/Lecture" />">Lecture</a><br /><br />
        <a href="<c:url value="category/Lab" />">Lab</a><br /><br />
        <a href="<c:url value="category/Other" />">Other</a><br /><br />
    </body>
</html>

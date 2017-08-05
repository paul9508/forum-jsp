<%-- 
    Document   : listUser
    Created on : 2017/4/5, 下午 05:46:32
    Author     : Gary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User List</title>
    </head>
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
                background-color: #eee;
            }
        </style>
  <body>
    <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
      <input type="submit" value="Log out" />
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <br />
    <a href="<c:url value="/" />">Return to index</a>
    <h2>Users</h2>
    <a href="<c:url value="/user/create" />">Create a User</a><br /><br />
    <c:choose>
      <c:when test="${fn:length(forumUsers) == 0}">
        <i>There are no users in the system.</i>
      </c:when>
      <c:otherwise>
        <table>
          <tr>
            <th>Username</th>
            <th>Password</th>
            <th>Roles</th>
            <th>Action</th>
          </tr>
          <c:forEach items="${forumUsers}" var="user">
            <tr>
              <td>${user.username}</td>
              <td>${user.password}</td>
              <td>
                <c:forEach items="${user.roles}" var="role" varStatus="status">
                  <c:if test="${!status.first}">, </c:if>
                  ${role.role}
                </c:forEach>
              </td>
              <td>
                [<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>],
                [<a href="<c:url value="/user/edit/${user.username}" />">Edit</a>]
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:otherwise>
    </c:choose>
  </body>
</html>


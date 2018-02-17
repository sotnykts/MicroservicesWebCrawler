<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Page</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Statistics:</h4>
    <a href="${sessionScope.contextPath}/StatisticServlet">Web Crawler statistics</a>
    <a href="${sessionScope.contextPath}/statistic/nodestatistics.jsp">Node's statistics</a>
    <a style="background-color: lightgray" href="#">User's statistics</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <h2> Users statistics: </h2>
    <table border="2" width="80%">
        <tr>
            <th>User ID</th>
            <th>User's Name</th>
            <th>User's Login</th>
            <th>Roles</th>
        </tr>

        <c:forEach var="user" items="${requestScope.userStatistics}">

            <tr>
                <td><c:out value="${user.userId}"/></td>
                <td><c:out value="${user.userName}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.userRole}"/></td>
            </tr>

        </c:forEach>
    </table>
    <br>
    <br>

    <h2> Web Crawler's roles groups: </h2>

    <table border="2" width="80%">
        <tr>
            <th>Roles group ID</th>
            <th>Group's name</th>
            <th>Description</th>
        </tr>
        <c:forEach var="roles" items="${requestScope.rolesGroup}">

            <tr>
                <td><c:out value="${roles.groupId}"/></td>
                <td><c:out value="${roles.groupName}"/></td>
                <td><c:out value="${roles.description}"/></td>
            </tr>

        </c:forEach>
    </table>
    <br>
    <br>

</div>
<%@include file="/include/footer.jsp" %>
</body>
</html>

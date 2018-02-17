<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage users</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Management:</h4>
    <a href="management.jsp">Manege nodes</a>
    <a style="background-color: lightgray" href="#">Manage users</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <h4>Management user's information:</h4>
    <a href="adminaddnewuser.jsp">Add new user</a><br>
    <a href="admindeleteuser.jsp">Delete user</a><br>
    <a href="adminchangedata.jsp">Change user's information</a><br>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

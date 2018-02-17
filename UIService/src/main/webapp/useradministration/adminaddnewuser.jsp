<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin add new user</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Management:</h4>
    <a href="#">Manege nodes</a>
    <a style="background-color: lightgray" href="${sessionScope.contextPath}/useradministration/usersmanage.jsp">Manage users</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

            <c:if test="${requestScope.exception != null}">

                <p style="color: red">${requestScope.exception} </p>

            </c:if>

            <c:if test="${requestScope.newUser == true}">

                <h4 style="color: blue">Add new user was successfully!</h4>

            </c:if>

            <h4> Please write information about new user:</h4>

            <form action="${sessionScope.contextPath}/AdminAddNewUserServlet" method="post">

            User's name:<br>
                <input type="text" name="name" /><br>

            User's login:<br>
                <input type="text" name="login" /><br>

            User's password:<br>
                <input type="password" name="pass1" /><br>

            User's password again:<br>
                <input type="password" name="pass2" /><br>

            User's roles in APP:<br>
                <input type="checkbox" name="role" value="Guest" /> Guest
                <input type="checkbox" name="role" value="User" /> User
                <input type="checkbox" name="role" value="Admin" /> Admin<br><br>

                <input type="submit" name="submit" />
        </form>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change user's information</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>User's menu:</h4>
    <a href="${sessionScope.contextPath}/user/profile.jsp"> Profile</a>
    <a style="background-color: lightgray" href="#">Change user's information</a>
    <a href="${sessionScope.contextPath}/user/changeuserpass.jsp">Change user's password</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <c:if test="${requestScope.verifyUserData == false}">

        <p style="color: red">

            You have mistake in login or password. Please try again.

        </p>

    </c:if>

        <h3>
            Change user information:
        </h3>

        <form action="${sessionScope.contextPath}/ChangeUserDataServlet" method="post">

            <input type="hidden" name="securityToken" value="${sessionScope.securityToken}"/>

            <h4>New Information:</h4>

            Enter new name:<br>
            <input type="text" name="newName"/><br>
            Enter new login:<br>
            <input type="text" name="newLogin"/><br><br>

            <h4>Old information:</h4>

            Name: <p style="font-weight:900">${sessionScope.securityToken.getUserData().getUserName()}</p>
            Enter your login:<br>
            <input type="text" name="oldLogin"/><br>
            Enter your password:<br>
            <input type="password" name="password"/><br><br>

            <input type="submit" value="submit"/>
        </form>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

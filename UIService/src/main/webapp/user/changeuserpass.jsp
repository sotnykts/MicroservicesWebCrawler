<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change User Password</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>User's menu:</h4>
    <a href="${sessionScope.contextPath}/user/profile.jsp"> Profile</a>
    <a href="${sessionScope.contextPath}/user/changeuserdata.jsp">Change user's information</a>
    <a style="background-color: lightgray" href="#">Change user's password</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <c:if test="${requestScope.exceptionMassage != null}">

        <p style="color: red"> ${requestScope.exceptionMassage} </p>

    </c:if>

    <h3>
        Change user password:
    </h3>

    <form action="${sessionScope.contextPath}/ChangeUserPassServlet" method="post">

        <input type="hidden" name="mySecurityToken" value="${sessionScope.securityToken}"/>

        <h4>New Information:</h4>

        Enter new password:<br>
        <input type="password" name="new1Password"/><br>
        Enter new password again:<br>
        <input type="password" name="new2Password"/><br><br>

        <h4>Old information:</h4>

        Name: <p style="font-weight:900">${sessionScope.securityToken.getUserData().getUserName()}</p>
        Enter your login:<br>
        <input type="text" name="login"/><br>
        Enter old password:<br>
        <input type="password" name="oldPassword"/><br><br>

        <input type="submit" value="submit"/>
    </form>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>


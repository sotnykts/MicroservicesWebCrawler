<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Page</title>
    <style>
        p.thick{
            font-weight: bold;
        }
    </style>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>User's menu:</h4>
    <a style="background-color: lightgray" href="#"> Profile</a>
    <a href="${sessionScope.contextPath}/user/changeuserdata.jsp">Change user's information</a>
    <a href="${sessionScope.contextPath}/user/changeuserpass.jsp">Change user's password</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

<h2> Profile: </h2>
    <p class="thick"> User name:</p>
    <c:out value='${sessionScope.securityToken.userData.userName}'/><br><br>
    <p class="thick"> User login: </p>
    <c:out value='${sessionScope.securityToken.userData.login}'/><br><br>
    <p class="thick"> User permission: </p>
    <c:out value='${sessionScope.securityToken.toPermissionString()}'/><br><br>

</div>
<%@include file="/include/footer.jsp" %>
</body>
</html>

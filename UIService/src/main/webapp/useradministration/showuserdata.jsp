<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show user data</title>
</head>
<body>

<h5 class="thick"> User name:</h5>
<c:out value='${requestScope.userToken.userData.userName}'/><br><br>
<h5 class="thick"> User login: </h5>
<c:out value='${requestScope.userToken.userData.login}'/><br><br>
<h5 class="thick"> User permission: </h5>
<c:out value='${requestScope.userToken.toPermissionString()}'/><br><br>

</body>
</html>

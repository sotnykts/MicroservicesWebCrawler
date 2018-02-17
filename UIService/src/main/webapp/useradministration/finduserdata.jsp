<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find user data</title>
</head>
<body>

<form action="${sessionScope.contextPath}/AdminFindUserServlet" method="post">

    <input type="hidden" name="nextJsp" value="${nextJsp}">
    <input type="hidden" name="backJsp" value="${backJsp}">

    User's Id:<br>
    <input type="text" name="userId"/><br>

    User's login:<br>
    <input type="text" name="login"/><br>

    User's name:<br>
    <input type="text" name="name"/><br><br>

    <input type="submit" name="submit"/>
</form>

</body>
</html>

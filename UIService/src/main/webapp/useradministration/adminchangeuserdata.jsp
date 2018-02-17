<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change user data</title>
</head>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Management:</h4>
    <a href="#">Manege nodes</a>
    <a style="background-color: lightgray" href="${sessionScope.contextPath}/useradministration/usersmanage.jsp">Manage
        users</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <c:if test="${requestScope.userToken != null}">

        <%@include file="showuserdata.jsp" %>

        <h4>Write new user's information for change (if you want change user password or login, you mast write
            both):</h4>

        <form action="${sessionScope.contextPath}/AdminChangeUserDataServlet" method="post">

            New user's name:<br>
            <input type="text" name="name"/><br>

            New user's login:<br>
            <input type="text" name="login"/><br>

            New user's password:<br>
            <input type="password" name="pass1"/><br>

            New user's password again:<br>
            <input type="password" name="pass2"/><br>

            New user's roles in APP(the user will only save those roles that will be specified now, the deprecated
            ones will be deleted):<br>
            <input type="checkbox" name="role" value="Guest"/> Guest
            <input type="checkbox" name="role" value="User"/> User
            <input type="checkbox" name="role" value="Admin"/> Admin<br><br>

            <input type="hidden" name="userId" value="${requestScope.userToken.userData.userId}">

            <input type="submit" name="Change user's information" value="Change user's information"/>
        </form>

    </c:if>

    <c:if test="${requestScope.exception == true}">



    </c:if>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

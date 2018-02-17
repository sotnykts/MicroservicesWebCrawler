<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Delete User</title>
    <style>
        p.thick {
            font-weight: bold;
        }
    </style>
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

    <h4> Please write information about user which need delete from system(you may write only one field):</h4>

    <c:set var="nextJsp" value="/useradministration/admindeleteuser.jsp"/>
    <c:set var="backJsp" value="/useradministration/admindeleteuser.jsp"/>

    <%@include file="finduserdata.jsp" %>

    <c:if test="${requestScope.userToken eq sessionScope.securityToken}">

        <p style="color:red"> You can't delete your account.</p>

    </c:if>

    <c:if test="${requestScope.exception}">

        <p style="color: red"> Something wrong! Please try again!</p>

    </c:if>

    <c:if test="${requestScope.remoteUser == true}">

        <h4 style="color: blue">Delete user was successfully!</h4>

    </c:if>

    <c:if test="${requestScope.userToken != null}">

        <%@include file="showuserdata.jsp" %>

        <c:if test="${!(requestScope.userToken eq sessionScope.securityToken)}">

            <form action="${sessionScope.contextPath}/AdminDeleteUserServlet" method="post">

                <input type="hidden" name="userId" value="${requestScope.userToken.userData.userId}">
                <input type="submit" name="delete user" value="delete user"/>

            </form>

        </c:if>

    </c:if>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

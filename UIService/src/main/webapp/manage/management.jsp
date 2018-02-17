<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Management</title>
</head>
<body>

<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Management:</h4>
    <a href="${sessionScope.contextPath}/NodeInformationServlet">Manege nodes</a>
    <a href="${sessionScope.contextPath}/useradministration/usersmanage.jsp">Manage users</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

            Welcome ${sessionScope.securityToken.userData.userName}! <br>

            <c:choose>

                <c:when test="${requestScope.roleId eq '3'}">

                    You don't have enough rights to use this part of the site.

                </c:when>
                <c:when test="${requestScope.roleId eq '2'}">

                    You can use only manage node.<br>
                    But, You don't have enough rights to use user's manage.

                </c:when>
                <c:when test="${requestScope.roleId eq '1'}">

                    You have full access!

                </c:when>
                <c:otherwise>
                    <p style="color: red" > Some thing wrong! Please re login in system!</p>
                </c:otherwise>
            </c:choose>

</div>

<%@include file="/include/footer.jsp" %>

</body>
</html>

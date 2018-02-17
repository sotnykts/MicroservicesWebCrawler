<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find user's data for change</title>
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

    <h4> Please write information about user which information you want change (you may write only one field):</h4>

    <c:set var="nextJsp" value="/useradministration/adminchangeuserdata.jsp"/>
    <c:set var="backJsp" value="/useradministration/adminchangedata.jsp"/>
    <%@include file="finduserdata.jsp" %>

    <c:if test="${requestScope.exception}">

        <p style="color: red"> Something wrong! Please try again!</p>

    </c:if>

    <c:if test="${requestScope.alteredUser == true}">

        <h4 style="color: blue">Change user's information was successfully!</h4>

        <c:if test="${requestScope.userToken != null}">

            <%@include file="showuserdata.jsp" %>

        </c:if>

    </c:if>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

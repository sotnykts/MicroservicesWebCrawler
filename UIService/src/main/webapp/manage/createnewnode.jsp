<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new nodes</title>
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

    <h3 style="color: red">Sorry. At the moment this functionality does not work.  </h3>

    <form action="${sessionScope.contextPath}/NodeInformationServlet" method="get">
        <input type="submit" value="return"/>
    </form>

</div>

<%@include file="/include/footer.jsp" %>

</body>
</html>

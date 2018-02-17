<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meeting page</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <br>
    <a style="background-color: lightgray" href="#"> Home</a>
    <h4>Statistics:</h4>
    <a href="${sessionScope.contextPath}/StatisticServlet">Web Crawler's statistics</a>
    <a href="${sessionScope.contextPath}/statistic/nodestatistics.jsp">Node's statistics</a>
    <a href="${sessionScope.contextPath}/UserStatisticServlet">User's statistics</a>
    <h4>Management:</h4>
    <a href="${sessionScope.contextPath}/NodeInformationServlet">Manage Web Crawler's nodes</a>
    <a href="${sessionScope.contextPath}/useradministration/usersmanage.jsp">Manage user's information</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>

</nav>

<div class="w3-container" style="margin-left: 17%">

    <h2> Welcome to the Web Crawler! </h2>
    <br>
    <p>Main idea of this task is to create high-performance web-crawler, backed by database.
        Crawler itself is a simple tool which starts from some starting URL, downloads page parse links (local and
        external)
        parses page content, saves page content processed to database saves links as a new starting points for later
        processing.
    </p>

</div>
<%@include file="/include/footer.jsp" %>
</body>
</html>

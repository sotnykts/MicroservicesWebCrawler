<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <br>
    <a style="background-color: lightgray" href="#"> Home</a>
    <h4>Statistics:</h4>
    <a href="#">Web Crawler's statistics</a>
    <a href="#">Node's statistics</a>
    <a href="#">User's statistics</a>
    <h4>Management:</h4>
    <a href="#">Manage Web Crawler's nodes</a>
    <a href="#">Manage user's information</a>

</nav>

<div class="w3-container" style="margin-left: 17%">

    <c:if test="${sessionScope.verifyLogin == null}">
        <br>
        <h3>
            To get started, you need to enter your login and password.
        </h3>
    </c:if>
    <c:if test="${requestScope.verifyLogin == false}">

        <br>
        <h3 style="color: red">
            Sorry! Your login or password was incorrect, or does not exist in DB.
        </h3>
        <p style="color: red"> Please try again.</p>

    </c:if>

    <form action="/WebCrawlerUI/LoginServlet" method="post">
        Enter your login:<br>
        <input type="text" name="login"/><br>
        Enter your password:<br>
        <input type="password" name="password"/><br><br>
        <input type="submit" value="submit"/>
    </form>

</div>
<%@include file="/include/footer.jsp" %>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Node's statistics</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Statistics:</h4>
    <a href="${sessionScope.contextPath}/StatisticServlet">Web Crawler statistics</a>
    <a style="background-color: lightgray" href="#">Node's statistics</a>
    <a href="${sessionScope.contextPath}/UserStatisticServlet">User's statistics</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <br>

    <form action="${sessionScope.contextPath}/NodesStatisticsServlet" method="get">
        Enter node's Id which statistics do you want see:<br>
        <input type="text" name="nodeId"/><br><br>
        <input type="submit" value="submit"/>
    </form>

    <c:if test="${requestScope.nodeStatistics != null}">

        <table border="2" width="80%">
            <tr>
                <th></th>
                <th></th>
            </tr>

            <tr>

                <td><c:out value="Node's start time"></c:out></td>
                <jsp:useBean id="startDate" class="java.util.Date"/>
                <jsp:setProperty name="startDate" property="time" value="${requestScope.nodeStatistics.startTime}"/>
                <td><fmt:formatDate value="${startDate}" pattern="dd/MM/yyyy hh:mm:ss"/></td>

            </tr>
            <tr>

                <td><c:out value="Node's stop time"></c:out></td>
                <c:if test="${requestScope.nodeStatistics.stopTime > 0}">
                    <jsp:useBean id="stopDate" class="java.util.Date"/>
                    <jsp:setProperty name="stopDate" property="time" value="${requestScope.nodeStatistics.stopTime}"/>
                    <td><fmt:formatDate value="${stopDate}" pattern="dd/MM/yyyy hh:mm:ss"/></td>
                </c:if>
                <c:if test="${requestScope.nodeStatistics.stopTime == 0}">
                    <td><c:out value="00:00:00"/></td>
                </c:if>
            </tr>
            <tr>

                <td><c:out value="Amount processed URLs"></c:out></td>
                <td><c:out value="${requestScope.nodeStatistics.amountProcessedUrl}"></c:out></td>

            </tr>
            <tr>

                <td><c:out value="Amount processes URLs"></c:out></td>
                <td><c:out value="${requestScope.nodeStatistics.amountProcessesUrl}"></c:out></td>

            </tr>
        </table>

    </c:if>

    <c:if test="${requestScope.exception != null }">

        <p style="color: red">${requestScope.exception}</p>

    </c:if>

    <br><br>
    <form action="${sessionScope.contextPath}/StatisticServlet" method="get">
        <button type="submit"> Return to the web crawler statistics</button>
    </form>

</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>

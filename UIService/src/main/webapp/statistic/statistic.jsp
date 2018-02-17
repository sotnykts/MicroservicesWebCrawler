<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Crawler statistics</title>
</head>
<body>
<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Statistics:</h4>
    <a style="background-color: lightgray" href="#">Web Crawler statistics</a>
    <a href="${sessionScope.contextPath}/statistic/nodestatistics.jsp">Node's statistics</a>
    <a href="${sessionScope.contextPath}/UserStatisticServlet">User's statistics</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

        <h2> Node's statistics: </h2>

        <table border="2" width="80%">
            <tr>
                <th>Node's Id</th>
                <th>Start time</th>
                <th>Stop time</th>
                <th>View statistics</th>
            </tr>

            <c:forEach var="node" items="${requestScope.nodeStatistics}">

                <tr>

                    <td><c:out value="${node.nodeId}"/></td>

                    <jsp:useBean id="startDate" class="java.util.Date"/>
                    <jsp:setProperty name="startDate" property="time" value="${node.startUnixTime}"/>
                    <td><fmt:formatDate value="${startDate}" pattern="dd/MM/yyyy hh:mm:ss"/></td>

                    <c:if test="${node.stopUnixTime > 0}">
                        <jsp:useBean id="stopDate" class="java.util.Date"/>
                        <jsp:setProperty name="stopDate" property="time" value="${node.stopUnixTime}"/>
                        <td><fmt:formatDate value="${stopDate}" pattern="dd/MM/yyyy hh:mm:ss"/></td>
                    </c:if>
                    <c:if test="${node.stopUnixTime == 0}">
                        <td><c:out value="00:00:00"/></td>
                    </c:if>

                    <td>
                        <form action="${sessionScope.contextPath}/NodesStatisticsServlet" method="get">
                            <button type="submit" name="nodeId" value="${node.nodeId}"> view statistics</button>
                        </form>
                    </td>

                </tr>

            </c:forEach>

            <tr style="background-color: gainsboro">
                <td><c:out value=" Total = ${requestScope.startedNode}"></c:out></td>
                <td><c:out value=" total started nodes = ${requestScope.startedNode}"></c:out></td>
                <td><c:out value=" total stopped nodes = ${requestScope.stoppedNode}"></c:out></td>
                <td></td>
            </tr>

        </table>

        <br>
        <br>

        <h2> URL's statistics: </h2>

        <table border="2" width="80%">
            <tr>
                <th></th>
                <th></th>
            </tr>

            <tr>

                <td><c:out value="Amount not processed URLs"></c:out></td>
                <td><c:out value="${requestScope.urlStatistic.notProcessedUrl}"></c:out></td>

            </tr>
            <tr>

                <td><c:out value="Amount processes URLs"></c:out></td>
                <td><c:out value="${requestScope.urlStatistic.processesUrl}"></c:out></td>

            </tr>
            <tr>

                <td><c:out value="Amount processed URLs"></c:out></td>
                <td><c:out value="${requestScope.urlStatistic.processedUrl}"></c:out></td>

            </tr>
            <tr style="background-color: gainsboro">

                <td><c:out value="Total URLs"></c:out></td>
                <td><c:out value="${requestScope.urlStatistic.amountUrl}"></c:out></td>

            </tr>

        </table>

</div>

<%@include file="/include/footer.jsp" %>

</body>
</html>
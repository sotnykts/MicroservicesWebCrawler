<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage node's</title>
</head>
<body>

<%@include file="/include/header.jsp" %>

<nav class="w3-sidenav w3-white w3-card-2" style="width:15%">
    <h4>Management:</h4>
    <a style="background-color: lightgray" href="#">Manege nodes</a>
    <a href="${sessionScope.contextPath}/useradministration/usersmanage.jsp">Manage users</a>
    <br>
    <a href="${sessionScope.contextPath}/LogoutServlet">Logout</a>
    <a href="${sessionScope.contextPath}/homepage/meetingpage.jsp">Home</a>

</nav>

<div class="w3-container" style="margin-left: 15%">

    <c:if test="${requestScope.stopNode == false}">

        <p style="color: red">  Some thing wrong with stopping your node. Please try again or report to the administration! </p>

        <c:remove var="stopNode" scope="session"/>

    </c:if>

    <h2> Node's information:</h2>

    <table border="2" width="80%">
        <tr>
            <th>Node's Id</th>
            <th>Start time</th>
            <th>Stop time</th>
            <th>Node's status</th>
            <th>Stop node</th>
        </tr>

        <c:forEach var="node" items="${requestScope.nodeStatus}">

            <tr>

                <td>
                    <c:out value="${node.nodeData.nodeId}"/>

                </td>

                <c:set var="nodeStatus" value="1" scope="page"/>

                <jsp:useBean id="startDate" class="java.util.Date"/>
                <jsp:setProperty name="startDate" property="time" value="${node.nodeData.startUnixTime}"/>
                <td><fmt:formatDate value="${startDate}" pattern="dd/MM/yyyy hh:mm:ss"/></td>

                <c:if test="${node.nodeData.stopUnixTime > 0}">
                    <jsp:useBean id="stopDate" class="java.util.Date"/>
                    <jsp:setProperty name="stopDate" property="time" value="${node.nodeData.stopUnixTime}"/>
                    <td><fmt:formatDate value="${stopDate}" pattern="dd/MM/yyyy hh:mm:ss"/></td>
                    <c:set var="nodeStatus" value="-1" scope="page"/>
                </c:if>
                <c:if test="${node.nodeData.stopUnixTime == 0}">
                    <td><c:out value="00:00:00"/></td>
                </c:if>

                <td>
                    <c:choose>
                        <c:when test="${node.nodeStatus == 1}">
                            <c:out value="node is working"/>
                        </c:when>
                        <c:when test="${node.nodeStatus == -1}">
                            <c:out value="node was stopped"/>
                        </c:when>
                        <c:when test="${node.nodeStatus == 0}">
                            <c:out value="node is frozen or was not correctly stopped"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="error"/>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:if test="${node.nodeStatus > -1}">
                        <form action="StopNodeServlet" method="post">
                            <button type="submit" name="nodeId" value="${node.nodeData.nodeId}"> stop node</button>
                        </form>
                    </c:if>
                </td>

            </tr>

        </c:forEach>

    </table>

    <br><br>
    <form action="${sessionScope.contextPath}/manage/createnewnode.jsp">
        <input type="submit" value="create new node"/>
    </form>
    <form action="${sessionScope.contextPath}/NodeInformationServlet">
        <input type="submit" value="renew page"/>
    </form>
</div>

<%@include file="/include/footer.jsp" %>

</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
    <style>
        .dropbtn {
            background-color: gray;
            color: cornflowerblue;
            padding: 16px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        .dropdown {
            position: relative;
            display: inline-block;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: #000000;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .dropdown-content a:hover {background-color: #f1f1f1}

        .dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown:hover .dropbtn {
            background-color: lightgray;
        }
    </style>
</head>
<body>
<div class="w3-container">

    <img src="crawler.gif" alt="Crawler" style="float:left; width:75px; height:50px; border:0; ">
    <ul class="w3-navbar w3-grey" style="font-size: x-large">

        <li><a href="${sessionScope.contextPath}/ManageServlet">Management</a></li>

        <li><a href="${sessionScope.contextPath}/StatisticServlet">Statistic</a></li>

        <c:choose>

            <c:when test="${sessionScope.securityToken != null}">
                <li style="float: right">
                    <a href="${sessionScope.contextPath}/user/profile.jsp">User's menu </a></li>
                </li>
                <li style="float: right">
                    <a href="<c:url value='/user/profile.jsp' />"><c:out
                            value='${sessionScope.securityToken.getUserData().getUserName()}'/></a>
                </li>
            </c:when>
            <c:otherwise>
                <li style="float: right">
                    <a href="<c:url value='/index.jsp'/>">User's menu </a></li>
                </li>
                <li style="float: right">
                    <a href="<c:url value='/index.jsp'/>">Guest</a>
                </li>
            </c:otherwise>

        </c:choose>

    </ul>

</div>
</body>
</html>

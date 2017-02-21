
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>



<html>
<head>
</head>
<body>
    <h3>Hi, you have logged in. </h3>

        <sql:setDataSource
            var="myDS"
            driver="com.mysql.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/User_Database"
            user="root" password="qazwsx992erd"
        />

        <sql:query var="list_users" dataSource="${myDS}">
            select * from User_data;
        </sql:query>

        <h3>List of Users</h3>
        <table border="1" cellpadding="5">
            <tr>
                <th>Name</th>
                <th>Username</th>
                <th colspan="2">Action</th>

            </tr>
            <c:forEach var="a_user" items="${list_users.rows}">
                <tr>
                    <td align="center"><c:out value="${a_user.firstname}" /></td>
                    <td align="center"><c:out value="${a_user.username}" /></td>
                    <td>
                        <a href="/edituser?id=${a_user.id}">
                           <input type="submit" value="Edit"/>
                        </a>
                    </td>
                    <td>
                        <c:set var="curuser" value="${username}"/>
                        <c:if test="${a_user.username != curuser}">
                            <a href="/deluser?id=${a_user.id}">
                               <input type="submit" value="Del"/>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <br><br>
        <form action="/adduser" method="get">
            <input type="submit" value="Add User">
        </form>

        <form action="/logout" method="get">
            <input type="submit" value="Logout">
        </form>
</body>
</html>

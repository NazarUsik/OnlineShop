<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 00:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<body>


<var class="navbar" style="float: left; position: fixed;">
    <a href="home">Home</a>
    <c:if test="${loginedUser == null}">
        |||
        <a href="login">Login</a>
        |||
        <a href="registration">Registration</a>
    </c:if>
    <c:if test="${loginedUser != null}">
        |||
        <c:if test="${setting != null}">
            <a href="setting">Setting</a>
        </c:if>
        <c:if test="${setting == null}">
            <form style="display: inline" action="${pageContext.request.contextPath}/user" method="post">
                <a href="javascript:" onclick="parentNode.submit();">Account</a>
            </form>
        </c:if>
        |||
        <a href="<c:url value="/logout"/>">Logout</a>
    </c:if>
</var>


</body>
</html>

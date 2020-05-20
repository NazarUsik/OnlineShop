<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 07:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Setting</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div align="center">
    <h2>Edit Customer</h2>
    <form:form action="saveSetting" method="post" modelAttribute="user">
        <table border="0" cellpadding="3">
            <tr>
                <td>First Name:</td>
                <td><form:input path="firstName" required="true"/></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><form:input path="lastName" required="true"/></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td>${user.email}
                    <form:hidden path="id"/>
                    <form:hidden path="email"/>
                    <form:hidden path="password"/>
                    <form:hidden path="roleId"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>

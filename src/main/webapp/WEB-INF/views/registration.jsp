<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 06:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Registration</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div align="center">
    <h2>Registration</h2>
    <p style="color: red">${param.error}</p>
    <form:form action="registration " method="post" modelAttribute="regUser">
        <table border="0" cellpadding="5">
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
                <td>
                    <form:input type="email" path="email" required="true"/>
                    <form:hidden path="roleId" value="2"/>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <form:password cssClass="password-field" path="password" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <input type="checkbox" value="Y" name="remember">
                        Remember me
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Registration"></td>
            </tr>
        </table>
    </form:form>
</div>


</body>
</html>

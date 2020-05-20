<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 20.05.2020
  Time: 05:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Reset Password</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div align="center">
    <h2>Edit Product</h2>
    <form:form action="reset-password" method="post" modelAttribute="user">
        <table border="0" cellpadding="4">
            <tr>
                <td>Email:</td>
                <td>${user.email}
                    <form:hidden path="email"/>
                </td>
            </tr>
            <tr>
                <td>New Password:</td>
                <td><form:input cssClass="password-field" path="password" required="true"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Submit"></td>
            </tr>
        </table>
    </form:form>
</div>

</body>
</html>

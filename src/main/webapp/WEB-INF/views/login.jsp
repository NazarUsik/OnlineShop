<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 04:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<div align="center">
    <h2>Login</h2>
    <p style="color: red">${param.error}${error}</p>

    <c:if test="${param.message != null}">
        <script type="text/javascript">
            alert(${param.message});
        </script>
    </c:if>

    <form name="f" action="/singin" method="post">
        <table border="0" cellpadding="4">
            <tr>
                <td>Email:</td>
                <td><input type="text" name='username' value="" required="true"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input type="password" name='password' required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <input type="checkbox" value="Y" name="remember">
                        Remember me
                        <br>
                        <a href="/forgot-password">Forgot Password?</a>
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Login"></td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>

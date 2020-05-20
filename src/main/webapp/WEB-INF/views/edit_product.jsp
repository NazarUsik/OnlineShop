<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Product</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div align="center">
    <h2>Edit Product</h2>
    <form:form action="saveProduct" method="post" modelAttribute="product">
        <table border="0" cellpadding="7">
            <tr>
                <td>ID:</td>
                <td>${product.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" required="true"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><form:input type="number" step="0.1" path="price" required="true"/></td>
            </tr>
            <tr>
                <td>Amount:</td>
                <td><form:input type="number" path="amount" required="true"/></td>
            </tr>
            <tr>
                <td>Number of Sold:</td>
                <td><form:input type="number" path="soldNumber" required="true"/></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
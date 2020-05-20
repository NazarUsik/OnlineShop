<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Order</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div align="center">
    <h2>Edit Order</h2>
    <form:form action="saveOrder" method="post" modelAttribute="order">
        <table border="0" cellpadding="7">
            <tr>
                <td>ID:</td>
                <td>${order.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Date:</td>
                <td>${order.date}
                    <form:hidden path="date"/>
                    <form:hidden path="total"/>
                    <form:hidden path="userId"/>
                    <form:hidden path="productId"/>
                    <form:hidden path="price"/>
                </td>
            </tr>
            <tr>
                <td>Price:</td>
                <td>${order.price}</td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td><form:input type="number" id="quantity_field" path="quantity"
                                max="${product.amount + order.quantity}" min="0"
                                required="true"/></td>
            </tr>
            <tr>
                <td>Total price:</td>
                <td><p id="total_field">${order.total}</p></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
<script type="text/javascript">
    document.getElementById("quantity_field").addEventListener('change', (event) => {
        document.getElementById("total_field").textContent = ' ' + ${order.price} * event.target.value;
    }, false);
</script>
</body>
</html>
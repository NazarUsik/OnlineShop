<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 00:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>User</title>
</head>
<body>
<jsp:include page="_header.jsp"/>


<div align="center">
    <c:if test="${param.status == 'successful'}">
        <script type="text/javascript">
            alert("Email has been send!");
        </script>
    </c:if>
    <c:if test="${param.message != null}">
        <script type="text/javascript">
            alert(${param.message});
        </script>
    </c:if>
    <h2>Hello ${loginedUser.firstName} ${loginedUser.lastName}</h2>
    <br>
    <h4>View all orders</h4>
    <c:if test="${orderList != null && fn:length(orderList) > 0 }">
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Date</th>
                <th>Price</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Total price</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.date}</td>
                    <td>${order.price}</td>
                    <td>${order.productId}</td>
                    <td>${order.quantity}</td>
                    <td>${order.total}</td>
                    <td>
                        <form style="display: inline"
                              action="${pageContext.request.contextPath}/editOrder"
                              method="post">
                            <a href="javascript:" onclick="parentNode.submit();">Edit</a>
                            <input type="hidden" name="id" value="${order.id}">
                        </form>
                        |||
                        <form style="display: inline" action="${pageContext.request.contextPath}/sentReceipt"
                              method="post">
                            <a href="javascript:" onclick="parentNode.submit();">Get receipt</a>
                            <input type="hidden" name="orderId" value="${order.id}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${orderList == null || fn:length(orderList) <= 0 }">
        <h1 style="color: red;">Orders not found</h1>
    </c:if>
</div>

</body>
</html>

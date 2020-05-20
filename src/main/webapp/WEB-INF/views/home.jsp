<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 17.05.2020
  Time: 00:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div align="center">
    <h2>View all Product</h2>
    <form method="get" action="search">
        <input type="text" name="keyword"/>
        <input type="submit" value="Search"/>
    </form>
    <c:if test="${loginedUser.roleId == 1}">
        <h3>
            <form style="display: inline" action="${pageContext.request.contextPath}/newProduct" method="post">
                <a href="javascript:" onclick="parentNode.submit();">New Product</a>
            </form>
        </h3>
    </c:if>
    <br>
    <c:if test="${productList != null && fn:length(productList) > 0 }">
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Number of Sold</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${productList}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.name}</td>
                    <td>${order.price}</td>
                    <td>${order.amount}</td>
                    <td>${order.soldNumber}</td>
                    <td>
                        <c:if test="${loginedUser != null}">
                            <c:choose>
                                <c:when test="${loginedUser.roleId == 1}">
                                    <form style="display: inline"
                                          action="${pageContext.request.contextPath}/editProduct"
                                          method="post">
                                        <a href="javascript:" onclick="parentNode.submit();">Edit</a>
                                        <input type="hidden" name="id" value="${order.id}">
                                    </form>
                                    |||
                                    <form style="display: inline" action="${pageContext.request.contextPath}/delProduct"
                                          method="post">
                                        <a href="javascript:" onclick="parentNode.submit();">Delete</a>
                                        <input type="hidden" name="id" value="${order.id}">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${order.amount > 0}">
                                        <a href="addToCart?productId=${order.id}">Add to Cart</a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${productList == null || fn:length(productList) <= 0 }">
        <h1 style="color: red;">Product not found</h1>
    </c:if>
</div>
</body>
</html>

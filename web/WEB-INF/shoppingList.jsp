<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, <c:out value="${username}"/> <a href="<c:url value='ShoppingList?action=logout'/>">Logout</a></p>

        <h1>List</h1>
        <form action="ShoppingList" method="POST">
            <label for="addItem">Add item:</label>
            <input type="text" id="addItem" name="addItem">
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
       
        <c:if test="${not empty shoppingList}">
            <form action="ShoppingList" method="POST">
                <c:forEach var="item" varStatus="loop" items="${shoppingList}">
                    <input type="radio" name="shoppingList" id="item_<c:out value='${loop.index}'/>" value="<c:out value='${loop.index}'/>">
                    <label for="item_<c:out value='${loop.index}'/>"><c:out value='${item}'/></label></br>
                </c:forEach>

                <input type="submit" value="Delete">
                <input type="hidden" name="action" value="delete">
            </form>
        </c:if>
    </body>
</html>

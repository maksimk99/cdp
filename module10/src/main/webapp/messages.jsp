<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="https://code.jquery.com/jquery-1.10.2.js"
            type="text/javascript"></script>
    <script src="js/app-ajax.js" type="text/javascript"></script>
</head>
<body>
<table style="border: none">
    <tr style="align-content: end">
        <th>
            <form id="formAdd">
                <input type="text" name="message"><br>
            </form>
            <button onclick="formAdd()">Add</button>
        </th>
        <th style="margin-left: 20px">
            <form id="formUpdate">
                <input type="number" name="messageId"><br>
                <input type="text" name="message"><br>
            </form>
            <button onclick="formUpdate()">Update</button>
        </th>
        <th style="margin-left: 20px">
            <form id="formDelete">
                <input type="number" name="messageId"><br>
            </form>
            <button onclick="formDelete()">Delete</button>
        </th>
    </tr>
</table>
<h4><span>Views amount: </span><span id="viewsAmount">${viewsAmount}</span></h4>
<h4>Time: <fmt:formatDate type="time" value="${dateNow}"/></h4>
<h1>
    Messages:
</h1>
<table id="listMessages" style="border: none">
    <c:forEach var="message" items="${messages}" varStatus="loop">
        <tr style="text-align: left">
            <th>${loop.index}</th>
            <th style="padding-left: 12px">${message}</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>
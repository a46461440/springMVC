<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request['contextPath']}/interceptor/login">
    <table>
        <tr>
            <td>姓名:<input type="text" id="name" name="name"> </td>
            <td>密码:<input type="password" id="password" name="password"> </td>
            <td><input type="submit" value="<spring:message code="submit"/> "> </td>
        </tr>
    </table>
</form>
</body>
</html>

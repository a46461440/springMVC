<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="title"/></title>
</head>
<body>
<table>
    <h1><spring:message code="welcome"/> <spring:message code="username"/> </h1>
    <form action="${pageContext.request['contextPath']}/i18N/login" method="post">
        <tr>
            <td><spring:message code="loginname"/> <input type="text" name="name"></td>
            <td><spring:message code="password"/> <input type="password" name="password"></td>
            <td colspan="2"><input type="submit" value="<spring:message code="submit"/>"> </td>
        </tr>
    </form>
</table>
</body>
</html>

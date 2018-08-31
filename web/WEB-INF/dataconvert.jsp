<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>dataconvert</title>
</head>
<body>
<form:form commandName="user" action="${pageContext.request['contextPath']}/dataConvert/testValidate" method="post">
    <table>
        <tr>
            <td><form:input path="name"/> <form:errors path="name" cssClass="error" /></td>
            <td><form:password path="password"/> <form:errors path="password" cssClass="error" /></td>
            <td><form:input path="date" type="date"/> <form:errors path="date" cssClass="error" /></td>
            <td><input type="submit" value="<spring:message code="submit"/>"/> </td>
        </tr>
    </table>
</form:form>
</body>
</html>

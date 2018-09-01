<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request['contextPath']}/multipart/upFile" enctype="multipart/form-data" method="post">
    <table>
        <tr>
            <td><input type="text" name="name"> </td>
        </tr>
        <tr>
            <td><input type="file" name="file0"> </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<spring:message code="submit"/> ">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

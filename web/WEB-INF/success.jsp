<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
<h1>success:${object}:${string}-${name}</h1>
<a href="/requestMappingTest/header/encoding">header</a>
<a href="/requestMappingTest/pathVariable/zxc">pathVariable:zxc</a>
<a href="/requestMappingTest/cookie">cookie</a>
<p>ModelAttribute设置在有返回类型的方法上 model的值为:${ModelAttributeSetKey}</p>
<p>ModelAttribute设置在属性上 model的值为:${requestMappingPojo.name}</p>
</body>
</html>

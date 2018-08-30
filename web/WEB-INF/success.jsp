<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="taglibs.jsp"%>--%>
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
<button onclick="normalJson()">点击测试json</button>
<p>pojo对象放入了session:<span id="pojo_display"></span></p>
<script type="text/javascript" src="/js/jquery-1.3.2.js"></script>
<script>
    let normalJson = function () {
        $.ajax({
            url : "${pageContext.request['contextPath']}/jsonTest/normal",
            contentType: 'application/json;charset=UTF-8',
            type : 'post',
            async : true,
            data : JSON.stringify({name : 'zxc', age : 23}),
//            data : {name : 'zxc', age : 23},
            dataType : 'text',
            success : function(data) {
                $("#pojo_display").text(data);
            }
        })
    }
</script>
</body>
</html>

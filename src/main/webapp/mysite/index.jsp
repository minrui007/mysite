<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/19
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MySite</title>
    <script type="text/javascript" src="scripts/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="scripts/base_url.js"></script>
</head>
<body>
    <form action="/user/findById" method="post">
        <table>
            <tr>
                <td>用户登录</td>
            </tr>
            <tr>
                <td>用户名：</td>
                <td><input type="text" id="id" name="id"></td>
            </tr>
            <tr>
                <td>密码：</td>
                <td><input type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <td><input type="submit" id="submit" value="登陆"></td>
                <td><input type="button" id="register" value="注册"></td>
            </tr>
        </table>
    </form>
</body>
<script>
    $("#register").click(function(){
        alert(123);
    })
</script>
</html>

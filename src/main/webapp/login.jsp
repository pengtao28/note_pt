<%--
  Created by IntelliJ IDEA.
  User: PT
  Date: 2021/5/6
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录页</title>
    <link rel="stylesheet" href="static/css/login.css"/>
    <script type="text/javascript" src="static/js/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="static/js/login.js"></script>
    <script type="text/javascript" src="static/js/util.js"></script>
</head>
<body>
<div class="background">
    <div class="pt_imgs">
        <img src="static/img/pt7.jpg" class="img" style="display: block;"/>
        <img src="static/img/pt8.jpg" class="img"/>
        <img src="static/img/pt9.jpg" class="img"/>
        <img src="static/img/pt10.jpg" class="img"/>
        <img src="static/img/pt11.jpg" class="img"/>
        <img src="static/img/pt12.jpg" class="img"/>
        <img src="static/img/pt13.jpg" class="img"/>
        <img src="static/img/pt21.jpg" class="img"/>
        <img src="static/img/pt22.jpg" class="img"/>
        <img src="static/img/pt23.jpg" class="img"/>
        <img src="static/img/pt24.jpg" class="img"/>
        <img src="static/img/pt25.jpg" class="img"/>
        <img src="static/img/pt26.jpg" class="img"/>
        <img src="static/img/pt27.jpg" class="img"/>
        <img src="static/img/pt28.jpg" class="img"/>
        <img src="static/img/pt29.jpg" class="img"/>
        <img src="static/img/pt30.jpg" class="img"/>
        <img src="static/img/pt31.jpg" class="img"/>
        <img src="static/img/pt32.jpg" class="img"/>
        <img src="static/img/pt33.jpg" class="img"/>
        <img src="static/img/pt34.jpg" class="img"/>
        <img src="static/img/pt35.jpg" class="img"/>
        <img src="static/img/pt36.png" class="img"/>
        <img src="static/img/pt37.jpg" class="img"/>
        <img src="static/img/pt38.jpg" class="img"/>
        <img src="static/img/pt39.jpg" class="img"/>
        <img src="static/img/pt40.jpg" class="img"/>
    </div>
    <div class="pt_login">
        <div class="pt_login_fram">
            <form action="user" method="post" class="loginForm">
                <input type="hidden" name="actionName" value="login">
                <p>用户登录</p>
                <input type="text" class="pt_name" placeholder="用户名" name="userName"
                       value="${resultInfo.result.uname}"/><br/>
                <input type="password" class="pt_password" placeholder="密码" name="userPwd"
                       value="${resultInfo.result.upwd}"/><br/>
                <input type="checkbox" class="pt_checkbox" name="rem" value="1"/>&nbsp;记住密码
                <span style="color: red;font-size: 12px;" class="msg">${resultInfo.msg}</span>
                <input type="button" value="登录" class="pt_button" onclick="checkLogin()" name="btn"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>


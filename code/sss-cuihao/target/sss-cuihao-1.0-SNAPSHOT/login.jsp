<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script>

    </script>

    <style>
        div{
            padding:10px 10px 0 10px;
        }
    </style>
</head>
<body>
    <div>
        <font color="red">${info}</font>
        <form method="post" action="/login-controller/login">
            <input type="text" name="userName"/>
            <input type="password" name="password"/>
            <input type="submit" value="登录"/>
        </form>
    </div>
</body>
</html>

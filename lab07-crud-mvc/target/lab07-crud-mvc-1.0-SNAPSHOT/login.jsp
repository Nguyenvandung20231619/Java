<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
</head>
<body>
    <h2>Đăng nhập Hệ thống</h2>
    <p style="color:red;">${error}</p>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <p>Username: <input name="username" required></p>
        <p>Password: <input type="password" name="password" required></p>
        <button type="submit">Đăng nhập</button>
    </form>
</body>
</html>
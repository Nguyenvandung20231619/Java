<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập - Lab 10 Secured App</title>
</head>
<body>
    <h2>Đăng nhập Hệ thống</h2>

    <c:if test="${not empty error}">
        <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/auth">
        <p>Email: <input type="email" name="email" required placeholder="admin@eaut.edu.vn"></p>
        <p>Mật khẩu: <input type="password" name="password" required></p>
        <button type="submit">Đăng nhập</button>
    </form>

    <br/><hr/>
    <p><b>Tài khoản thử nghiệm:</b></p>
    <ul>
        <li><b>ADMIN:</b> admin@eaut.edu.vn / 123456</li>
        <li><b>STAFF:</b> staff@eaut.edu.vn / 123456</li>
        <li><b>USER:</b> user@eaut.edu.vn / 123456</li>
    </ul>
</body>
</html>
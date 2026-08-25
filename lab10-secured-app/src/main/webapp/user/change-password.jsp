<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head><title>Đổi Mật Khẩu</title></head>
<body>
    <h2>Đổi Mật Khẩu Cá Nhân</h2>
    <c:if test="${not empty error}"><p style="color:red">${error}</p></c:if>
    <c:if test="${not empty message}"><p style="color:green">${message}</p></c:if>

    <form method="post" action="${pageContext.request.contextPath}/auth">
        <input type="hidden" name="action" value="change-password"/>
        <p>Mật khẩu cũ: <input type="password" name="oldPassword" required></p>
        <p>Mật khẩu mới: <input type="password" name="newPassword" required></p>
        <p>Xác nhận mật khẩu mới: <input type="password" name="confirmPassword" required></p>
        <button type="submit">Cập nhật Mật khẩu</button>
    </form>
    <p><a href="${pageContext.request.contextPath}/dashboard.jsp">Quay lại Dashboard</a></p>
</body>
</html>
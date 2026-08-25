<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bảng điều khiển - Dashboard</title>
</head>
<body>
    <h2>Trang Chủ Điều Khiển</h2>
    <p>Xin chào: <b>${currentUser.fullName}</b> | Vai trò: <b><mark>${currentUser.role}</mark></b></p>
    <p><a href="${pageContext.request.contextPath}/auth?action=logout">Đăng xuất</a></p>
    <hr/>

    <h3>Menu Chức Năng Hệ Thống</h3>
    <ul>
        <!-- BÀI 10: Menu Phân Quyền Theo Role -->
        <c:if test="${currentUser.role == 'ADMIN'}">
            <li><a href="${pageContext.request.contextPath}/staff/sinh-vien">Quản lý Sinh viên</a></li>
        </c:if>

        <c:if test="${currentUser.role == 'STAFF'}">
            <li><a href="${pageContext.request.contextPath}/staff/sinh-vien">Quản lý Sinh viên</a></li>
        </c:if>

        <c:if test="${currentUser.role == 'USER'}">
            <li><a href="${pageContext.request.contextPath}/user/profile.jsp">Xem Hồ sơ cá nhân</a></li>
            <li><a href="${pageContext.request.contextPath}/user/change-password.jsp">Đổi Mật khẩu</a></li>
        </c:if>
    </ul>
</body>
</html>
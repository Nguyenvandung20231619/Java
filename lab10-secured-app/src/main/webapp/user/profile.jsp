<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Hồ sơ cá nhân</title></head>
<body>
    <h2>Thông Tin Hồ Sơ Cá Nhân</h2>
    <p><b>Họ và Tên:</b> ${currentUser.fullName}</p>
    <p><b>Email:</b> ${currentUser.email}</p>
    <p><b>Vai trò:</b> ${currentUser.role}</p>
    <p><a href="${pageContext.request.contextPath}/dashboard.jsp">Quay lại Dashboard</a></p>
</body>
</html>
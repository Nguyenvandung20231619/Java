<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>${not empty student ? "Cập nhật sinh viên" : "Thêm sinh viên"}</title>
</head>
<body>
    <h2>${not empty student ? "Cập nhật thông tin sinh viên" : "Thêm sinh viên mới"}</h2>

    <form action="${pageContext.request.contextPath}/students" method="post">
        <!-- Nếu đang sửa, truyền cờ action=update -->
        <c:if test="${not empty student}">
            <input type="hidden" name="action" value="update">
        </c:if>

        <label>Mã sinh viên:</label><br>
        <!-- Khi Sửa: Khóa ô ID (readonly). Khi Thêm: Cho phép nhập -->
        <input type="text" name="id" value="${student.id}" ${not empty student ? "readonly" : "required"}><br><br>

        <label>Họ tên:</label><br>
        <input type="text" name="name" value="${student.name}" required><br><br>

        <label>Lớp:</label><br>
        <input type="text" name="className" value="${student.className}" required><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" value="${student.email}" required><br><br>

        <button type="submit">${not empty student ? "Cập nhật" : "Lưu sinh viên"}</button>
        <a href="${pageContext.request.contextPath}/students">Hủy</a>
    </form>
</body>
</html>
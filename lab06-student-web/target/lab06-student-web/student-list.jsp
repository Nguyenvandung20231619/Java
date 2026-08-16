<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Danh sách sinh viên</title>
</head>
<body>
    <h2>Danh sách sinh viên</h2>

    <p>
        <a href="${pageContext.request.contextPath}/welcome.jsp">Trang quản trị</a> | 
        <a href="${pageContext.request.contextPath}/student-form.jsp">Thêm sinh viên mới</a>
    </p>

    <!-- Bài 6: Form tìm kiếm theo tên -->
    <form action="${pageContext.request.contextPath}/students" method="get">
        <input type="text" name="keyword" value="${keyword}" placeholder="Nhập tên sinh viên...">
        <button type="submit">Tìm kiếm</button>
        <c:if test="${not empty keyword}">
            <a href="${pageContext.request.contextPath}/students">Xóa lọc</a>
        </c:if>
    </form>

    <br>

    <!-- Kiểm tra và hiển thị bảng -->
    <c:choose>
        <c:when test="${empty students}">
            <p style="color: red;">Không tìm thấy sinh viên nào!</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="8" cellspacing="0">
                <thead>
                    <tr>
                        <th>Mã SV</th>
                        <th>Họ tên</th>
                        <th>Lớp</th>
                        <th>Email</th>
                        <th>Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="sv" items="${students}">
                        <tr>
                            <td>${sv.id}</td>
                            <td>${sv.name}</td>
                            <td>${sv.className}</td>
                            <td>${sv.email}</td>
                            <td>
                                <!-- Bài 8: Nút Sửa -->
                                <a href="${pageContext.request.contextPath}/students?action=edit&id=${sv.id}">Sửa</a> | 
                                <!-- Bài 7: Nút Xóa có xác nhận -->
                                <a href="${pageContext.request.contextPath}/students?action=delete&id=${sv.id}" 
                                   onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên ${sv.name} (Mã: ${sv.id}) không?')">Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>
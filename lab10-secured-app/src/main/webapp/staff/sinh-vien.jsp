<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý sinh viên</title>
</head>
<body>
    <h2>Quản lý sinh viên</h2>
    <p>Xin chào <b>${currentUser.fullName}</b> (${currentUser.role})</p>
    <p><a href="${pageContext.request.contextPath}/dashboard.jsp">Quay lại Dashboard</a> |
       <a href="${pageContext.request.contextPath}/auth?action=logout">Đăng xuất</a></p>

    <c:if test="${not empty error}">
        <p style="color: red; font-weight: bold;">${error}</p>
    </c:if>

    <h3>${empty sinhVienEdit ? 'Thêm sinh viên' : 'Cập nhật sinh viên'}</h3>
    <form method="post" action="${pageContext.request.contextPath}/staff/sinh-vien">
        <c:if test="${not empty sinhVienEdit}">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${sinhVienEdit.id}">
        </c:if>
        <label>Mã sinh viên: <input name="maSinhVien" maxlength="20" value="${sinhVienEdit.maSinhVien}" required></label><br>
        <label>Họ tên: <input name="hoTen" maxlength="100" value="${sinhVienEdit.hoTen}" required></label><br>
        <label>Email: <input type="email" name="email" value="${sinhVienEdit.email}"></label><br>
        <label>Lớp: <input name="lop" value="${sinhVienEdit.lop}"></label><br>
        <button type="submit">${empty sinhVienEdit ? 'Thêm sinh viên' : 'Lưu cập nhật'}</button>
        <c:if test="${not empty sinhVienEdit}">
            <a href="${pageContext.request.contextPath}/staff/sinh-vien">Hủy</a>
        </c:if>
    </form>

    <h3>Danh sách sinh viên</h3>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>Mã sinh viên</th><th>Họ tên</th><th>Email</th><th>Lớp</th><th>Thao tác</th>
        </tr>
        <c:forEach var="sinhVien" items="${sinhViens}">
            <tr>
                <td>${sinhVien.maSinhVien}</td>
                <td>${sinhVien.hoTen}</td>
                <td>${sinhVien.email}</td>
                <td>${sinhVien.lop}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/staff/sinh-vien?action=edit&id=${sinhVien.id}">Sửa</a>
                    <form method="post" action="${pageContext.request.contextPath}/staff/sinh-vien">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${sinhVien.id}">
                        <button type="submit">Xóa</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

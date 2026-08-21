<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sinh viên - Lab 9</title>
</head>
<body>
    <h2>Quản lý Sinh Viên (JPA / Hibernate)</h2>

    <p><a href="${pageContext.request.contextPath}/views/sinhvien/form.jsp">➕ Thêm sinh viên mới</a></p>

    <form method="get" action="${pageContext.request.contextPath}/sinh-vien">
        <label>Tìm kiếm (Tên/Lớp): </label>
        <input type="text" name="keyword" value="${param.keyword}"/>
        <button type="submit">Tìm kiếm</button>
        <a href="${pageContext.request.contextPath}/sinh-vien">Làm mới</a>
    </form>
    <br/>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Mã SV</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Lớp</th>
                <th>Ngày sinh</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sv" items="${dsSinhVien}">
                <tr>
                    <td>${sv.id}</td>
                    <td>${sv.maSinhVien}</td>
                    <td>${sv.hoTen}</td>
                    <td>${sv.email}</td>
                    <td>${sv.lop}</td>
                    <td>${sv.ngaySinh}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/sinh-vien?action=edit&id=${sv.id}">Sửa</a> | 
                        <a href="${pageContext.request.contextPath}/sinh-vien?action=delete&id=${sv.id}" 
                           onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên này?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty dsSinhVien}">
                <tr>
                    <td colspan="7" style="text-align: center;">Không có dữ liệu sinh viên nào.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</body>
</html>
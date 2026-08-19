<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sinh viên</title>
</head>
<body>
    <h2>Danh sách Sinh viên</h2>
    <form method="get" action="${pageContext.request.contextPath}/sinh-vien">
        <input name="keyword" placeholder="Tìm tên hoặc lớp" value="${param.keyword}">
        <button type="submit">Tìm kiếm</button>
    </form>
    <br>
    <a href="${pageContext.request.contextPath}/sinh-vien?action=new">Thêm sinh viên mới</a> | 
    <a href="${pageContext.request.contextPath}/">Trang chủ</a>
    <br><br>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Mã SV</th>
            <th>Họ và Tên</th>
            <th>Email</th>
            <th>Lớp</th>
            <th>Thao tác</th>
        </tr>
        <c:forEach var="sv" items="${dsSinhVien}">
            <tr>
                <td>${sv.id}</td>
                <td>${sv.maSinhVien}</td>
                <td><a href="${pageContext.request.contextPath}/sinh-vien?action=detail&id=${sv.id}">${sv.hoTen}</a></td>
                <td>${sv.email}</td>
                <td>${sv.lop}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/sinh-vien?action=edit&id=${sv.id}">Sửa</a> |
                    <a href="${pageContext.request.contextPath}/sinh-vien?action=delete&id=${sv.id}" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
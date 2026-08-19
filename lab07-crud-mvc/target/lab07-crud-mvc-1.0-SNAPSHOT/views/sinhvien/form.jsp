<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thông tin sinh viên</title>
</head>
<body>
    <h2>${sv == null ? "Thêm mới sinh viên" : "Cập nhật sinh viên"}</h2>
    <form method="post" action="${pageContext.request.contextPath}/sinh-vien">
        <input type="hidden" name="id" value="${sv.id}">
        <p>Mã Sinh viên: <input name="maSinhVien" value="${sv.maSinhVien}" required></p>
        <p>Họ tên: <input name="hoTen" value="${sv.hoTen}" required></p>
        <p>Email: <input type="email" name="email" value="${sv.email}"></p>
        <p>Lớp: <input name="lop" value="${sv.lop}"></p>
        <button type="submit">Lưu dữ liệu</button>
        <a href="${pageContext.request.contextPath}/sinh-vien">Hủy bỏ</a>
    </form>
</body>
</html>
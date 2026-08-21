<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${sinhVien != null ? "Cập nhật sinh viên" : "Thêm sinh viên"}</title>
</head>
<body>
    <h2>${sinhVien != null ? "Cập nhật thông tin sinh viên" : "Thêm mới sinh viên"}</h2>

    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/sinh-vien">
        <input type="hidden" name="id" value="${sinhVien.id}"/>
        <p>
            <label>Mã Sinh Viên:</label><br/>
            <input type="text" name="maSinhVien" value="${sinhVien.maSinhVien}" required/>
        </p>
        <p>
            <label>Họ và Tên:</label><br/>
            <input type="text" name="hoTen" value="${sinhVien.hoTen}" required/>
        </p>
        <p>
            <label>Email:</label><br/>
            <input type="email" name="email" value="${sinhVien.email}"/>
        </p>
        <p>
            <label>Lớp Học:</label><br/>
            <input type="text" name="lop" value="${sinhVien.lop}"/>
        </p>
        <p>
            <label>Ngày Sinh:</label><br/>
            <input type="date" name="ngaySinh" value="${sinhVien.ngaySinh}"/>
        </p>
        <button type="submit">Lưu lại</button>
        <a href="${pageContext.request.contextPath}/sinh-vien">Hủy bỏ</a>
    </form>
</body>
</html>
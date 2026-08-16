<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="vn.edu.eaut.lab6.store.StudentStore" %>
<%@ page import="vn.edu.eaut.lab6.model.Student" %>
<%@ page import="java.util.*, java.util.stream.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    List<Student> list = StudentStore.findAll();
    Map<String, Long> classStats = list.stream()
            .collect(Collectors.groupingBy(Student::getClassName, Collectors.counting()));
    request.setAttribute("totalStudents", list.size());
    request.setAttribute("classStats", classStats);
%>
<html>
<head>
    <title>Trang quản trị / Dashboard</title>
</head>
<body>
    <h2>Xin chào, ${sessionScope.username}!</h2>
    <p>Thời gian đăng nhập: <strong>${sessionScope.loginTime}</strong></p>
    <hr>
    
    <h3>Tổng quan hệ thống (Dashboard):</h3>
    <ul>
        <li>Tổng số sinh viên: <strong>${totalStudents}</strong></li>
    </ul>

    <h4>Phân bố sinh viên theo lớp:</h4>
    <ul>
        <c:forEach var="entry" items="${classStats}">
            <li>Lớp <strong>${entry.key}</strong>: ${entry.value} sinh viên</li>
        </c:forEach>
    </ul>
    <hr>

    <h3>Chức năng chính:</h3>
    <ul>
        <li><a href="${pageContext.request.contextPath}/students">Quản lý danh sách sinh viên</a></li>
        <li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
    </ul>
</body>
</html>
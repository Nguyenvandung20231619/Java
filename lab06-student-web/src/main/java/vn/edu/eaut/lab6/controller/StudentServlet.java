package vn.edu.eaut.lab6.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab6.model.Student;
import vn.edu.eaut.lab6.store.StudentStore;

import java.io.IOException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                // Bài 8: Lấy dữ liệu cũ và mở form student-form.jsp ở chế độ chỉnh sửa
                String editId = request.getParameter("id");
                Student editStudent = StudentStore.findById(editId);
                request.setAttribute("student", editStudent);
                request.getRequestDispatcher("/student-form.jsp").forward(request, response);
                break;

            case "delete":
                // Bài 7: Xóa sinh viên và quay về trang danh sách
                String deleteId = request.getParameter("id");
                StudentStore.delete(deleteId);
                response.sendRedirect(request.getContextPath() + "/students");
                break;

            case "list":
            default:
                // Bài 3 & Bài 6: Hiển thị danh sách hoặc kết quả tìm kiếm
                String keyword = request.getParameter("keyword");
                request.setAttribute("students", StudentStore.searchByName(keyword));
                request.setAttribute("keyword", keyword);
                request.getRequestDispatcher("/student-list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        String email = request.getParameter("email");

        Student student = new Student(id, name, className, email);

        if ("update".equals(action)) {
            // Bài 8: Xử lý cập nhật thông tin
            StudentStore.update(student);
        } else {
            // Bài 2 & 3: Xử lý thêm sinh viên mới
            StudentStore.add(student);
        }

        // Chuyển hướng về trang danh sách sau khi lưu/cập nhật thành công (PRG Pattern)
        response.sendRedirect(request.getContextPath() + "/students");
    }
}
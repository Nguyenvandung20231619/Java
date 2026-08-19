package vn.edu.eaut.lab7.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Nếu chuyển hướng sang trang login, sẽ forward tới trang login.jsp
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Thiết lập mã hóa tiếng Việt cho request/response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form login.jsp
        String u = req.getParameter("username");
        String p = req.getParameter("password");

        // Kiểm tra tài khoản và mật khẩu đơn giản (admin / 123456)
        if ("admin".equals(u) && "123456".equals(p)) {
            // Đăng nhập thành công: lưu thông tin người dùng vào Session
            HttpSession session = req.getSession();
            session.setAttribute("username", u);

            // Chuyển hướng sang trang quản lý sinh viên
            resp.sendRedirect(req.getContextPath() + "/sinh-vien");
        } else {
            // Đăng nhập thất bại: gửi thông báo lỗi về lại trang login.jsp
            req.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không chính xác!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
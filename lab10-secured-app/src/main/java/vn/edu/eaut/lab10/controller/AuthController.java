package vn.edu.eaut.lab10.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.edu.eaut.lab10.model.User;
import vn.edu.eaut.lab10.service.AuthService;

import java.io.IOException;

@WebServlet("/auth")
public class AuthController extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("change-password".equals(action)) {
            handleChangePassword(request, response);
            return;
        }

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = authService.login(email, password);

        if (user == null) {
            request.setAttribute("error", "Email hoặc mật khẩu không chính xác hoặc tài khoản đã bị khóa!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", user);
        response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if ("logout".equals(request.getParameter("action"))) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    private void handleChangePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User currentUser = (User) session.getAttribute("currentUser");

        String oldPass = request.getParameter("oldPassword");
        String newPass = request.getParameter("newPassword");
        String confirmPass = request.getParameter("confirmPassword");

        if (!newPass.equals(confirmPass)) {
            request.setAttribute("error", "Xác nhận mật khẩu mới không khớp!");
            request.getRequestDispatcher("/user/change-password.jsp").forward(request, response);
            return;
        }

        try {
            authService.changePassword(currentUser.getId(), oldPass, newPass);
            request.setAttribute("message", "Đổi mật khẩu thành công!");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
        }
        request.getRequestDispatcher("/user/change-password.jsp").forward(request, response);
    }
}
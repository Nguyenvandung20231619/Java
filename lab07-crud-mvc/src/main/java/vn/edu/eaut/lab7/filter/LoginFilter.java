package vn.edu.eaut.lab7.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/sinh-vien", "/sinh-vien/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Kiểm tra xem đã lưu session 'username' khi đăng nhập thành công chưa
        boolean loggedIn = (session != null && session.getAttribute("username") != null);

        if (loggedIn) {
            chain.doFilter(request, response); // Đã đăng nhập -> Cho phép truy cập
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp"); // Chưa đăng nhập -> Chuyển về trang login
        }
    }
}
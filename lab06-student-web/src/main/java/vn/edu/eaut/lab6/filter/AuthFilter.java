package vn.edu.eaut.lab6.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter(urlPatterns = {"/students", "/student-form.jsp", "/welcome.jsp"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("AuthFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("username") != null;
        String username = loggedIn ? (String) session.getAttribute("username") : "Guest";

        // Bài 11: Ghi log truy cập ra Console
        System.out.printf("[LOG TRUY CẬP] %s | User: %s | Method: %s | URI: %s%n",
                LocalDateTime.now(), username, req.getMethod(), req.getRequestURI());

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
        System.out.println("AuthFilter destroyed");
    }
}
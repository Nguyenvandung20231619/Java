package vn.edu.eaut.lab9.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab9.model.SinhVien;
import vn.edu.eaut.lab9.service.SinhVienService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class SinhVienController extends HttpServlet {

    private final SinhVienService service = new SinhVienService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            Integer id = parseId(request.getParameter("id"));
            if (id == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sinh viên không hợp lệ");
                return;
            }
            SinhVien sv = service.getSinhVienById(id);
            if (sv == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sinh viên");
                return;
            }
            request.setAttribute("sinhVien", sv);
            request.getRequestDispatcher("/views/sinhvien/form.jsp").forward(request, response);
            return;
        }

        if ("delete".equals(action)) {
            Integer id = parseId(request.getParameter("id"));
            if (id == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sinh viên không hợp lệ");
                return;
            }
            service.deleteSinhVien(id);
            response.sendRedirect(request.getContextPath() + "/sinh-vien");
            return;
        }

        String keyword = request.getParameter("keyword");
        request.setAttribute("dsSinhVien", service.searchSinhVien(keyword));
        request.getRequestDispatcher("/views/sinhvien/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idStr = request.getParameter("id");
        String maSV = request.getParameter("maSinhVien");
        String hoTen = request.getParameter("hoTen");
        String email = request.getParameter("email");
        String lop = request.getParameter("lop");
        String ngaySinhStr = request.getParameter("ngaySinh");

        if (maSV == null || maSV.isBlank() || hoTen == null || hoTen.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã sinh viên và họ tên là bắt buộc");
            return;
        }

        SinhVien sv = new SinhVien(maSV.trim(), hoTen.trim(), email, lop);

        if (ngaySinhStr != null && !ngaySinhStr.isBlank()) {
            try {
                sv.setNgaySinh(LocalDate.parse(ngaySinhStr));
            } catch (DateTimeParseException ex) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ngày sinh không hợp lệ");
                return;
            }
        }

        if (idStr != null && !idStr.isBlank()) {
            Integer id = parseId(idStr);
            if (id == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID sinh viên không hợp lệ");
                return;
            }
            sv.setId(id);
        }

        try {
            service.saveSinhVien(sv);
        } catch (IllegalArgumentException ex) {
            request.setAttribute("sinhVien", sv);
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("/views/sinhvien/form.jsp").forward(request, response);
            return;
        }
        response.sendRedirect(request.getContextPath() + "/sinh-vien");
    }

    private Integer parseId(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            return id > 0 ? id : null;
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
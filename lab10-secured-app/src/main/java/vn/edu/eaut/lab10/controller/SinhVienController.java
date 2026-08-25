package vn.edu.eaut.lab10.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.eaut.lab10.model.SinhVien;
import vn.edu.eaut.lab10.repository.SinhVienRepository;

import java.io.IOException;

@WebServlet("/staff/sinh-vien")
public class SinhVienController extends HttpServlet {

    private final SinhVienRepository sinhVienRepository = new SinhVienRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("edit".equals(request.getParameter("action"))) {
            try {
                SinhVien sinhVien = sinhVienRepository.findById(
                        Integer.valueOf(request.getParameter("id")));
                if (sinhVien == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sinh viên.");
                    return;
                }
                request.setAttribute("sinhVienEdit", sinhVien);
            } catch (NumberFormatException exception) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã sinh viên không hợp lệ.");
                return;
            }
        }
        request.setAttribute("sinhViens", sinhVienRepository.findAll());
        request.getRequestDispatcher("/staff/sinh-vien.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                sinhVienRepository.delete(Integer.valueOf(request.getParameter("id")));
            } else if ("update".equals(action)) {
                Integer id = Integer.valueOf(request.getParameter("id"));
                String maSinhVien = request.getParameter("maSinhVien");
                String hoTen = request.getParameter("hoTen");
                if (maSinhVien == null || maSinhVien.isBlank() || hoTen == null || hoTen.isBlank()) {
                    request.setAttribute("error", "Mã sinh viên và họ tên không được để trống.");
                    request.setAttribute("sinhVienEdit", sinhVienRepository.findById(id));
                    doGet(request, response);
                    return;
                }
                SinhVien sinhVien = sinhVienRepository.findById(id);
                if (sinhVien == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sinh viên.");
                    return;
                }
                sinhVien.setMaSinhVien(maSinhVien.trim());
                sinhVien.setHoTen(hoTen.trim());
                sinhVien.setEmail(request.getParameter("email"));
                sinhVien.setLop(request.getParameter("lop"));
                sinhVienRepository.update(sinhVien);
            } else {
                String maSinhVien = request.getParameter("maSinhVien");
                String hoTen = request.getParameter("hoTen");
                if (maSinhVien == null || maSinhVien.isBlank() || hoTen == null || hoTen.isBlank()) {
                    request.setAttribute("error", "Mã sinh viên và họ tên không được để trống.");
                    doGet(request, response);
                    return;
                }
                sinhVienRepository.save(new SinhVien(
                        maSinhVien.trim(),
                        hoTen.trim(),
                        request.getParameter("email"),
                        request.getParameter("lop")));
            }
            response.sendRedirect(request.getContextPath() + "/staff/sinh-vien");
        } catch (NumberFormatException exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã sinh viên không hợp lệ.");
        } catch (RuntimeException exception) {
            request.setAttribute("error", "Không thể cập nhật sinh viên. Mã sinh viên có thể đã tồn tại.");
            doGet(request, response);
        }
    }
}

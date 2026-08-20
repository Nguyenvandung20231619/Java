package vn.edu.eaut.lab8.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Sach {
    private int id;

    @NotBlank(message = "Tên sách không được để trống")
    private String tenSach;

    @NotBlank(message = "Tác giả không được để trống")
    private String tacGia;

    @NotNull(message = "Năm xuất bản không được để trống")
    @Min(value = 1000, message = "Năm xuất bản phải lớn hơn 1000")
    @Max(value = 2026, message = "Năm xuất bản không được vượt quá năm hiện tại")
    private Integer namXuatBan;

    public Sach() {}
    public Sach(int id, String tenSach, String tacGia, Integer namXuatBan) {
        this.id = id; this.tenSach = tenSach; this.tacGia = tacGia; this.namXuatBan = namXuatBan;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenSach() { return tenSach; }
    public void setTenSach(String tenSach) { this.tenSach = tenSach; }
    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public Integer getNamXuatBan() { return namXuatBan; }
    public void setNamXuatBan(Integer namXuatBan) { this.namXuatBan = namXuatBan; }
}
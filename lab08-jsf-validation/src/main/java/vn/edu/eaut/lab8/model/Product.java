package vn.edu.eaut.lab8.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Product {
    private int id;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String tenSp;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.01", message = "Giá sản phẩm phải lớn hơn 0")
    private Double gia;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    private Integer soLuong;

    public Product() {}
    public Product(int id, String tenSp, Double gia, Integer soLuong) {
        this.id = id; this.tenSp = tenSp; this.gia = gia; this.soLuong = soLuong;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenSp() { return tenSp; }
    public void setTenSp(String tenSp) { this.tenSp = tenSp; }
    public Double getGia() { return gia; }
    public void setGia(Double gia) { this.gia = gia; }
    public Integer getSoLuong() { return soLuong; }
    public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }
}
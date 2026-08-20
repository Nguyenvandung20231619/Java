package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.SinhVien;
import vn.edu.eaut.lab8.repository.SinhVienRepository;
import java.io.Serializable;
import java.util.*;

@Named("sinhVienBean")
@SessionScoped
public class SinhVienBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    private SinhVien sinhVien = new SinhVien();
    private final SinhVienRepository repo = new SinhVienRepository();
    private String keyword = "";
    private boolean isEditMode = false;
    private List<String> dsLop = Arrays.asList("DCCNTT15.10.1", "DCCNTT15.10.2", "DCCNTT15.10.3");

    public String save() {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }
        if (isEditMode) {
            repo.update(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã cập nhật thông tin sinh viên"));
            isEditMode = false;
        } else {
            repo.add(sinhVien);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã thêm sinh viên"));
        }
        sinhVien = new SinhVien();
        return "sinhvien-list?faces-redirect=true";
    }

    public String edit(SinhVien sv) {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }
        this.sinhVien = new SinhVien(sv.getId(), sv.getMaSinhVien(), sv.getHoTen(), sv.getEmail(), sv.getLop());
        this.isEditMode = true;
        return "sinhvien-form?faces-redirect=true";
    }

    public String delete(int id) {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }
        repo.delete(id);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã xóa sinh viên"));
        return null;
    }

    public List<SinhVien> getDsSinhVien() {
        if (!loginBean.isLoggedIn()) {
            return repo.findAll();
        }
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }
        List<SinhVien> filtered = new ArrayList<>();
        for (SinhVien sv : repo.findAll()) {
            if (sv.getHoTen().toLowerCase().contains(keyword.toLowerCase()) ||
                sv.getLop().toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(sv);
            }
        }
        return filtered;
    }

    public String search() {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }
        return null;
    }

    public SinhVien getSinhVien() { return sinhVien; }
    public void setSinhVien(SinhVien sinhVien) { this.sinhVien = sinhVien; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public boolean isEditMode() { return isEditMode; }
    public List<String> getDsLop() { return dsLop; }
}
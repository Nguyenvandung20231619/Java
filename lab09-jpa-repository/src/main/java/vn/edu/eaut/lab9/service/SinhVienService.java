package vn.edu.eaut.lab9.service;

import vn.edu.eaut.lab9.model.SinhVien;
import vn.edu.eaut.lab9.repository.SinhVienRepository;

import java.util.List;

public class SinhVienService {

    private final SinhVienRepository repository;

    public SinhVienService() {
        this.repository = new SinhVienRepository();
    }

    public List<SinhVien> getAllSinhVien() {
        return repository.findAll();
    }

    public SinhVien getSinhVienById(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return repository.findById(id);
    }

    public void saveSinhVien(SinhVien sinhVien) {
        if (sinhVien == null) {
            throw new IllegalArgumentException("Thông tin sinh viên không được để trống!");
        }

        if (repository.existsByMaSinhVien(sinhVien.getMaSinhVien(), sinhVien.getId())) {
            throw new IllegalArgumentException("Mã sinh viên đã tồn tại!");
        }
        
        if (sinhVien.getId() == null) {
            repository.save(sinhVien);
        } else {
            repository.update(sinhVien);
        }
    }

    public void deleteSinhVien(Integer id) {
        if (id != null && id > 0) {
            repository.delete(id);
        }
    }

    public List<SinhVien> searchSinhVien(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return repository.findAll();
        }
        return repository.search(keyword.trim());
    }
}
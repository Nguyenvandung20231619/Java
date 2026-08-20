package vn.edu.eaut.lab8.repository;

import vn.edu.eaut.lab8.model.SinhVien;
import java.util.*;

public class SinhVienRepository {
    private static final List<SinhVien> data = new ArrayList<>();
    private static int autoId = 3;

    static {
        data.add(new SinhVien(1, "20240001", "Nguyễn Văn An", "an@gmail.com", "DCCNTT15.10.1"));
        data.add(new SinhVien(2, "20240002", "Trần Thị Bình", "binh@gmail.com", "DCCNTT15.10.2"));
    }

    public List<SinhVien> findAll() { return data; }

    public void add(SinhVien sv) {
        sv.setId(autoId++);
        data.add(sv);
    }

    public void update(SinhVien sv) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == sv.getId()) {
                data.set(i, sv);
                break;
            }
        }
    }

    public void delete(int id) {
        data.removeIf(x -> x.getId() == id);
    }
}
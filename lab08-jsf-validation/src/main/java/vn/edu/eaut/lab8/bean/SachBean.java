package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Sach;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("sachBean")
@SessionScoped
public class SachBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    private Sach sach = new Sach();
    private List<Sach> dsSach = new ArrayList<>();
    private int autoId = 1;

    public String save() {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }
        sach.setId(autoId++);
        dsSach.add(sach);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã thêm sách mới"));
        sach = new Sach();
        return null;
    }

    public Sach getSach() { return sach; }
    public void setSach(Sach sach) { this.sach = sach; }
    public List<Sach> getDsSach() { return dsSach; }
}
package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private boolean loggedIn = false;

    public String login() {
        if ("admin".equals(username) && "123456".equals(password)) {
            loggedIn = true;
            return "sinhvien-list?faces-redirect=true";
        }
        loggedIn = false;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Đăng nhập thất bại", "Sai tài khoản hoặc mật khẩu"));
        return "login";
    }

    public String logout() {
        loggedIn = false;
        username = "";
        password = "";
        return "login?faces-redirect=true";
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isLoggedIn() { return loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
}
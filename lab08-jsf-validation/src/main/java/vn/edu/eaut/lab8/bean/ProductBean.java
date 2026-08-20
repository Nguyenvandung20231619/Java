package vn.edu.eaut.lab8.bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import vn.edu.eaut.lab8.model.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("productBean")
@SessionScoped
public class ProductBean implements Serializable {
    @Inject
    private LoginBean loginBean;

    private Product product = new Product();
    private List<Product> dsProduct = new ArrayList<>();
    private int autoId = 1;

    public String save() {
        if (!loginBean.isLoggedIn()) {
            return "login?faces-redirect=true";
        }
        product.setId(autoId++);
        dsProduct.add(product);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thành công", "Đã thêm sản phẩm mới"));
        product = new Product();
        return null;
    }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public List<Product> getDsProduct() { return dsProduct; }
}
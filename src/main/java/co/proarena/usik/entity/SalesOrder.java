package co.proarena.usik.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "sales_order")
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private Date date;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "price")
    private Float price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total")
    private Float total;

    public SalesOrder() {

    }

    public SalesOrder(Long id, Date date, Long userId, Long productId,
                      Float price, Integer quantity, Float total) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Product = [" +
                "id = " + id +
                ", date = " + date +
                ", userId = " + userId +
                ", productId = " + productId +
                ", price = " + price +
                ", quantity = " + quantity +
                ", total = " + total + "]";
    }
}

package co.proarena.usik.entity;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Float price;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "sold_number")
    private Integer soldNumber;

    public Product() {
    }

    public Product(Long id, String name, Float price, Integer amount, Integer soldNumber) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.soldNumber = soldNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getSoldNumber() {
        return soldNumber;
    }

    public void setSoldNumber(Integer soldNumber) {
        this.soldNumber = soldNumber;
    }

    @Override
    public String toString() {
        return "Product = [" +
                "id = " + id +
                ", name = " + name +
                ", price = " + price +
                ", amount = " + amount +
                ", soldNumber = " + soldNumber + "]";
    }
}

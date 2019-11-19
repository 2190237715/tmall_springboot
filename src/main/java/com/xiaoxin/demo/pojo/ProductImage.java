package com.xiaoxin.demo.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName ProductImage--产品图片
 * @createDate 2019/11/19 16:05
 */
@Entity
@Table(name = "ProductImage")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ProductImage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pid")
    private Product product;

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

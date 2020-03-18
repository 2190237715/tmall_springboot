package com.xiaoxin.demo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName Review--评价
 * @createDate 2020/03/13 15:31
 */
@Entity
@Table(name = "review")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;//id
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;//用户
    @ManyToOne
    @JoinColumn(name = "pid")
    private Product product;//产品
    private String content;//内容
    private Date createDate;//创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

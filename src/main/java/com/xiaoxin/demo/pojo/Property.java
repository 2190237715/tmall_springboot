package com.xiaoxin.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author fuqiangxin
 * @version 1.0
 * @ClassName Property--属性
 * @createDate 2019/11/15 09:00
 */
@Entity
@Table(name = "property")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Property {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "cid")
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

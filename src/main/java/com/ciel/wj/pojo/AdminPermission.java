package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "admin_permission")
@JsonIgnoreProperties({"handler","hibernateLazyInitialier"})
public class AdminPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String name;
    String desc_;
    String url;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc_(String desc_) {
        this.desc_ = desc_;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc_() {
        return desc_;
    }

    public String getUrl() {
        return url;
    }
}

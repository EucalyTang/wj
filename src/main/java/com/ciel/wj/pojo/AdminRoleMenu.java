package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "admin_role_menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitialier"})
public class AdminRoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int rid;
    int mid;

    public void setId(int id) {
        this.id = id;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getId() {
        return id;
    }

    public int getRid() {
        return rid;
    }

    public int getMid() {
        return mid;
    }
}

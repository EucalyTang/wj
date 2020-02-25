package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "admin_user_role")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    int uid;
    int rid;

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public int getRid() {
        return rid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
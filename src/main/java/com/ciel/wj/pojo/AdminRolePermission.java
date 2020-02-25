package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "admin_role_permission")
@JsonIgnoreProperties({"handler","hibernateLazyInitialier"})
public class AdminRolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int rid;
    int pid;

    public void setId(int id) {
        this.id = id;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return id;
    }

    public int getRid() {
        return rid;
    }

    public int getPid() {
        return pid;
    }
}

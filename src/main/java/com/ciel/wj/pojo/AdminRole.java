package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin_role")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String name;
    String nameZh;
    boolean enabled;
    @Transient
    List<AdminPermission> perms;
    @Transient
    List<AdminMenu> menus;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public List<AdminMenu> getMenus() {
        return menus;
    }

    public List<AdminPermission> getPerms() {
        return perms;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setMenus(List<AdminMenu> menus) {
        this.menus = menus;
    }

    public void setPerms(List<AdminPermission> perms) {
        this.perms = perms;
    }
}

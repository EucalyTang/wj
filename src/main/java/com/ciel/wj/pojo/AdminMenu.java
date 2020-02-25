package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admin_menu")
@JsonIgnoreProperties({"handler", "hiberneteLazyInitializer"})
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String path;
    String name;
    String nameZh;
    String iconCls;
    String component;
    int parentId;
    @Transient
    List<AdminMenu> children;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getComponent() {
        return component;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }

    public List<AdminMenu> getChildren() {
        return children;
    }
}

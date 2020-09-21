package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    private String password;
    private String salt;
    private String name;
    private String phone;
    @Email(message = "请输入正确的邮箱")
    private String email;
    private String inviteCode;
    private boolean enabled;

    @OneToMany
    @JoinColumn(name = "userInviteCode")
    private List<Member> members;

    @Transient
    private List<AdminRole> roles;

//    // 默认构造函数
//    public User() {}
//
//    // 用于配合自定义查询的构造函数
//    public User(int id,String username, String name, String phone, String email, String inviteCode, boolean enabled) {
//        this.id = id;
//        this.username = username;
//        this.name = name;
//        this.phone = phone;
//        this.email = email;
//        this.inviteCode = inviteCode;
//        this.enabled = enabled;
//    }

}

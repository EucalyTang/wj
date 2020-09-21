package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "member")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameZh;
    private String nameEn;
    private String sex;
    private String email;
    private String passportNumber;
    private String passportImg;
    private String password;
    private Date birthDate;
    private String idpaNumber;
    private String memberNumber;
    private String militaryOrPolice;
    private boolean enabled;

    private String userInviteCode;

}

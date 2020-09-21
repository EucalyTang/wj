package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order_equip")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class OrderEquip {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mid")
    private Member member;

    private Date scheduleDatetime;

    @ManyToOne
    @JoinColumn(name = "gun_id")
    private Gun gun;
    private int gunQuantity;

    //private double gunDiscount;
    private double gunAmount;

    @ManyToOne
    @JoinColumn(name = "bullet_id")
    private Bullet bullet;
    private int bulletQuantity;

    //private double bulletDiscount;
    private double bulletAmount;

    private double originTotal;
    private double finalTotal;
    private Date submitDatetime;

    @ManyToOne
    @JoinColumn(name = "submit_user_id")
    private User submitUser;

    private Date verifyDatetime;

    @ManyToOne
    @JoinColumn(name = "verify_user_id")
    private User verifyUser;

    @ManyToOne
    @JoinColumn
    private Discount discount;
    private int status;
}

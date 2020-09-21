package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "order_range")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class OrderRange {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mid")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "range_id")
    private ShootRange shootRange;

    private Date scheduleDatetime;
    //private double price;
    private Date submitDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submit_uid")
    private User submitUser;
    private Date verifyDatetime;
    @ManyToOne
    @JoinColumn(name = "verify_uid")
    private User verifyUser;
    private int status;

}

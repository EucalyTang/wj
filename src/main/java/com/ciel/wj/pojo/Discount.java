package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "discount")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Discount {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double gunDiscountValue;
    private double bulletDiscountValue;
    private String remark;
}

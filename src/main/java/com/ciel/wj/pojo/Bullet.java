package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bullet")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Bullet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bore;
    private String brand;
    private int quantity;
    private String level;
    private double price;
}

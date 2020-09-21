package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gun")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Gun {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String brand;
    private String type;
    private String bore;
    private double price;
    private int quantity;
}

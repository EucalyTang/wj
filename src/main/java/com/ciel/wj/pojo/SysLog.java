package com.ciel.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table( name = "sys_log")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class SysLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int userId;
    String userAction;
    String methodName;
    String methodArgs;
    long execTime;
    Date createDatetime;
}

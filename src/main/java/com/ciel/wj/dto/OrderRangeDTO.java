package com.ciel.wj.dto;

import com.ciel.wj.dto.base.OutputConverter;
import com.ciel.wj.pojo.OrderRange;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class OrderRangeDTO implements OutputConverter<OrderRangeDTO, OrderRange> {
    private int id;
    private int memberId;
    private String memberNameZh;
    private int shootRangeId;
    private String shootRangeName;
    private Date scheduleDatetime;
    private double shootRangePrice;
    private Date submitDatetime;
    private int submitUserId;
    private String submitUserName;
    private Date verifyDatetime;
    //private int verifyUserId;
    private String verifyUserName;
    private int status;

    public OrderRangeDTO(OrderRange or) {
        this.id = or.getId();
        this.memberId = or.getMember().getId();
        this.memberNameZh = or.getMember().getNameZh();
        this.shootRangeId = or.getShootRange().getId();
        this.shootRangeName = or.getShootRange().getName();
        this.scheduleDatetime = or.getScheduleDatetime();
        this.shootRangePrice = or.getShootRange().getPrice();
        this.submitDatetime = or.getSubmitDatetime();
        this.submitUserId = or.getSubmitUser().getId();
        this.submitUserName = or.getSubmitUser().getName();
        this.verifyDatetime = or.getVerifyDatetime();
        //this.verifyUserId = or.getVerifyUser().getId();
        this.verifyUserName = or.getVerifyUser().getName();
        this.status = or.getStatus();
    }

    public OrderRangeDTO() {
    }

}

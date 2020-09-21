package com.ciel.wj.dto;

import com.ciel.wj.dto.base.OutputConverter;
import com.ciel.wj.pojo.OrderEquip;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class OrderEquipDTO implements OutputConverter<OrderEquipDTO, OrderEquip> {
    private int id;
    private int memberId;
    private String memberNameZh;
    private int gunId;
    private String gunName;
    private double gunPrice;
    private int gunQuantity;
    private double gunAmount;
    private int bulletId;
    private String bulletBrand;
    private String bulletLevel;
    private double bulletPrice;
    private int bulletQuantity;
    private double bulletAmount;

    private Date scheduleDatetime;
    private double originTotal;
    private double finalTotal;
    private Date submitDatetime;
    private int submitUserId;
    private String submitUserName;
    private Date verifyDatetime;
    private int verifyUserId;
    private String verifyUserName;

    private int discountId;
    private String discountName;
    private double gunDiscountValue;
    private double bulletDiscountValue;
    private int status;

    public OrderEquipDTO(OrderEquip oe) {
        this.id = oe.getId();

        this.memberId = oe.getMember().getId();
        this.memberNameZh = oe.getMember().getNameZh();

        this.gunId = oe.getGun().getId();
        this.gunName = oe.getGun().getName();
        this.gunPrice = oe.getGun().getPrice();

        this.bulletId = oe.getBullet().getId();
        this.bulletBrand = oe.getBullet().getBrand();
        this.bulletLevel = oe.getBullet().getLevel();
        this.bulletPrice = oe.getBullet().getPrice();

        this.gunAmount = oe.getGunAmount();
        this.gunQuantity = oe.getGunQuantity();
        this.bulletQuantity = oe.getBulletQuantity();
        this.bulletAmount = oe.getBulletAmount();

        this.scheduleDatetime = oe.getScheduleDatetime();
        this.originTotal = oe.getOriginTotal();
        this.finalTotal = oe.getFinalTotal();

        this.submitDatetime = oe.getSubmitDatetime();
        this.verifyDatetime = oe.getVerifyDatetime();

        this.submitUserId = oe.getSubmitUser().getId();
        this.submitUserName = oe.getSubmitUser().getName();

        this.verifyUserId = oe.getVerifyUser().getId();
        this.verifyUserName = oe.getVerifyUser().getName();

        this.discountId = oe.getDiscount().getId();
        this.discountName = oe.getDiscount().getName();
        this.gunDiscountValue = oe.getDiscount().getGunDiscountValue();
        this.bulletDiscountValue = oe.getDiscount().getBulletDiscountValue();

        this.status = oe.getStatus();
    }

    public OrderEquipDTO() {
    }
}

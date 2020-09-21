package com.ciel.wj.param;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderRangeParam {
    private String memberNameZh;
    private String submitUserName;
    private int status;
}

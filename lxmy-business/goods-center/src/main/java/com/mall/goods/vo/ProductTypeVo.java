package com.mall.goods.vo;


import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品分类对应属性信息
 *  on 2018/5/23.
 */
@Data
public class ProductTypeVo {
    private Long id;
    private Long pid;
    private Long goodsId;
    private String pic;
    private String name;
    private BigDecimal price;

}

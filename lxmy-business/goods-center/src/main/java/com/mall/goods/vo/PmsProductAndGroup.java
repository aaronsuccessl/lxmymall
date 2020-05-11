package com.mall.goods.vo;



import com.mall.common.entity.pms.PmsProduct;
import lombok.Data;

/**
 * 创建和修改商品时使用的参数
 *  on 2018/4/26.
 */
@Data
public class PmsProductAndGroup extends PmsProduct {
    private String isGroup = "1"; //1 可以发起团购
    private String  is_favorite;
}

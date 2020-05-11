package com.mall.goods.vo;


import com.mall.common.entity.pms.PmsProductAttribute;
import com.mall.common.entity.pms.PmsProductAttributeCategory;

import java.util.List;

/**
 * 包含有分类下属性的dto
 *  on 2018/5/24.
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    private List<PmsProductAttribute> productAttributeList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }
}

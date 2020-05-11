package com.mall.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.common.entity.pms.PmsBrand;
import com.mall.common.entity.pms.PmsProduct;
import com.mall.common.entity.pms.PmsProductVertifyRecord;
import com.mall.common.vo.GoodsDetailResult;
import com.mall.common.vo.HomeContentResult;
import com.mall.goods.vo.PmsProductParam;
import com.mall.goods.vo.PmsProductResult;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author
 * @since 2019-04-19
 */
public interface IPmsProductService extends IService<PmsProduct> {

    /**
     * 根据商品编号获取更新信息
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * 根据商品名称或者货号模糊查询
     */
    List<PmsProduct> list(String keyword);

    List<PmsProduct> getNewProductList(int pageNum, int pageSize) ;

}

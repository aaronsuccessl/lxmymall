package com.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.entity.pms.*;
import com.mall.common.entity.pms.PmsProduct;
import com.mall.common.entity.sms.SmsHomeAdvertise;
import com.mall.common.redis.constant.RedisToolsConstant;
import com.mall.common.redis.template.RedisRepository;
import com.mall.common.utils.ValidatorUtils;
import com.mall.common.vo.GoodsDetailResult;
import com.mall.common.vo.HomeContentResult;
import com.mall.goods.mapper.*;
import com.mall.goods.service.*;
import com.mall.goods.vo.PmsProductParam;
import com.mall.goods.vo.PmsProductResult;
import com.mall.sentinel.config.ConstansValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author
 * @since 2019-04-19
 */
@Slf4j
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

    @Resource
    private PmsProductMapper productMapper;

    @Resource
    private RedisRepository redisRepository;


    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productMapper.getUpdateInfo(id);
    }


    @Override
    public List<PmsProduct> list(String keyword) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("delete_status", 0);

        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("name", keyword);

        }
        return productMapper.selectList(queryWrapper);
    }


    /**
     * 建立和插入关系表操作
     *
     * @param dao       可以操作的dao
     * @param dataList  要插入的数据
     * @param productId 建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("saveBatch", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<PmsProduct> getNewProductList(int pageNum, int pageSize) {
        PmsProduct query = new PmsProduct();
        query.setPublishStatus(1);
        query.setVerifyStatus(1);
        return this.page(new Page<PmsProduct>(pageNum, pageSize), new QueryWrapper<>(query).orderByDesc("create_time").select(ConstansValue.sampleGoodsList)).getRecords();
    }


}



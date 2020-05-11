package com.mall.goods.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.annotation.SysLog;
import com.mall.common.constant.ConstansValue;
import com.mall.common.entity.pms.PmsProduct;
import com.mall.common.utils.CommonResult;
import com.mall.common.utils.DateUtils;
import com.mall.common.utils.ValidatorUtils;
import com.mall.goods.vo.PmsProductParam;
import com.mall.goods.vo.PmsProductResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author
 * @since 2019-04-19
 */
@Slf4j
@RestController
@Api(tags = "PmsProductController", description = "商品信息管理")
@RequestMapping("/pms/PmsProduct")
public class ProductController {
    @Resource
    private com.mall.goods.service.IPmsProductService IPmsProductService;

    @SysLog(MODULE = "pms", REMARK = "根据条件查询所有商品信息列表")
    @ApiOperation("根据条件查询所有商品信息列表")
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('pms:PmsProduct:read')")
    public Object getPmsProductByPage(PmsProduct entity,
                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        try {
            return new CommonResult().success(IPmsProductService.page(new Page<PmsProduct>(pageNum, pageSize), new QueryWrapper<>(entity).orderByDesc("create_time").select(ConstansValue.sampleGoodsList)));
        } catch (Exception e) {
            log.error("根据条件查询所有商品信息列表：%s", e.getMessage(), e);
        }
        return new CommonResult().failed();
    }

    @SysLog(MODULE = "pms", REMARK = "删除商品信息")
    @ApiOperation("删除商品信息")
    @GetMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('pms:PmsProduct:delete')")
    public Object deletePmsProduct(@ApiParam("商品信息id") @PathVariable Long id) {
        try {
            if (ValidatorUtils.empty(id)) {
                return new CommonResult().paramFailed("商品信息id");
            }
            if (IPmsProductService.removeById(id)) {
                return new CommonResult().success();
            }
        } catch (Exception e) {
            log.error("删除商品信息：%s", e.getMessage(), e);
            return new CommonResult().failed();
        }
        return new CommonResult().failed();
    }

}

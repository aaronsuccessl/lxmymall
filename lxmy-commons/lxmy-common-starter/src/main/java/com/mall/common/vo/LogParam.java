package com.mall.common.vo;

import lombok.Data;

/**
 * @Auther:
 * @Date: 2019/5/22 09:39
 * @Description:
 */
@Data
public class LogParam {
    private String startTime;
    private String endTime;
    private String keyword;
    private Integer current = 1;
    private Integer size = 10;
}

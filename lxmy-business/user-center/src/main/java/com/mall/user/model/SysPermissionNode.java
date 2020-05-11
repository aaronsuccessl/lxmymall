package com.mall.user.model;

import com.mall.common.model.SysPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *  on 2018/9/30.
 */
public class SysPermissionNode extends SysPermission {
    @Getter
    @Setter
    private List<SysPermissionNode> children;
}

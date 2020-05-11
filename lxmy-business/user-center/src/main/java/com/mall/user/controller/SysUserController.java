package com.mall.user.controller;

import com.mall.common.annotation.LoginUser;
import com.mall.common.annotation.SysLog;
import com.mall.common.model.*;
import com.mall.common.utils.CommonResult;
import com.mall.user.service.ISysUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @author
 * 用户
 */
@Slf4j
@RestController
@Api(tags = "用户模块api")
public class SysUserController {
    private static final String ADMIN_CHANGE_MSG = "超级管理员不给予修改";

    @Autowired
    private ISysUserService appUserService;

    /**
     * 当前登录用户 LoginAppUser
     *
     * @return
     */
    @ApiOperation(value = "根据access_token当前登录用户")
    @GetMapping("/users/current")
    public LoginAppUser getLoginAppUser(@LoginUser(isFull = true) SysUser user) {
        return appUserService.getLoginAppUser(user);
    }
    @SysLog(MODULE = "sys", REMARK = "获取当前登录用户信息")
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getAdminInfo(@LoginUser(isFull = true) SysUser user) {

        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", user.getIcon());
        return new CommonResult().success(data);
    }
    /**
     * 查询用户实体对象SysUser
     */
    @GetMapping(value = "/users/name/{username}")
    @ApiOperation(value = "根据用户名查询用户实体")
    @Cacheable(value = "user", key = "#username")
    public SysUser selectByUsername(@PathVariable String username) {
        return appUserService.selectByUsername(username);
    }

    /**
     * 查询用户登录对象LoginAppUser
     */

    @GetMapping(value = "/users-anon/login", params = "username")
    @ApiOperation(value = "根据用户名查询用户")
    public LoginAppUser findByUsername(@RequestParam("username") String username) {
        return appUserService.findByUsername(username);
    }

}

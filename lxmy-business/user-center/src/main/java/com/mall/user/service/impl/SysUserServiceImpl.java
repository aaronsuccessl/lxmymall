package com.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.common.constant.CommonConstant;
import com.mall.common.lock.DistributedLock;
import com.mall.common.model.*;
import com.mall.user.mapper.SysUserMapper;
import com.mall.user.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    private final static String LOCK_KEY_USERNAME = CommonConstant.LOCK_KEY_PREFIX+"username:";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserMapper userMapper;

    @Autowired
    private DistributedLock lock;

    @Override
    public LoginAppUser findByUsername(String username) {
        SysUser sysUser = this.selectByUsername(username);
        return getLoginAppUser(sysUser);
    }

    @Override
    public LoginAppUser getLoginAppUser(SysUser sysUser) {
        LoginAppUser loginAppUser = new LoginAppUser();
        BeanUtils.copyProperties(sysUser, loginAppUser);
        return loginAppUser;
        /*if (sysUser != null) {
            LoginAppUser loginAppUser = new LoginAppUser();
            BeanUtils.copyProperties(sysUser, loginAppUser);

            List<SysRole> sysRoles = roleMapper.findRolesByUserId(sysUser.getId());
            // 设置角色
            loginAppUser.setRoles(sysRoles);

            if (!CollectionUtils.isEmpty(sysRoles)) {
                Set<Long> roleIds = sysRoles.parallelStream().map(SuperEntity::getId).collect(Collectors.toSet());
                List<SysPermission> menus = roleMenuMapper.findMenusByRoleIds(roleIds, CommonConstant.PERMISSION);
                if (!CollectionUtils.isEmpty(menus)) {
                    Set<String> permissions = menus.parallelStream().map(p -> p.getPathMethod()+":"+p.getPath())
                            .collect(Collectors.toSet());
                    // 设置权限集合
                    loginAppUser.setPermissions(permissions);
                }
            }
            return loginAppUser;
        }
        return null;*/
    }

//    /**
//     * 根据用户名查询用户
//     * @param username
//     * @return
//     */
    @Override
    public SysUser selectByUsername(String username) {
        List<SysUser> users = baseMapper.selectList(
                new QueryWrapper<SysUser>().eq("username", username)
        );
        return getUser(users);
    }

    private SysUser getUser(List<SysUser> users) {
        SysUser user = null;
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }

}
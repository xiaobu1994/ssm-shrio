package com.xiaobu.service;


import com.xiaobu.entity.SysUser;

import java.util.Map;

/**
 * Shiro-权限相关的业务处理
 */
public interface ShiroService {

    /**
     * 初始化权限
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * 重新加载权限
     */
    void updatePermission();

    /**
     * 重新加载用户权限
     *
     */
    void reloadAuthorizingByUserId(SysUser user);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     */
    void reloadAuthorizingByRoleId(Long roleId);

}

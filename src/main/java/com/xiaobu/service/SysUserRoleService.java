package com.xiaobu.service;


import com.xiaobu.base.service.AbstractService;
import com.xiaobu.entity.SysUserRole;

/**
 * 用户角色
 */
public interface SysUserRoleService extends AbstractService<SysUserRole, Long> {

    /**
     * 添加用户角色
     */
    void addUserRole(Long userId, String roleIds);

    /**
     * 根据用户ID删除用户角色
     */
    void removeByUserId(Long userId);
}

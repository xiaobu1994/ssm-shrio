package com.xiaobu.service;


import com.xiaobu.base.service.AbstractService;
import com.xiaobu.entity.SysRoleResources;

/**
 * 角色资源
 */
public interface SysRoleResourcesService extends AbstractService<SysRoleResources, Long> {

    /**
     * 添加角色资源
     *
     */
    void addRoleResources(Long roleId, String resourcesIds);

    /**
     * 通过角色id批量删除
     *
     */
    void removeByRoleId(Long roleId);
}

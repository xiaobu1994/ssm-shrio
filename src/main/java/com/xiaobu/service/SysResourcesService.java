package com.xiaobu.service;

import com.github.pagehelper.PageInfo;
import com.xiaobu.base.service.AbstractService;
import com.xiaobu.entity.SysResources;
import com.xiaobu.entity.vo.ResourceConditionVO;

import java.util.List;
import java.util.Map;

/**
 * 系统资源
 */
public interface SysResourcesService extends AbstractService<SysResources, Long> {

    /**
     * 分页查询
     */
    PageInfo findPageBreakByCondition(ResourceConditionVO vo);

    /**
     * 获取用户的资源列表
     */
    List<SysResources> listUserResources(Map<String, Object> map);

    /**
     * 获取ztree使用的资源列表
     */
    List<Map<String, Object>> queryResourcesListWithSelected(Long rid);

    /**
     * 获取资源的url和permission
     */
    List<SysResources> listUrlAndPermission();

    /**
     * 获取所有可用的菜单资源
     */
    List<SysResources> listAllAvailableMenu();

    /**
     * 获取父级资源下所有menu资源
     */
    List<Map<String, Object>> listChildMenuByPid(Long pid);

    /**
     * 获取用户关联的所有资源
     *
     */
    List<SysResources> listByUserId(Long userId);
}

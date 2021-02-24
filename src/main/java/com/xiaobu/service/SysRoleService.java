package com.xiaobu.service;


import com.github.pagehelper.PageInfo;
import com.xiaobu.base.service.AbstractService;
import com.xiaobu.entity.SysRole;
import com.xiaobu.entity.vo.RoleConditionVO;

import java.util.List;
import java.util.Map;

/**
 * 角色
 */
public interface SysRoleService extends AbstractService<SysRole, Long> {

    /**
     * 获取ztree使用的角色列表
     */
    List<Map<String, Object>> queryRoleListWithSelected(Integer uid);

    /**
     * 分页查询
     */
    PageInfo<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    /**
     * 获取用户的角色
     */
    List<SysRole> listRolesByUserId(Long userId);
}

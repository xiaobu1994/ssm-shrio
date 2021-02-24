package com.xiaobu.service;


import com.github.pagehelper.PageInfo;
import com.xiaobu.base.service.AbstractService;
import com.xiaobu.entity.SysUser;
import com.xiaobu.entity.vo.UserConditionVO;

import java.util.List;


/**
 * 用户
 */
public interface SysUserService extends AbstractService<SysUser, Long> {

    /**
     * 分页查询
     */
    PageInfo<SysUser> findPageBreakByCondition(UserConditionVO vo);

    /**
     * 更新用户最后一次登录的状态信息
     */
    SysUser updateUserLastLoginInfo(SysUser user);

    /**
     * 根据用户名查找
     */
    SysUser getByUserName(String userName);

    /**
     * 通过角色Id获取用户列表
     */
    List<SysUser> listByRoleId(Long roleId);

}

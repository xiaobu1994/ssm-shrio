package com.xiaobu.mapper;

import com.xiaobu.base.mapper.BaseMapper;
import com.xiaobu.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiaobu
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<Integer> findUserIdByRoleId(Integer roleId);
}

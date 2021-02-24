package com.xiaobu.mapper;

import com.xiaobu.base.mapper.BaseMapper;
import com.xiaobu.entity.SysUser;
import com.xiaobu.entity.vo.UserConditionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiaobu
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findPageBreakByCondition(UserConditionVO vo);

    List<SysUser> listByRoleId(Long roleId);

}

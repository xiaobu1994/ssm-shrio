package com.xiaobu.mapper;

import com.xiaobu.base.mapper.BaseMapper;
import com.xiaobu.entity.SysRole;
import com.xiaobu.entity.vo.RoleConditionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xiaobu
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询
     *
     */
    List<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    /**
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     * 感谢网友
     *
     */
    List<SysRole> queryRoleListWithSelected(Integer userId);

    List<SysRole> listRolesByUserId(Long userId);
}

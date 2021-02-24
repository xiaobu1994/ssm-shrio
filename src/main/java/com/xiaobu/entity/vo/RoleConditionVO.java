package com.xiaobu.entity.vo;

import com.xiaobu.base.entity.vo.BaseConditionVO;
import com.xiaobu.entity.SysRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaobu
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleConditionVO extends BaseConditionVO {
    private SysRole sysRole;
}


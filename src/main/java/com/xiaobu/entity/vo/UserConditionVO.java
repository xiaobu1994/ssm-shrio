package com.xiaobu.entity.vo;

import com.xiaobu.base.entity.vo.BaseConditionVO;
import com.xiaobu.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述:
 * @author xiaobu
 * @date 2019/3/27 9:23
 * @version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class UserConditionVO extends BaseConditionVO {
    private SysUser sysUser;
}

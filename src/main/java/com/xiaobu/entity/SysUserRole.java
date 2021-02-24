
package com.xiaobu.entity;

import com.xiaobu.base.entity.vo.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserRole extends AbstractDO {
    private static final long serialVersionUID = 4331415988677877989L;
    private Long userId;
    private Long roleId;
}

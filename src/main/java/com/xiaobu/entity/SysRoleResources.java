
package com.xiaobu.entity;

import com.xiaobu.base.entity.vo.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleResources extends AbstractDO {
    private static final long serialVersionUID = 799936725542279796L;
    private Long roleId;
    private Long resourcesId;
}

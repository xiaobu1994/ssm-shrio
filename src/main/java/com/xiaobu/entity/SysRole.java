
package com.xiaobu.entity;

import com.xiaobu.base.entity.vo.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole extends AbstractDO {
    private static final long serialVersionUID = -7817094735102481756L;
    private String name;
    private String description;
    private Boolean available;

    @Transient
    private Integer selected;
}

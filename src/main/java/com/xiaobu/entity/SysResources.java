
package com.xiaobu.entity;

import com.xiaobu.base.entity.vo.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysResources extends AbstractDO {
    private static final long serialVersionUID = 7500559881443051031L;
    private String name;
    private String type;
    private String url;
    private String permission;
    private Long parentId;
    private Integer sort;
    private Boolean external;
    private Boolean available;
    private String icon;

    @Transient
    private String checked;
    @Transient
    private SysResources parent;
    @Transient
    private List<SysResources> nodes;
}

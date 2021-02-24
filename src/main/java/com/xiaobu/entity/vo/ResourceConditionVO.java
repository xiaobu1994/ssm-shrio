package com.xiaobu.entity.vo;

import com.xiaobu.base.entity.vo.BaseConditionVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiaobu
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResourceConditionVO extends BaseConditionVO {
    private String type;
}


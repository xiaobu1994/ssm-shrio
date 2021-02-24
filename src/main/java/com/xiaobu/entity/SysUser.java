
package com.xiaobu.entity;

import com.xiaobu.base.entity.vo.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends AbstractDO {
    private static final long serialVersionUID = -5045412421364087713L;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private String qq;
    private Date birthday;
    private Integer gender;
    private String avatar;
    private String userType;
    private String regIp;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Integer loginCount;
    private String remark;
    private Integer status;
}

package com.xiaobu.config.shiro.realm;

import com.xiaobu.base.enums.UserStatusEnum;
import com.xiaobu.entity.SysResources;
import com.xiaobu.entity.SysRole;
import com.xiaobu.entity.SysUser;
import com.xiaobu.service.SysResourcesService;
import com.xiaobu.service.SysRoleService;
import com.xiaobu.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Shiro-密码输入错误的状态下重试次数的匹配管理
 * @author xiaobu
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private SysUserService userService;
    @Resource
    private SysResourcesService resourcesService;
    @Resource
    private SysRoleService roleService;

    /**
     * 认证
     * 提供账户信息返回认证信息（用户的角色信息集合）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        SysUser user = userService.getByUserName(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在！");
        }
        if (user.getStatus() != null && UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new LockedAccountException("帐号已被锁定，禁止登录！");
        }

        // principal参数使用用户Id，方便动态刷新用户权限
        return new SimpleAuthenticationInfo(
                user.getId(),
                user.getPassword(),
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    /**
     * 授权，为当前登录的Subject授予角色和权限（角色的权限信息集合）  getPrincipal 身份
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();

        // 赋予角色
        List<SysRole> roleList = roleService.listRolesByUserId(userId);
        for (SysRole role : roleList) {
            info.addRole(role.getName());
        }

        // 赋予权限
        List<SysResources> resourcesList = resourcesService.listByUserId(userId);
        if (!CollectionUtils.isEmpty(resourcesList)) {
            for (SysResources resources : resourcesList) {
                String permission = resources.getPermission();
                System.out.println(resources.getName() + "   " + permission);
                if (!StringUtils.isEmpty(permission)) {
                    info.addStringPermission(permission);
                }
            }
        }
        return info;
    }

}

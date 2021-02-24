package com.xiaobu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobu.base.entity.vo.PageResult;
import com.xiaobu.base.entity.vo.ResponseVO;
import com.xiaobu.base.enums.ResponseStatus;
import com.xiaobu.base.util.PasswordUtil;
import com.xiaobu.base.util.ResultUtil;
import com.xiaobu.entity.SysUser;
import com.xiaobu.entity.vo.UserConditionVO;
import com.xiaobu.service.SysUserRoleService;
import com.xiaobu.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 * @author xiaobu
 */
@RestController
@RequestMapping("/user")
public class RestUserController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private SysUserRoleService userRoleService;

    @PostMapping("/list")
    public PageResult list(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<SysUser> pageInfo = userService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    /**
     * 保存用户角色
     *
     * @param userId
     * @param roleIds
     *         用户角色
     *         此处获取的参数的角色id是以 “,” 分隔的字符串
     * @return
     */
    @PostMapping("/saveUserRoles")
    public ResponseVO saveUserRoles(Long userId, String roleIds) {
        if (StringUtils.isEmpty(userId)) {
            return ResultUtil.error("error");
        }
        userRoleService.addUserRole(userId, roleIds);
        return ResultUtil.success("成功");
    }

    @PostMapping(value = "/add")
    public ResponseVO add(SysUser user) {
        SysUser u = userService.getByUserName(user.getUsername());
        if (u != null) {
            return ResultUtil.error("该用户名[" + user.getUsername() + "]已存在！请更改用户名");
        }
        try {
            user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            userService.insert(user);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            userService.removeByPrimaryKey(id);
            userRoleService.removeByUserId(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个用户");
    }

    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.userService.getByPrimaryKey(id));
    }

    @PostMapping("/edit")
    public ResponseVO edit(SysUser user) {
        try {
            userService.updateSelective(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("用户修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }




    @RequiresPermissions("user:test")
    @GetMapping("test")
    public String test(){
        return "123";
    }
}

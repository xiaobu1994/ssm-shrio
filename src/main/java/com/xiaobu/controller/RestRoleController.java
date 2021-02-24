package com.xiaobu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobu.base.entity.vo.PageResult;
import com.xiaobu.base.entity.vo.ResponseVO;
import com.xiaobu.base.enums.ResponseStatus;
import com.xiaobu.base.util.ResultUtil;
import com.xiaobu.entity.SysRole;
import com.xiaobu.entity.vo.RoleConditionVO;
import com.xiaobu.service.ShiroService;
import com.xiaobu.service.SysRoleResourcesService;
import com.xiaobu.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统角色管理
 * @author xiaobu
 */
@RestController
@RequestMapping("/roles")
public class RestRoleController {
    @Autowired
    private SysRoleService roleService;
    @Autowired
    private SysRoleResourcesService roleResourcesService;
    @Autowired
    private ShiroService shiroService;

    @PostMapping("/list")
    public PageResult getAll(RoleConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo<SysRole> pageInfo = roleService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @PostMapping("/rolesWithSelected")
    public ResponseVO rolesWithSelected(Integer uid) {
        return ResultUtil.success(null, roleService.queryRoleListWithSelected(uid));
    }

    @PostMapping("/saveRoleResources")
    public ResponseVO saveRoleResources(Long roleId, String resourcesId) {
        if (StringUtils.isEmpty(roleId)) {
            return ResultUtil.error("error");
        }
        roleResourcesService.addRoleResources(roleId, resourcesId);
        // 重新加载所有拥有roleId的用户的权限信息
        shiroService.reloadAuthorizingByRoleId(roleId);
        return ResultUtil.success("成功");
    }

    @PostMapping(value = "/add")
    public ResponseVO add(SysRole role) {
        roleService.insert(role);
        return ResultUtil.success("成功");
    }

    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            roleService.removeByPrimaryKey(id);
            roleResourcesService.removeByRoleId(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个角色");
    }

    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.roleService.getByPrimaryKey(id));
    }

    @PostMapping("/edit")
    public ResponseVO edit(SysRole role) {
        try {
            roleService.updateSelective(role);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("角色修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}

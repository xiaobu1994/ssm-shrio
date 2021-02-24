package com.xiaobu.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobu.base.entity.vo.PageResult;
import com.xiaobu.base.entity.vo.ResponseVO;
import com.xiaobu.base.enums.ResponseStatus;
import com.xiaobu.base.util.ResultUtil;
import com.xiaobu.entity.SysResources;
import com.xiaobu.entity.vo.ResourceConditionVO;
import com.xiaobu.service.ShiroService;
import com.xiaobu.service.SysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统资源管理
 * @author xiaobu
 */
@RestController
@RequestMapping("/resources")
public class RestResourcesController {

    @Autowired
    private SysResourcesService resourcesService;
    @Autowired
    private ShiroService shiroService;

    @PostMapping("/list")
    public PageResult getAll(ResourceConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber() - 1, vo.getPageSize());
        PageInfo pageInfo = resourcesService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @PostMapping("/resourcesWithSelected")
    public ResponseVO resourcesWithSelected(Long rid) {
        return ResultUtil.success(null, resourcesService.queryResourcesListWithSelected(rid));
    }

    @PostMapping(value= "/add")
    public ResponseVO add(SysResources resources) {
        resourcesService.insert(resources);
        //更新权限
        shiroService.updatePermission();
        return ResultUtil.success("成功");
    }

    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        for (Long id : ids) {
            resourcesService.removeByPrimaryKey(id);
        }

        //更新权限
        shiroService.updatePermission();
        return ResultUtil.success("成功删除 [" + ids.length + "] 个资源");
    }

    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.resourcesService.getByPrimaryKey(id));
    }

    @PostMapping("/edit")
    public ResponseVO edit(SysResources resources) {
        try {
            resourcesService.updateSelective(resources);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("资源修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }
}

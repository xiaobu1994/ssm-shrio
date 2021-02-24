package com.xiaobu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobu.entity.SysRole;
import com.xiaobu.entity.vo.RoleConditionVO;
import com.xiaobu.mapper.SysRoleMapper;
import com.xiaobu.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 角色
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 获取ztree使用的角色列表
     */
    @Override
    public List<Map<String, Object>> queryRoleListWithSelected(Integer userId) {
        List<SysRole> sysRole = roleMapper.queryRoleListWithSelected(userId);
        if (CollectionUtils.isEmpty(sysRole)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysRole role : sysRole) {
            map = new HashMap<String, Object>(3);
            map.put("id", role.getId());
            map.put("pId", 0);
            map.put("checked", role.getSelected() != null && role.getSelected() == 1);
            map.put("name", role.getDescription());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<SysRole> findPageBreakByCondition(RoleConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysRole> sysRoles = roleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysRoles)) {
            return null;
        }
        PageInfo bean = new PageInfo<SysRole>(sysRoles);
        bean.setList(sysRoles);
        return bean;
    }

    /**
     * 获取用户的角色
     */
    @Override
    public List<SysRole> listRolesByUserId(Long userId) {
        return roleMapper.listRolesByUserId(userId);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public void insert(SysRole entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        roleMapper.insert(entity);
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<SysRole> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysRole> sysRole = new ArrayList<>();
        for (SysRole role : entities) {
            role.setUpdateTime(new Date());
            role.setCreateTime(new Date());
            sysRole.add(role);
        }
        roleMapper.insertList(sysRole);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    public void removeByPrimaryKey(Long primaryKey) {
        roleMapper.deleteByPrimaryKey(primaryKey);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    @Override
    public void update(SysRole entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public void updateSelective(SysRole entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public SysRole getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        return roleMapper.selectByPrimaryKey(primaryKey);

    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public SysRole getOneByEntity(SysRole entity) {
        Assert.notNull(entity, "User不可为空！");
        return roleMapper.selectOne(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    public List<SysRole> listAll() {
        return roleMapper.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<SysRole> listByEntity(SysRole entity) {
        Assert.notNull(entity, "Role不可为空！");
        return roleMapper.select(entity);
    }
}

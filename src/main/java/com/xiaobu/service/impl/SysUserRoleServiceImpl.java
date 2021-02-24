package com.xiaobu.service.impl;

import com.xiaobu.entity.SysUserRole;
import com.xiaobu.mapper.SysUserRoleMapper;
import com.xiaobu.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户角色
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper resourceMapper;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public void insert(SysUserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity);
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<SysUserRole> entities) {
        Assert.notNull(entities, "entities不可为空！");
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        List<SysUserRole> sysUserRole = new ArrayList<>();
        for (SysUserRole ur : entities) {
            ur.setUpdateTime(new Date());
            ur.setCreateTime(new Date());
            sysUserRole.add(ur);
        }
        resourceMapper.insertList(sysUserRole);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    public void removeByPrimaryKey(Long primaryKey) {
        resourceMapper.deleteByPrimaryKey(primaryKey);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    @Override
    public void update(SysUserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public void updateSelective(SysUserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public SysUserRole getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        return resourceMapper.selectByPrimaryKey(primaryKey);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public SysUserRole getOneByEntity(SysUserRole entity) {
        Assert.notNull(entity, "User不可为空！");
        return resourceMapper.selectOne(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    public List<SysUserRole> listAll() {
        return resourceMapper.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public List<SysUserRole> listByEntity(SysUserRole entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        return resourceMapper.select(entity);
    }


    /**
     * 添加用户角色
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addUserRole(Long userId, String roleIds) {
        //删除
        removeByUserId(userId);
        //添加
        String[] roleIdArr = roleIds.split(",");
        if (roleIdArr.length == 0) {
            return;
        }
        SysUserRole u = null;
        List<SysUserRole> roles = new ArrayList<>();
        for (String roleId : roleIdArr) {
            if (StringUtils.isEmpty(roleId)) {
                continue;
            }
            u = new SysUserRole();
            u.setUserId(userId);
            u.setRoleId(Long.parseLong(roleId));
            roles.add(u);
        }
        insertList(roles);
    }

    /**
     * 根据用户ID删除用户角色
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByUserId(Long userId) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        resourceMapper.deleteByExample(example);
    }
}

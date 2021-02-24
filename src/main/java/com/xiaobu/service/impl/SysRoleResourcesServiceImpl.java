package com.xiaobu.service.impl;

import com.xiaobu.entity.SysRoleResources;
import com.xiaobu.mapper.SysRoleResourcesMapper;
import com.xiaobu.service.SysRoleResourcesService;
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
 * 角色资源
 */
@Service
public class SysRoleResourcesServiceImpl implements SysRoleResourcesService {
    @Autowired
    private SysRoleResourcesMapper resourceMapper;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public void insert(SysRoleResources entity) {
        Assert.notNull(entity, "SysRoleResources不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity);
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<SysRoleResources> entities) {
        Assert.notNull(entities, "entities不可为空！");
        if (CollectionUtils.isEmpty(entities)) {
            return;
        }
        List<SysRoleResources> sysRoleResources = new ArrayList<>();
        for (SysRoleResources rr : entities) {
            rr.setUpdateTime(new Date());
            rr.setCreateTime(new Date());
            sysRoleResources.add(rr);
        }
        resourceMapper.insertList(sysRoleResources);
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
    public void update(SysRoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        entity.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public void updateSelective(SysRoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        entity.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public SysRoleResources getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        return resourceMapper.selectByPrimaryKey(primaryKey);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public SysRoleResources getOneByEntity(SysRoleResources entity) {
        Assert.notNull(entity, "SysRoleResources不可为空！");
        return  resourceMapper.selectOne(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    public List<SysRoleResources> listAll() {
        return resourceMapper.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<SysRoleResources> listByEntity(SysRoleResources entity) {
        Assert.notNull(entity, "RoleResources不可为空！");
        return resourceMapper.select(entity);
    }



    /**
     * 添加角色资源
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addRoleResources(Long roleId, String resourcesIds) {
        //删除
        removeByRoleId(roleId);
        //添加
        if (!StringUtils.isEmpty(resourcesIds)) {
            String[] resourcesArr = resourcesIds.split(",");
            if (resourcesArr.length == 0) {
                return;
            }
            SysRoleResources r = null;
            List<SysRoleResources> roleResources = new ArrayList<>();
            for (String ri : resourcesArr) {
                if (StringUtils.isEmpty(ri)) {
                    continue;
                }
                r = new SysRoleResources();
                r.setRoleId(roleId);
                r.setResourcesId(Long.parseLong(ri));
                r.setCreateTime(new Date());
                r.setUpdateTime(new Date());
                roleResources.add(r);

            }
            if (roleResources.size() == 0) {
                return;
            }
            resourceMapper.insertList(roleResources);
        }
    }

    /**
     * 通过角色id批量删除
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByRoleId(Long roleId) {
        //删除
        Example example = new Example(SysRoleResources.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        resourceMapper.deleteByExample(example);
    }
}

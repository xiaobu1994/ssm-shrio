package com.xiaobu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobu.entity.SysResources;
import com.xiaobu.entity.vo.ResourceConditionVO;
import com.xiaobu.mapper.SysResourceMapper;
import com.xiaobu.service.SysResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 系统资源
 */
@Service
public class SysResourcesServiceImpl implements SysResourcesService {

    @Autowired
    private SysResourceMapper resourceMapper;

    /**
     * 分页查询
     */
    @Override
    public PageInfo findPageBreakByCondition(ResourceConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysResources> sysResources = resourceMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<SysResources> resources = new ArrayList<>(sysResources);
        PageInfo bean = new PageInfo<SysResources>(sysResources);
        bean.setList(resources);
        return bean;
    }

    /**
     * 获取用户的资源列表
     */
    @Override
    public List<SysResources> listUserResources(Map<String, Object> map) {
        List<SysResources> sysResources = resourceMapper.listUserResources(map);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        return sysResources;
    }

    /**
     * 获取ztree使用的资源列表
     */
    @Override
    public List<Map<String, Object>> queryResourcesListWithSelected(Long rid) {
        List<SysResources> sysResources = resourceMapper.queryResourcesListWithSelected(rid);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (SysResources resources : sysResources) {
            map = new HashMap<String, Object>(3);
            map.put("id", resources.getId());
            map.put("pId", resources.getParentId());
            map.put("checked", resources.getChecked());
            map.put("name", resources.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 获取资源的url和permission
     */
    @Override
    public List<SysResources> listUrlAndPermission() {
        return resourceMapper.listUrlAndPermission();
    }

    /**
     * 获取所有可用的菜单资源
     */
    @Override
    public List<SysResources> listAllAvailableMenu() {
        return resourceMapper.listAllAvailableMenu();
    }

    /**
     * 获取父级资源下所有menu资源
     */
    @Override
    public List<Map<String, Object>> listChildMenuByPid(Long pid) {
        List<SysResources> sysResources = resourceMapper.listMenuResourceByPid(pid);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<Map<String, Object>> result = new LinkedList<>();
        Map<String, Object> item = null;
        for (SysResources sysResource : sysResources) {
            item = new HashMap<>(2);
            item.put("value", sysResource.getId());
            item.put("text", sysResource.getName());
            result.add(item);
        }
        return result;
    }

    /**
     * 获取用户关联的所有资源
     */
    @Override
    public List<SysResources> listByUserId(Long userId) {
        return resourceMapper.listByUserId(userId);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public void insert(SysResources entity) {
        Assert.notNull(entity, "Resources不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity);
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<SysResources> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysResources> sysResources = new ArrayList<>();
        for (SysResources resources : entities) {
            resources.setUpdateTime(new Date());
            resources.setCreateTime(new Date());
            sysResources.add(resources);
        }
        resourceMapper.insertList(sysResources);
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
    public void update(SysResources entity) {
        Assert.notNull(entity, "Resources不可为空！");
        entity.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKey(entity);
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public void updateSelective(SysResources entity) {
        Assert.notNull(entity, "Resources不可为空！");
        entity.setUpdateTime(new Date());
        resourceMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public SysResources getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        return resourceMapper.selectByPrimaryKey(primaryKey);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public SysResources getOneByEntity(SysResources entity) {
        Assert.notNull(entity, "User不可为空！");
        return resourceMapper.selectOne(entity);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    public List<SysResources> listAll() {
        return resourceMapper.selectAll();
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<SysResources> listByEntity(SysResources entity) {
        Assert.notNull(entity, "Resources不可为空！");
        return resourceMapper.select(entity);
    }

}

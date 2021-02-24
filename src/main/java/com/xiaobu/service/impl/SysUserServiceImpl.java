package com.xiaobu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaobu.base.enums.UserStatusEnum;
import com.xiaobu.base.util.IpUtil;
import com.xiaobu.base.util.PasswordUtil;
import com.xiaobu.entity.SysUser;
import com.xiaobu.entity.vo.UserConditionVO;
import com.xiaobu.exception.CustomException;
import com.xiaobu.holder.RequestHolder;
import com.xiaobu.mapper.SysUserMapper;
import com.xiaobu.service.SysRoleService;
import com.xiaobu.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleService roleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(SysUser user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setRegIp(IpUtil.getRealIp(RequestHolder.getRequest()));
        user.setStatus(UserStatusEnum.NORMAL.getCode());
         sysUserMapper.insertSelective(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<SysUser> users) {
        Assert.notNull(users, "Users不可为空！");
        List<SysUser> sysUsers = new ArrayList<>();
        String regIp = IpUtil.getRealIp(RequestHolder.getRequest());
        for (SysUser user : users) {
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
            user.setRegIp(regIp);
            sysUsers.add(user);
        }
        sysUserMapper.insertList(sysUsers);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey(Long primaryKey) {
        sysUserMapper.deleteByPrimaryKey(primaryKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUser user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                throw new CustomException("密码加密失败");
            }
        }
         sysUserMapper.updateByPrimaryKey(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSelective(SysUser user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new CustomException("密码加密失败");
            }
        } else {
            user.setPassword(null);
        }
         sysUserMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */

    @Override
    public SysUser getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        return sysUserMapper.selectByPrimaryKey(primaryKey);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public SysUser getOneByEntity(SysUser entity) {
        Assert.notNull(entity, "User不可为空！");
        return sysUserMapper.selectOne(entity);
    }

    @Override
    public List<SysUser> listAll() {
        return  sysUserMapper.selectAll();
    }

    @Override
    public List<SysUser> listByEntity(SysUser user) {
        Assert.notNull(user, "User不可为空！");
        return  sysUserMapper.select(user);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<SysUser> findPageBreakByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.findPageBreakByCondition(vo);
        PageInfo bean = new PageInfo<SysUser>(sysUsers);
        bean.setList(sysUsers);
        return bean;
    }

    /**
     * 更新用户最后一次登录的状态信息
     */
    @Override
    public SysUser updateUserLastLoginInfo(SysUser user) {
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.getRealIp(RequestHolder.getRequest()));
            user.setPassword(null);
            this.updateSelective(user);
        }
        return user;
    }

    /**
     * 根据用户名查找
     */
    @Override
    public SysUser getByUserName(String userName) {
        SysUser user = new SysUser();
        user.setUsername(userName);
        return getOneByEntity(user);
    }

    /**
     * 通过角色Id获取用户列表
     */
    @Override
    public List<SysUser> listByRoleId(Long roleId) {
        return sysUserMapper.listByRoleId(roleId);
    }

}

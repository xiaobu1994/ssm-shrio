package com.xiaobu.mapper;

import com.xiaobu.base.mapper.BaseMapper;
import com.xiaobu.entity.SysResources;
import com.xiaobu.entity.vo.ResourceConditionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xiaobu
 */
@Mapper
public interface SysResourceMapper extends BaseMapper<SysResources> {

    /**
     * 分页查询
     */
    List<SysResources> findPageBreakByCondition(ResourceConditionVO vo);

    List<SysResources> listUserResources(Map<String, Object> map);

    /**
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     * 感谢网友
     */
    List<SysResources> queryResourcesListWithSelected(Long rid);

    List<SysResources> listUrlAndPermission();

    List<SysResources> listAllAvailableMenu();

    List<SysResources> listMenuResourceByPid(Long pid);

    List<SysResources> listByUserId(Long userId);
}

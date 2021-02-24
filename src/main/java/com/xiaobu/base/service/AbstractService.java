
package com.xiaobu.base.service;

import java.util.List;


public interface AbstractService<T, PK> {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     * @param entity t
     */
    void insert(T entity);

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities  entities
     */
    void insertList(List<T> entities);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey key
     */
    void removeByPrimaryKey(PK primaryKey);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity t
     */
    void update(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity t
     */
    void updateSelective(T entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey key
     * @return t
     */
    T getByPrimaryKey(PK primaryKey);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity t
     * @return t
     */
    T getOneByEntity(T entity);

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return list
     */
    List<T> listAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity  entity
     * @return  list
     */
    List<T> listByEntity(T entity);
}
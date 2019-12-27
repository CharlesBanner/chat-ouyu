package com.charles.service.base.impl;
import com.charles.commons.domain.Page;
import com.charles.commons.model.base.IdAutoIncrementStrategy;
import com.charles.service.base.BaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author xiaos
 * @version $Id: BaseServiceImpl.java, v0.1 2018/10/30 10:31 xiaos Exp $$
 */
public class BaseServiceImpl<M extends Mapper<T>, T> implements BaseService<T> {

    @Autowired
    protected M mapper;

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    @Override
    public List<T> selectListAll() {
        return mapper.selectAll();
    }

    @Override
    public int selectCount(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    public int insert(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int insertSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    @Override
    public int delete(T entity) {
        return mapper.delete(entity);
    }

    @Override
    public int deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int updateSelectiveById(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public Page<T> selectPage(int pageNum, int pageSize, T condition) {
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageSize < 1){
            pageSize = 10;
        }
        com.github.pagehelper.Page<T> page = PageHelper.startPage(pageNum, pageSize);
        this.selectList(condition);
        return new Page<>(page);
    }

    @Override
    public Page<T> selectPageByExample(int pageNum, int pageSize, Object exampleCondition) {
        if (pageNum < 1){
            pageNum = 1;
        }
        if (pageSize < 1){
            pageSize = 10;
        }
        com.github.pagehelper.Page<T> page = PageHelper.startPage(pageNum, pageSize);
        this.selectByExample(exampleCondition);
        return new Page<>(page);
    }

    @Override
    public <Entity extends IdAutoIncrementStrategy> void distinct(List<Entity> list) {
        Iterator<Entity> iterator = list.iterator();
        Map<Integer, IdAutoIncrementStrategy> map = new HashMap<>();
        while (iterator.hasNext()) {
            Entity obj = iterator.next();
            if (map.get(obj.getId()) != null) {
                iterator.remove();
                continue;
            }
            map.put(obj.getId(), obj);
        }
    }
}
package net.tfedu.zhl.core.service;

import java.util.List;

import net.tfedu.zhl.helper.ResultJSON;
import tk.mybatis.mapper.entity.Example;

/**
 * demo，请勿删除
 * 
 * @author bruce
 *
 */
public interface BaseService<T> {

    /**
     * 
     * @param id
     * @return
     */
    ResultJSON get(Object id);
    
    
    /**
     * 根据主键获取
     * @param key
     * @return
     */
    ResultJSON getByPrimaryKey(Object key);    

    /**
     * 增加
     */
    ResultJSON insert(T c);
    
    /**
     * 增加
     */
    ResultJSON insertSelective(T c);
    
    /**
     * 增加
     */
    ResultJSON insertUseGeneratedKeys(T c);

    /**
     * 删除
     */
    ResultJSON delete(Object id);

    /**
     * 批量插入
     */
    ResultJSON insert(List<T> datas);
    
    /**
     * 按属性值查询
     * @param c
     * @return
     */
    ResultJSON select(T c);

    /**
     * 分页查询
     */
    ResultJSON getPage(int pageNum, int pageSize);

    
    /**
     * 分页查询
     */
    ResultJSON getPageByExample(Example example,int pageNum, int pageSize);
    
    /**
     * 更新部分属性
     */
    ResultJSON update(T c);
    
    
    /**
     * 获取全部记录
     * @param c
     * @return
     */
    ResultJSON selectAll();

}

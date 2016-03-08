package net.tfedu.zhl.cloud.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.tfedu.zhl.cloud.core.helper.CoreMapper;
import net.tfedu.zhl.cloud.core.service.BaseService;

@Service
public abstract class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    protected CoreMapper<T> coreMapper;

    public int save(T entity){
        return coreMapper.insert(entity);
    }

    public int delete(Object key){
        return coreMapper.deleteByPrimaryKey(key);
    }
    
	@Override
	public T selectByKey(Object key) {
		return coreMapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateAll(T entity) {
		return coreMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateNotNull(T entity) {
		return coreMapper.updateByPrimaryKeySelective(entity);
	}
    /**
     * 单表分页查询
     * 
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> selectPage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        //Spring4支持泛型注入
        return coreMapper.select(null);
    }
}
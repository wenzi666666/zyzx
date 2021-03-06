package net.tfedu.zhl.cloud.resource.poolTypeFormat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.dao.ResPoolMapper;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.helper.CacheUtil;
import net.tfedu.zhl.sso.respoolappend.dao.SResPoolAppendMapper;
import net.tfedu.zhl.sso.respoolappend.entity.SResPoolAppend;
import tk.mybatis.mapper.entity.Example;

/**
 * 查询 资源库
 * 
 * @author WeiCuicui
 *
 */
@Service("resPoolService")
public class ResPoolServiceImpl implements ResPoolService {

    @Resource
    ResPoolMapper resPoolMapper;
    
    @Resource
    SResPoolAppendMapper resPoolAppendMapper;

    /**
     *  查询所有资源库
     */
    @Override
    public List<ResPool> getAllPools() {

        List<ResPool> pools = resPoolMapper.getAllPools();

        // 添加“全部”这个资源库类型
        ResPool all = new ResPool();

        long id = 0;
        all.setId(id);
        all.setName("全部");
        pools.add(0, all); // 添加到列表第一个位置

        return pools;
    }

	@Override
	public List<ResPool> getExistPools() {
		
		Example example = new Example(ResPool.class);
		
		example.createCriteria().andCondition(" flag = false ");
		
		return resPoolMapper.selectByExample(example);
	}

	@Override
	@Cacheable(value=CacheUtil.CACHE_APP)
	public List<ResPool> getAllPoolsWithAppend() throws Exception {
		 
		List<ResPool> list = getExistPools();
		
		Example example = new Example(SResPoolAppend.class);
		
		example.createCriteria().andCondition(" flag = false ");
		
		List<SResPoolAppend> ls =  resPoolAppendMapper.selectByExample(example);
		
		
		ResPool temp = null;
		for (SResPoolAppend sResPoolAppend : ls) {
			temp = new ResPool();
			BeanUtils.copyProperties(temp, sResPoolAppend);
			list.add(temp);
		}
		
		
		
		return list;
	}
}
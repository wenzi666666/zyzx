package net.tfedu.zhl.sso.developer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.PaginationHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.app.dao.SAppMapper;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.developer.dao.SDeveloperMapper;
import net.tfedu.zhl.sso.developer.entity.SDeveloper;
import net.tfedu.zhl.sso.developer.service.SDeveloperService;
import tk.mybatis.mapper.entity.Example;

/**
 
  
    @author wangwr
    @date 2017年1月19日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("developerService")
public class SDeveloperServiceImpl extends BaseServiceImpl<SDeveloper> implements SDeveloperService {


	@Resource 
	SDeveloperMapper mapper;
	
	
	@Resource 
	SAppMapper appMapper;


	@SuppressWarnings("static-access")
	@Override
	public ResultJSON pageDeveloper(Example example, int pageNum, int pageSize) {
		
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("developerid desc");
        // 这里不能放其它语句
        List<SDeveloper> list = mapper.selectByExample(example);
        
        result = defaultSuccess(new PaginationHelper<SDeveloper>().transfer(list));

		return result;

		
	}


	@Override
	public ResultJSON addApp(SApp app) {
		return defaultSuccess(appMapper.insertSelective(app));
	}


	@Override
	public ResultJSON updateApp(SApp app) {
		return defaultSuccess(appMapper.updateByPrimaryKeySelective(app));
	}


	@Override
	public ResultJSON delApp(Integer appId) {
		return defaultSuccess(appMapper.deleteByPrimaryKey(appId));
	}


	@SuppressWarnings("static-access")
	@Override
	public ResultJSON pageApp(Example example, int pageNum, int pageSize) {
		
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("createtime desc");
        List<SApp> list = appMapper.selectByExample(example);
        
        result = defaultSuccess(new PaginationHelper<SApp>().transfer(list));

		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

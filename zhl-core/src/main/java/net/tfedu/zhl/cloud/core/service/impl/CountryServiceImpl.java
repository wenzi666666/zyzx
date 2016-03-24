package net.tfedu.zhl.cloud.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.tfedu.zhl.cloud.core.dao.CountryMapper;
import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.service.CountryService;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
	
	@Resource CountryMapper countryMapper;
	
	public int insert(Country c){
		return countryMapper.insert(c);
	}

	@Override
	public List<Country> selectAll() {
		return countryMapper.selectAll();
	}
	
	@Override
	public int insert(List<Country> datas) {
		return countryMapper.insertList(datas);
	}
	
	@Override
	public int update(int id, String cname){
		Country c = new Country();
		c.setId(id);
		c.setCname(cname);
		return countryMapper.updateByPrimaryKeySelective(c);
	}
	
	@Override
	public List<Country> getPage(int pageNum,int pageSize){
		//Page插件必须放在查询语句之前紧挨的第一个位置
		PageHelper.startPage(pageNum, pageSize);
		//这里不能放其它语句
		return countryMapper.selectAll();
	}
	
	@Override
	public List<Map<?,?>> getSelective(){
		//返回List(Map<字段名称，字段值>, Map<字段名称，字段值>,Map<字段名称，字段值>...)集合
		return countryMapper.queryMaps();
	}
	
	@Override
	public List<Long> queryIds(){
		return countryMapper.queryIds();
	}
}

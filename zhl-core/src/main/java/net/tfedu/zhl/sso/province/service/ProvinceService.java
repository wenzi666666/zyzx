package net.tfedu.zhl.sso.province.service;

import java.util.List;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.sso.province.entity.Province;

/**
 
  	省管理业务管理类
  
    @author wangwr
    @date 2017年1月6日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface ProvinceService extends BaseService<Province> {
	
	
	List<Province> queryProvinceByName(String name);

}

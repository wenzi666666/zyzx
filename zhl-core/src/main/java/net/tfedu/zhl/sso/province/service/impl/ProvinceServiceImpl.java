package net.tfedu.zhl.sso.province.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.province.dao.ProvinceMapper;
import net.tfedu.zhl.sso.province.entity.Province;
import net.tfedu.zhl.sso.province.service.ProvinceService;

/**
    	
           省管理业务管理类
  
    @author wangwr
    @date 2017年1月6日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service(value="provinceServiceImpl")
public class ProvinceServiceImpl extends BaseServiceImpl<Province> implements ProvinceService {
	
	@Autowired
	ProvinceMapper proMapper;



	@Override
	public List<Province> queryProvinceByName(String provinceName) {
		
		String _provinceName =  provinceName.replace("省","")
				.replace("市","")
				.replace("自治区","");
		
		return proMapper.queryProvinceByName(_provinceName);
	}

	

}

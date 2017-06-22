package net.tfedu.zhl.sso.province.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;
import net.tfedu.zhl.userlayer.province.entity.Province;
import net.tfedu.zhl.userlayer.province.service.ProvinceService;

/**
 
  
    @author wangwr
    @date 2017年3月22日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public class ProvinceServiceTest extends BaseServiceTestCase {
	
	@Resource
	ProvinceService provinceServiceImpl;
	
	
	ResultJSON result ; 

	@Test
	public void testQueryProvinceByName() {
		
		List<Province>	 ls = provinceServiceImpl.queryProvinceByName("山东省");
		
		System.out.println(ls!=null && ls.size()>0);
		
		if(ls!=null && ls.size()>0){
			
			System.out.println(ls.get(0).toString());
		}
	
	}

}

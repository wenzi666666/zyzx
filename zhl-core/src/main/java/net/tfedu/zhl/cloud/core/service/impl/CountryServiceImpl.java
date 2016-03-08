package net.tfedu.zhl.cloud.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.core.dao.CountryMapper;
import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.service.CountryService;

@Service("countryService")
public class CountryServiceImpl extends BaseServiceImpl<Country> implements CountryService {
	
	@Resource CountryMapper countryMapper;
	
	public int save(Country c){
		return super.save(c);
	}

	@Override
	public List<Country> selectAllTest() {
		return countryMapper.selectAllTest();
	}
	
}

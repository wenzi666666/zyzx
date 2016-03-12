package net.tfedu.zhl.cloud.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public List<Country> selectAllTest() {
		return countryMapper.selectAllTest();
	}
	
}

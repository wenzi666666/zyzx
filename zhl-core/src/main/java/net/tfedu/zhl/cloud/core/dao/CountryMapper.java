package net.tfedu.zhl.cloud.core.dao;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.helper.CoreMapper;

public interface CountryMapper extends CoreMapper<Country>{

	@SuppressWarnings("rawtypes")
	List<Map> queryMaps();

	List<Long> queryIds();

}

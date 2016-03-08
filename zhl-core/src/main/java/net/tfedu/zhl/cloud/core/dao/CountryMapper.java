package net.tfedu.zhl.cloud.core.dao;

import java.util.List;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.cloud.core.helper.CoreMapper;

public interface CountryMapper extends CoreMapper<Country>{
	//父接口已经提供了selectAll方法，这里是测试自定义sql
	public List<Country> selectAllTest();
}

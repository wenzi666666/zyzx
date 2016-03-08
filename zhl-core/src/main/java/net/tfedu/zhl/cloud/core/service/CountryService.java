package net.tfedu.zhl.cloud.core.service;

import java.util.List;

import net.tfedu.zhl.cloud.core.entity.Country;

public interface CountryService extends BaseService<Country> {
	public int save(Country c);
	public List<Country> selectAllTest();
}

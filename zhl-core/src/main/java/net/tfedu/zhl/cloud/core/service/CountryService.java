package net.tfedu.zhl.cloud.core.service;

import java.util.List;

import net.tfedu.zhl.cloud.core.entity.Country;

public interface CountryService {
	public int insert(Country c);
	public List<Country> selectAll();
}

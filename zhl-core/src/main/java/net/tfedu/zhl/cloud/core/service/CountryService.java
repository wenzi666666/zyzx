package net.tfedu.zhl.cloud.core.service;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.core.entity.Country;

public interface CountryService {
	public int insert(Country c);
	public List<Country> selectAll();
	int insert(List<Country> datas);
	List<Map<?,?>> getSelective();
	List<Country> getPage(int pageNum, int pageSize);
	int update(int id, String cname);
	List<Long> queryIds();
}

package net.tfedu.zhl.cloud.core.dao;

import java.util.List;
import java.util.Map;

import net.tfedu.zhl.cloud.core.entity.Country;
import net.tfedu.zhl.helper.CoreMapper;
/**
 * xxx实体类
 * @author bruce
 *
 */
public interface CountryMapper extends CoreMapper<Country>{
	/**
	 * 自由查询，万能的map，最佳伙伴是lambda
	 */
	List<Map<?,?>> queryMaps();
	/**
	 * 根据单个属性查询
	 */
	List<Long> queryIds();

}

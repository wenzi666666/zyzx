package net.tfedu.zhl.cloud.demo.service;

import net.tfedu.zhl.cloud.demo.entity.Country;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * demo，请勿删除
 * 
 * @author bruce
 *
 */
public interface CountryService extends BaseService<Country> {

    
    /**
     * 自由查询，万能的map，最佳伙伴是lambda
     */
    ResultJSON queryMaps();
  
    /**
     * 根据单个属性查询
     */
    ResultJSON queryIds();
}

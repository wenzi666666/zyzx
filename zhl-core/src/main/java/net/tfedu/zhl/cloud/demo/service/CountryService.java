package net.tfedu.zhl.cloud.demo.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.demo.entity.Country;

/**
 * demo，请勿删除
 * 
 * @author bruce
 *
 */
public interface CountryService {

    /**
     * 
     * @param id
     * @return
     */
    Country get(int id);

    /**
     * 增加
     */
    int insert(Country c);

    /**
     * 删除
     */
    int delete(int id);

    /**
     * 批量插入
     */
    int insert(List<Country> datas);

    /**
     * 自由查询，万能的map，最佳伙伴是lambda
     */
    List<Map<?, ?>> queryMaps();

    /**
     * 分页查询
     */
    PageInfo<Country> getPage(int pageNum, int pageSize);

    /**
     * 更新部分属性
     */
    int update(Country c);

    /**
     * 根据单个属性查询
     */
    List<Long> queryIds();
}

package net.tfedu.zhl.cloud.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.cloud.demo.dao.CountryMapper;
import net.tfedu.zhl.cloud.demo.entity.Country;
import net.tfedu.zhl.cloud.demo.service.CountryService;

/**
 * demo，勿删
 * 
 * @author SKS
 *
 */
@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Resource
    CountryMapper countryMapper;

    @Override
    public Country get(int id) {
        return countryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Country c) {
        return countryMapper.insert(c);
    }

    @Override
    public int delete(int id) {
        return countryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新部分属性
     */
    @Override
    public int update(Country c) {
        return countryMapper.updateByPrimaryKeySelective(c);
    }

    /**
     * 批量插入
     */
    @Override
    public int insert(List<Country> datas) {
        return countryMapper.insertList(datas);
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<Country> getPage(int pageNum, int pageSize) {
        // Page插件必须放在查询语句之前紧挨的第一个位置
        PageHelper.startPage(pageNum, pageSize);
        PageHelper.orderBy("id desc");
        // 这里不能放其它语句
        List<Country> list = countryMapper.selectAll();
        // 用PageInfo对结果进行包装
        PageInfo<Country> page = new PageInfo<Country>(list);
        return page;
    }

    /**
     * 自由查询，万能的map，最佳伙伴是lambda
     */
    @Override
    public List<Map<?, ?>> queryMaps() {
        // 返回List(Map<字段名称，字段值>, Map<字段名称，字段值>,Map<字段名称，字段值>...)集合
        return countryMapper.queryMaps();
    }

    /**
     * 根据单个属性查询
     */
    @Override
    public List<Long> queryIds() {
        return countryMapper.queryIds();
    }

}

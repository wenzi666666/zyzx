package net.tfedu.zhl.cloud.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.demo.dao.CountryMapper;
import net.tfedu.zhl.cloud.demo.entity.Country;
import net.tfedu.zhl.cloud.demo.service.CountryService;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

/**
 * demo，勿删
 * 
 * @author SKS
 *
 */
@Service("countryService")
public class CountryServiceImpl extends BaseServiceImpl<Country> implements CountryService {

    @Resource
    CountryMapper countryMapper;

    /**
     * 自由查询，万能的map，最佳伙伴是lambda
     */
    @Override
    public ResultJSON queryMaps() {
        // 返回List(Map<字段名称，字段值>, Map<字段名称，字段值>,Map<字段名称，字段值>...)集合
        try {
            List<Map<?, ?>> data = countryMapper.queryMaps();
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据单个属性查询
     */
    @Override
    public ResultJSON queryIds() {
        try {
            List<Long> data = countryMapper.queryIds();
            exception = CustomException.SUCCESS;
            result = new ResultJSON(exception.getCode(), exception.getMessage(), data, "");
        } catch (Exception e) {
            exception = CustomException.UNCUSTOM;
            result = new ResultJSON(exception.getCode(), e.getMessage(), "", "");
            e.printStackTrace();
        }
        return result;
    }

}

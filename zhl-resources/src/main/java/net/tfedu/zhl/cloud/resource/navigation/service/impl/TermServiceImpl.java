package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import net.tfedu.zhl.sso.term.entity.JTerm;

/**
 * 查询所有学段 serviceImpl
 */
@Service("termService")
public class TermServiceImpl implements TermService {

    @Resource
    JTermMapper termMapper;

    /**
     * 查询所有学段
     */
    @Override
    @Cacheable(value="bussinesscache",key="'allTerms'")
    public List<JTerm> selectAll() {
    	
        return termMapper.getAllTerms();

    }
}

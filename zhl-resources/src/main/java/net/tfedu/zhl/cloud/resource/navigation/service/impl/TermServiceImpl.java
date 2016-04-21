package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import net.tfedu.zhl.sso.term.entity.JTerm;

import org.springframework.stereotype.Service;

/**
 * 查询所有学段 serviceImpl
 */
@Service("termService")
public class TermServiceImpl extends BaseServiceImpl<JTerm>implements TermService {

    @Resource
    JTermMapper termMapper;

    /**
     * 查询所有学段
     */
    @Override
    public ResultJSON selectAll() {
    	
        List<JTerm> data =  termMapper.getAllTerms();
        result = defaultSuccess(data);
        return result;
    }
}

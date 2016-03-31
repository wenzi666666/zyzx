package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.core.term.entity.JTerm;
import net.tfedu.zhl.cloud.resource.navigation.dao.TermMapper;
import net.tfedu.zhl.cloud.resource.navigation.service.TermService;
/**
 * 查询所有学段 serviceImpl
 */
@Service("termService")
public class TermServiceImpl implements TermService {

	@Resource TermMapper termMapper;
	
	/**
	 * 查询所有学段
	 */
	@Override
	public List<JTerm> selectAll(){
		return termMapper.getAllTerms();
	}
}

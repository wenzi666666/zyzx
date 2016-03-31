package net.tfedu.zhl.cloud.resource.navigation.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.navigation.dao.JSyscourseMapper;
import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;
import net.tfedu.zhl.cloud.resource.navigation.service.EditionService;

import org.springframework.stereotype.Service;

/**
 * 根据学段、学科，查询所有教材版本
 * @author WeiCuicui
 *
 */
@Service("editionService")
public class EditionServiceImpl implements EditionService {
	
	@Resource JSyscourseMapper jSyscourseMapper;

	//参数 map中存放 termId，subjectId
	@Override
	public List<JSyscourse> getAllEditionsByTermAndSub(HashMap<String, Object> map){
		return jSyscourseMapper.getAllEditions(map);
	}
	
}

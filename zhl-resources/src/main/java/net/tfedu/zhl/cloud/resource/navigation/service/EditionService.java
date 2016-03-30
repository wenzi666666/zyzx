package net.tfedu.zhl.cloud.resource.navigation.service;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.cloud.resource.navigation.entity.JSyscourse;

/**
 * 根据学段、学科获得所有教材版本
 * @author WeiCuicui
 *
 */
public interface EditionService {

	/*
	 * 根据学段、学科，获得所有教材版本
	 * 参数 map中存放 termId，subjectId
	 */
	public List<JSyscourse> getAllEditionsByTermAndSub(HashMap<String, Object> map);
}

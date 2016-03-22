package net.tfedu.zhl.cloud.resources.navigation.dao;

import java.util.HashMap;
import java.util.List;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.cloud.resources.navigation.entity.JSyscourse;
/**
 * 根据学段、学科，获得所有教材版本
 * @author WeiCuicui
 *
 */
public interface JSyscourseMapper extends CoreMapper<JSyscourse> {
	
	//根据学段、学科，获得所有教材版本
	public List<JSyscourse> getAllEditions(HashMap<String, Long> map);
	
	//获得特定版本下的所有教材
	public List<JSyscourse> getAllBooks(long pnodeId);
}
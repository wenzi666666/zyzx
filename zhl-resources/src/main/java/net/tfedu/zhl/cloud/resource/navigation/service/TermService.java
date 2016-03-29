package net.tfedu.zhl.cloud.resource.navigation.service;

import java.util.List;

import net.tfedu.zhl.cloud.core.term.entity.JTerm;


/**
 *  查询所有学段 service
 */
public interface TermService {

	//查询所有学段
	public List<JTerm> selectAll();
}

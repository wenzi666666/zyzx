package net.tfedu.zhl.cloud.core.term.dao;

import net.tfedu.zhl.cloud.core.term.entity.JUserTerm;
import net.tfedu.zhl.helper.CoreMapper;

public interface JUserTermMapper extends CoreMapper<JUserTerm> {
	
	public void updateUserTerm(Long userId,Long termId);
}
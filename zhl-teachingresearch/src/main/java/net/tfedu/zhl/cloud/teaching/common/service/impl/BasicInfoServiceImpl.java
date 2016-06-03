package net.tfedu.zhl.cloud.teaching.common.service.impl;

import net.tfedu.zhl.cloud.teaching.common.service.BasicInfoService;
import net.tfedu.zhl.cloud.teaching.term.entity.Term;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Service;


@Service("basicInfoServiceImpl")
public class BasicInfoServiceImpl extends BaseServiceImpl<Term> implements BasicInfoService {
	
	

	
	@Override
	public ResultJSON getAllTerm() throws Exception {
		return selectAll();
	}

	
	
	
	
	

}

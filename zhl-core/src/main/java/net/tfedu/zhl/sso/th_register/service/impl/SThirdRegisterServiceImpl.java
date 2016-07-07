package net.tfedu.zhl.sso.th_register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.th_register.dao.SThirdRegisterRelativeMapper;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.th_register.service.SThirdRegisterService;

@Service("sThirdRegisterService")
public class SThirdRegisterServiceImpl extends BaseServiceImpl<SThirdRegisterRelative> implements SThirdRegisterService {

	
	@Autowired
	SThirdRegisterRelativeMapper mapper;
	
	
	
	@Override
	public SThirdRegisterRelative getThirdRelativeResult(String userName, String thirdCode) throws Exception {
		return mapper.getThirdRelativeResult(userName, thirdCode);
	}

	
	
	
}

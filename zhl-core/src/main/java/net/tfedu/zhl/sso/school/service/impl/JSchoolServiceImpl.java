package net.tfedu.zhl.sso.school.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.sso.school.dao.JSchoolMapper;
import net.tfedu.zhl.sso.school.entity.JSchool;
import net.tfedu.zhl.sso.school.service.JSchoolService;

@Service("schoolService")
public class JSchoolServiceImpl extends BaseServiceImpl<JSchool> implements JSchoolService {

	@Autowired
	JSchoolMapper mapper;

	@Override
	public List<JSchool> getSchoolsByDistrictId(Long districtId) {

		// Example example = new Example(JSchool.class);
		//
		// example.createCriteria().andCondition("flag =
		// false").andCondition("districtid=",districtId);
		//
		// return mapper.selectByExample(example);
		//

		JSchool sch = new JSchool();
		sch.setFlag(false);
		sch.setDistrictid(String.valueOf(districtId));

		return mapper.select(sch);
	}

}

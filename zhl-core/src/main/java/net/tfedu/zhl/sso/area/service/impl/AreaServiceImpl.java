package net.tfedu.zhl.sso.area.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.area.service.AreaService;
import net.tfedu.zhl.userlayer.city.dao.CityMapper;
import net.tfedu.zhl.userlayer.city.entity.City;
import net.tfedu.zhl.userlayer.district.dao.DistrictMapper;
import net.tfedu.zhl.userlayer.district.entity.District;
import net.tfedu.zhl.userlayer.grade.dao.GradeMapper;
import net.tfedu.zhl.userlayer.grade.entity.Grade;
import net.tfedu.zhl.userlayer.province.dao.ProvinceMapper;
import net.tfedu.zhl.userlayer.province.entity.Province;
import net.tfedu.zhl.userlayer.school.dao.JSchoolMapper;
import net.tfedu.zhl.userlayer.school.entity.JSchool;


@Service("areaServiceImpl")
public class AreaServiceImpl implements AreaService {
	@Resource
	ProvinceMapper provinceMapper;
	@Resource
	CityMapper cityMapper;
	@Resource
	DistrictMapper districtMapper;
	@Resource
	JSchoolMapper jSchoolMapper;
	
	@Resource
	GradeMapper gradeMapper;

	
	

	@Override
	public ResultJSON queryProvince() {
		List<Province> provinces = provinceMapper.queryProvince();
		return ResultJSON.getSuccess(provinces);
	}

	@Override
	public ResultJSON queryCityByProvinceId(long provinceId) {
		List<City> cities = cityMapper.queryCityByProvinceId(provinceId);
		return ResultJSON.getSuccess(cities);
	}

	@Override
	public ResultJSON queryDistirctByCityId(long cityId) {
		List<District> districts = districtMapper.queryDistirctByCityId(cityId);
		return ResultJSON.getSuccess(districts);
	}

	@Override
	public ResultJSON querySchoolByDistrictId(long districtId) {
		List<JSchool> schools = jSchoolMapper
				.querySchoolByDistrictId(districtId);
		return ResultJSON.getSuccess(schools);
	}

	@Override
	public ResultJSON queryClassBySchoolId(long schoolId) {
		// TODO Auto-generated method stub
		List<Grade> list = gradeMapper.queryGradeBySchoolId(schoolId);
		return ResultJSON.getSuccess(list);
	}

}

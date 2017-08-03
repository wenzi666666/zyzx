package net.tfedu.zhl.sso.area.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.area.service.AreaService;
import net.tfedu.zhl.sso.city.dao.CityMapper;
import net.tfedu.zhl.sso.city.entity.City;
import net.tfedu.zhl.sso.district.dao.DistrictMapper;
import net.tfedu.zhl.sso.district.entity.District;
import net.tfedu.zhl.sso.grade.dao.GradeMapper;
import net.tfedu.zhl.sso.grade.entity.Grade;
import net.tfedu.zhl.sso.province.dao.ProvinceMapper;
import net.tfedu.zhl.sso.province.entity.Province;
import net.tfedu.zhl.sso.school.dao.JSchoolMapper;
import net.tfedu.zhl.sso.school.entity.JSchool;

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
		List<JSchool> schools = jSchoolMapper.querySchoolByDistrictId(districtId);
		return ResultJSON.getSuccess(schools);
	}

	@Override
	public ResultJSON queryClassBySchoolId(long schoolId) {
		List<Grade> list = gradeMapper.queryGradeBySchoolId(schoolId);
		return ResultJSON.getSuccess(list);
	}

	@Override
	public Long syncCloudSchoolInfo(String provinceId, String provinceName, String cityId, String cityName,
			String districtId, String districtName, String schoolId, String schoolName) {

		Province p = provinceMapper.selectByPrimaryKey(Long.parseLong(provinceId));
		if (null == p) {

			Province record = new Province();
			record.setId(Long.parseLong(provinceId));
			record.setName(provinceName);
			record.setFlag(false);
			record.setCode("");
			provinceMapper.insert(record);
		} else {

			if (!provinceName.equals(p.getName())) {
				p.setName(provinceName);
				provinceMapper.updateByPrimaryKey(p);
			}

		}

		City c = cityMapper.selectByPrimaryKey(Integer.parseInt(cityId));

		if (null == c) {
			City record = new City();
			record.setFlag(false);
			record.setId(Integer.parseInt(cityId));
			record.setProvinceid(Integer.parseInt(provinceId));
			record.setName(cityName);
			cityMapper.insert(record);
		} else {
			if (!cityName.equals(c.getName()) || Integer.parseInt(provinceId) != c.getProvinceid()) {
				c.setProvinceid(Integer.parseInt(provinceId));
				c.setName(cityName);
				cityMapper.updateByPrimaryKey(c);
			}
		}

		District d = districtMapper.selectByPrimaryKey(Integer.parseInt(districtId));
		if (null == d) {

			District record = new District();
			record.setFlag(false);
			record.setId(Integer.parseInt(districtId));
			record.setCityid(cityId);
			record.setName(districtName);
			districtMapper.insert(record);

		} else {
			if (!districtName.equals(d.getName()) || !cityId.equals(d.getCityid())) {
				d.setCityid(cityId);
				d.setName(districtName);
				districtMapper.updateByPrimaryKey(d);
			}

		}

		JSchool s = jSchoolMapper.selectByPrimaryKey(Long.parseLong(schoolId));
		if (null == s) {
			s = new JSchool();
			s.setFlag(false);
			s.setDistrictid(districtId);
			s.setName(schoolName);
			s.setCreatedate(Calendar.getInstance().getTime());
			s.setId(Long.parseLong(schoolId));
			jSchoolMapper.insert(s);
		} else {
			if (!s.getName().equals(schoolName) || !s.getDistrictid().equals(districtId)) {
				s.setName(schoolName);
				s.setDistrictid(districtId);
				jSchoolMapper.updateByPrimaryKey(s);
			}

		}

		return Long.parseLong(schoolId);
	}

}

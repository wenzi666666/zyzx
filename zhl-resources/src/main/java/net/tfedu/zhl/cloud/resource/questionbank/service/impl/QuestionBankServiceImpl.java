package net.tfedu.zhl.cloud.resource.questionbank.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.questionbank.service.QuestionBankService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.city.dao.CityMapper;
import net.tfedu.zhl.sso.city.entity.City;
import net.tfedu.zhl.sso.district.dao.DistrictMapper;
import net.tfedu.zhl.sso.district.entity.District;
import net.tfedu.zhl.sso.province.dao.ProvinceMapper;
import net.tfedu.zhl.sso.province.entity.Province;
import net.tfedu.zhl.sso.school.dao.JSchoolMapper;
import net.tfedu.zhl.sso.school.entity.JSchool;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.UsersEntity;

import org.springframework.stereotype.Service;

/**
 * 题库对接的service
 * @author WeiCuicui
 *
 */
@Service("questionBankService")
public class QuestionBankServiceImpl implements QuestionBankService{

	@Resource JUserMapper jUserMapper;
	@Resource ProvinceMapper provinceMapper;
	@Resource CityMapper cityMapper;
	@Resource DistrictMapper districtMapper;
	@Resource JSchoolMapper jSchoolMapper;
	@Resource JTermMapper jTermMapper;
	
	/**
	 * 根据用户名，查询用户信息
	 * @param userName
	 * @return
	 */
	@Override
	public ResultJSON queryUserBasicInfo(String userName){
		
		List<UsersEntity> users = jUserMapper.queryUserBasicInfo(userName);
		return ResultJSON.getSuccess(users);
	}
	
	/**
	 * 查询所有省份
	 * @return
	 */
	@Override
	public ResultJSON queryProvince(){
		List<Province> provinces = provinceMapper.queryProvince();
		return ResultJSON.getSuccess(provinces);
	}
	
	/**
	 * 查询省份下的所有市
	 * @param provinceId
	 * @return
	 */
	@Override
	public ResultJSON queryCityByProvinceId(long provinceId){
		List<City> cities = cityMapper.queryCityByProvinceId(provinceId);
		return ResultJSON.getSuccess(cities);
	}
	
	/**
	 * 根据所在市，查询所有区
	 * @return
	 */
	@Override
	public ResultJSON queryDistirctByCityId(int cityId){
		List<District> districts = districtMapper.queryDistirctByCityId(cityId);
		return ResultJSON.getSuccess(districts);
	}
	
	/**
	 * 根据所在区，查询所有学校
	 * @param districtIds
	 * @return
	 */
	@Override
	public ResultJSON querySchoolByDistrictId(int districtId){
		List<JSchool> schools = jSchoolMapper.querySchoolByDistrictId(districtId);
		return ResultJSON.getSuccess(schools);
	}
	
	/**
	 * 查询所有学段信息
	 * @return
	 */
	@Override
	public ResultJSON queryTerm(){
		List<JTerm> terms = jTermMapper.queryTerm();
		return ResultJSON.getSuccess(terms);
	}
	
	/**
	 * 查询学段下的学科列表
	 * @param termId
	 * @return
	 */
	@Override
	public ResultJSON querySubjectByTermId(long termId){
	    List<JSubject> subjects = jTermMapper.querySubjectByTermId(termId);
	    return ResultJSON.getSuccess(subjects);
	}
}

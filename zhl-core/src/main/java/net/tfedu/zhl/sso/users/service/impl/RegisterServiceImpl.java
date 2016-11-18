package net.tfedu.zhl.sso.users.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.core.exception.OutOfDateException;
import net.tfedu.zhl.core.exception.WithoutUserException;
import net.tfedu.zhl.core.exception.WrongPassWordException;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.city.dao.CityMapper;
import net.tfedu.zhl.sso.city.entity.City;
import net.tfedu.zhl.sso.district.dao.DistrictMapper;
import net.tfedu.zhl.sso.district.entity.District;
import net.tfedu.zhl.sso.province.dao.ProvinceMapper;
import net.tfedu.zhl.sso.province.entity.Province;
import net.tfedu.zhl.sso.school.dao.JSchoolMapper;
import net.tfedu.zhl.sso.school.entity.JSchool;
import net.tfedu.zhl.sso.subject.dao.JSubjectMapper;
import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.subject.entity.JSubject;
import net.tfedu.zhl.sso.subject.entity.JTeacherSubject;
import net.tfedu.zhl.sso.term.dao.JTermMapper;
import net.tfedu.zhl.sso.term.dao.JUserTermMapper;
import net.tfedu.zhl.sso.term.entity.JTerm;
import net.tfedu.zhl.sso.term.entity.JUserTerm;
import net.tfedu.zhl.sso.th_register.dao.SThirdRegisterRelativeMapper;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.thirdpartyrelation.dao.STPRelationMapper;
import net.tfedu.zhl.sso.thirdpartyrelation.entity.STPRelation;
import net.tfedu.zhl.sso.user.dao.JUserMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.userinfo.dao.JUserInfoMapper;
import net.tfedu.zhl.sso.userinfo.entity.JUserInfo;
import net.tfedu.zhl.sso.users.dao.SBatchMapper;
import net.tfedu.zhl.sso.users.dao.SCardMapper;
import net.tfedu.zhl.sso.users.dao.SRegisterMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SBatch;
import net.tfedu.zhl.sso.users.entity.SCard;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.service.RegisterService;

/**
 * 注册表service
 * 
 * @author wangwr
 * 
 */
@Service("registerService")
@Transactional
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	SRegisterMapper rMapper;

	@Autowired
	SCardMapper cardMapper;

	@Autowired
	SBatchMapper batchMapper;

	@Autowired
	JUserMapper userMapper;

	@Autowired
	JUserTermMapper userTermMapper;

	@Autowired
	JTeacherSubjectMapper teachSubjectMapper;

	@Autowired
	JUserInfoMapper userInfoMapper;

	@Autowired
	ProvinceMapper proMapper;

	@Autowired
	CityMapper cityMapper;

	@Autowired
	DistrictMapper disMapper;

	@Autowired
	JSchoolMapper schMapper;

	@Autowired
	JTermMapper termMapper;

	@Autowired
	JSubjectMapper subjectMapper;

	/**
	 * 第三方对接时name的映射表
	 */
	@Autowired
	SThirdRegisterRelativeMapper relativeMapper;
	
	/**
	 * 第三方对接时id的映射表
	 */
	@Autowired
	STPRelationMapper   tpIdRelationMapper;

	/**
	 * id获取注册用户
	 */
	public SRegister getRegister(Long id) {
		SRegister r = new SRegister();
		r.setId(id);
		return rMapper.selectOne(r);
	}

	/**
	 * name获取注册用户
	 */
	public SRegister getRegister(String userName) {
		SRegister r = new SRegister();
		r.setName(userName);
		return rMapper.selectOne(r);
	}

	/**
	 * 修改用户密码
	 * 
	 * @param userId
	 * @param password
	 */
	public void modifyRegisterPassword(Long userId, String password) {
		SRegister r = new SRegister();
		r.setId(userId);
		byte[] pwd = PWDEncrypt.doEncryptByte(password);
		r.setPwd(pwd);
		rMapper.modifyPassword(userId, pwd);
	}

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public SRegister login(String userName, String password) throws Exception {

		byte[] pwd = PWDEncrypt.doEncryptByte(password);
		SRegister r = new SRegister();
		r.setName(userName);
		r = rMapper.selectOne(r);
		if (r != null) {
			// 存在 检测是密码正确
			if (!Arrays.equals(pwd, r.getPwd())) {
				throw new WrongPassWordException();
			}
			// 存在 检测是否过期
			Date endTime = r.getReendtime();
			Calendar c = Calendar.getInstance();
			// 如果之前已经过期
			if (endTime.before(c.getTime())) {
				throw new OutOfDateException();
			}
		} else {
			// 用户不存在
			throw new WithoutUserException();
		}
		return r;
	}

	@Override
	public SRegister addRegister(RegisterAddForm form) throws Exception {
		String userName = form.getUserName();
		String trueName = form.getTrueName();
		String nickName = StringUtils.isEmpty(form.getNickName()) ? trueName : form.getNickName();
		boolean sex = form.isSex();
		long role = form.getRole();
		String motto = form.getMotto() == null || "".equals(form.getMotto().trim()) ? "" : form.getMotto().trim();
		String birthDate = form.getBirthDate();
		String provinceName = form.getProvinceName();
		String cityName = form.getCityName();
		String arealName = form.getArealName();
		String schoolName = form.getSchoolName();
		String termName = form.getTermName();
		String subjectName = form.getSubjectName();

		long provinceId = 0;
		long cityId = 0;
		long districtId = 0;
		long schoolId = 0;
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 机构信息处理
		List<Province> proList = proMapper.queryProvinceByName(provinceName);
		if (proList == null || 0 == proList.size()) {
			Province record = new Province();
			record.setName(provinceName);
			record.setFlag(false);
			proMapper.insertSelective(record);
			provinceId = record.getId();
		} else {
			provinceId = proList.get(0).getId();
		}

		List<City> cityList = cityMapper.queryCityByProvinceIdANDName(provinceId, cityName);
		if (cityList == null || 0 == cityList.size()) {
			City record = new City();
			record.setName(cityName);
			record.setProvinceid(Integer.parseInt(provinceId + ""));
			record.setFlag(false);
			cityMapper.insertSelective(record);
			cityId = record.getId();
		} else {
			cityId = cityList.get(0).getId();
		}

		List<District> disList = disMapper.queryDistirctByCityIdANDName(cityId, arealName);
		if (disList == null || 0 == disList.size()) {
			District record = new District();
			record.setName(arealName);
			record.setCityid(String.valueOf(cityId));
			record.setFlag(false);
			disMapper.insertSelective(record);
			districtId = record.getId();
		} else {
			districtId = disList.get(0).getId();
		}

		List<JSchool> schList = schMapper.querySchoolByDistrictIdAndName(districtId, schoolName);
		if (schList == null || 0 == schList.size()) {
			JSchool record = new JSchool();
			record.setName(schoolName);
			record.setCreatedate(date);
			record.setDistrictid(String.valueOf(districtId));
			schMapper.insertSelective(record);
			schoolId = record.getId();
		} else {
			schoolId = schList.get(0).getId();
		}

		//
		SRegister register = new SRegister();
		// 卡有效期
		int expNum = 36;

		SBatch ben = new SBatch();
		String batchName = "PT" + format.format(date) + "_" + userName + "_" + 1;
		ben.setBatchname(batchName);
		ben.setNumbercard(1);
		ben.setCreatetime(date);
		ben.setFlag(false);
		batchMapper.insertSelective(ben);// 增加批次

		SCard card = new SCard();
		card.setRoleid(role);
		card.setRegistkeytype("P");
		card.setExpNum(expNum);
		card.setBatchid(ben.getId());
		card.setCreatetime(date);
		card.setState(0);
		card.setFlag(false);

		String password = "";
		String cardNo = "";
		String prefix = "" + role;

		boolean flag = true;
		while (flag) {
			cardNo = prefix + (int) (Math.random() * 100000000.0D + 900000000.0D);
			password = "" + (int) (Math.random() * 100000.0D + 900000.0D);
			SCard _card = new SCard();
			_card.setCardnumber(cardNo);
			int cont = cardMapper.selectCount(_card);
			if (0 == cont) {
				flag = false;
			}
		}

		card.setCardnumber(cardNo);
		card.setCardpwd(password);
		card.setFlag(false);

		cardMapper.insertSelective(card);// 增加卡号

		SRegister s = new SRegister();
		s.setCardid(card.getId());
		s.setFlag(false);
		s.setEmail("");
		s.setName(userName);
		s.setNodeid(1);
		s.setRegistertime(date);
		s.setRoleid(role);
		s.setPwd(PWDEncrypt.doEncryptByte("tfedu000000"));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONDAY, expNum);
		s.setReendtime(c.getTime());// 设置最后的有效期

		// 增加注册信息
		rMapper.addRegister(s);

		JUser user = new JUser();
		user.setId(s.getId());
		user.setName(userName);
		user.setTruename(trueName);
		user.setNickname(nickName);
		user.setFlag(false);
		user.setIsfirstlogin(false);
		user.setMale(sex);
		user.setRoleid(String.valueOf(role));
		user.setStatus(0);
		user.setSchoolid(schoolId);

		userMapper.insertSelective(user);

		// userInfoMapper
		JUserInfo userinfo = new JUserInfo();
		userinfo.setUserid(user.getId());
		if (StringUtils.isNotEmpty(birthDate)) {
			userinfo.setBirthdate(format.parse(birthDate));
		}
		userinfo.setResume(motto);
		userinfo.setFlag(false);
		userInfoMapper.insertSelective(userinfo);

		// 学段 学科
		JTerm term = new JTerm();
		term.setName(termName);
		term = termMapper.selectOne(term);

		if (term != null) {
			JUserTerm userTerm = new JUserTerm();
			userTerm.setUserid(user.getId());
			userTerm.setTermid(term.getId());
			userTermMapper.insertSelective(userTerm);
		}

		JSubject subject = new JSubject();
		subject.setName(subjectName);
		subject = subjectMapper.selectOne(subject);

		if (subject != null) {
			JTeacherSubject ts = new JTeacherSubject();
			ts.setUserid(user.getId());
			ts.setSubjectid(subject.getId());
			teachSubjectMapper.insertSelective(ts);
		}

		return s;
	}

	@Override
	public SRegister addRegister(RegisterAddForm form, String userName, String platformcode) throws Exception {

		// 注册
		SRegister s = addRegister(form);
		// 增加映射关系
		SThirdRegisterRelative record = new SThirdRegisterRelative();
		record.setThCode(platformcode);
		record.setThUsername(userName);
		record.setZhlUsername(s.getName());
		relativeMapper.insertSelective(record);

		return s;
	}

	@Override
	public void registerOrUpdateUserWithThirdPartyApp(RegisterAddForm form, SApp app) throws Exception {

		// 第三方编码为app中的前缀
		String thirdCode = app.getPrefix();

		// 当前username是否已经映射过
		SThirdRegisterRelative relative = relativeMapper.getThirdRelativeResult(form.getUserName(), thirdCode);

		// 没有则添加用户
		SRegister register = null;
		if (relative == null || relative.getId() == 0) {
			// 注册时，增加前缀
			String zhl_username = thirdCode + "_" + form.getUserName();

			// 是否已经注册了
			Long _tempId = rMapper.getRegisterIdByName(zhl_username);

			int i = 1;
			while (_tempId != null && _tempId > 0) {
				zhl_username = zhl_username + "" + i;
				i++;
				_tempId = rMapper.getRegisterIdByName(zhl_username);
			}

			
			
			//返回学校id
			long schoolId = getSchoolId(form);
			
			long userId = doAddRegister(zhl_username, form, app, schoolId);
			
			
			// 增加name映射关系
			SThirdRegisterRelative record = new SThirdRegisterRelative();
			record.setThCode(thirdCode);
			record.setThUsername(form.getUserName());
			record.setZhlUsername(zhl_username);
			relativeMapper.insertSelective(record);
			
			
			// 增加id映射关系
			STPRelation stp = new STPRelation();
			stp.setAppid(app.getAppid());
			stp.setCreatetime(Calendar.getInstance().getTime());
			stp.setFlag(false);
			stp.setTpid(form.getTh_uuid());
			stp.setZhlid(userId);
			stp.setOperationtype(STPRelation.OPERATION_TYPE_REGISTER);
			tpIdRelationMapper.insert(stp);
			
		}else{
			
			//返回学校id
			long schoolId = getSchoolId(form);
			
			//更新用户真实姓名等信息
			JUser user = userMapper.getUserByName(relative.getZhlUsername());
			if(!form.getTrueName().equals(user.getTruename())
					|| !form.getNickName().equals(user.getNickname())
					|| !user.getRoleid().equals(String.valueOf(form.getRole()))
					|| form.isSex() == user.getMale()
					|| schoolId!= user.getSchoolid()
					){
				long userId =user.getId();
				user = new JUser();
				user.setId(userId);
				user.setTruename(form.getTrueName());
				user.setNickname(form.getNickName());
				user.setMale(form.isSex());
				user.setRoleid(String.valueOf(form.getRole()));
				user.setSchoolid(schoolId);
			}
			
			userMapper.updateByPrimaryKeySelective(user);
			
		}

	}
	
	
	
	/**
	 * 执行注册
	 * @param zhl_username 注册以此为用户名
	 * @param form
	 * @param app
	 * @return
	 * @throws ParseException 
	 */
	protected Long doAddRegister(String zhl_username,RegisterAddForm form,SApp app,long schoolId) throws ParseException {
		
		// 卡有效期
		int expNum = app.getUsefullife();
		if(expNum==0){
			expNum = 36;
		}
		
		
		//默认密码
		String default_pwd = app.getUserdefaultpwd();
		
		
		
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		

		
		String  userName = zhl_username;
		String trueName = form.getTrueName();
		String nickName = StringUtils.isEmpty(form.getNickName()) ? trueName : form.getNickName();
		boolean sex = form.isSex();
		long role = form.getRole();
		String motto = form.getMotto() == null || "".equals(form.getMotto().trim()) ? "" : form.getMotto().trim();
		String birthDate = form.getBirthDate();
		String termName = form.getTermName();
		String subjectName = form.getSubjectName();

		
		//
		SRegister register = new SRegister();
		

		SBatch ben = new SBatch();
		String batchName = "PT" + format.format(date) + "_" + userName + "_" + 1;
		ben.setBatchname(batchName);
		ben.setNumbercard(1);
		ben.setCreatetime(date);
		ben.setFlag(false);
		batchMapper.insertSelective(ben);// 增加批次

		SCard card = new SCard();
		card.setRoleid(role);
		card.setRegistkeytype("P");
		card.setExpNum(expNum);
		card.setBatchid(ben.getId());
		card.setCreatetime(date);
		card.setState(0);
		card.setFlag(false);

		String password = "";
		String cardNo = "";
		String prefix = "" + role;

		boolean flag = true;
		while (flag) {
			cardNo = prefix + (int) (Math.random() * 100000000.0D + 900000000.0D);
			password = "" + (int) (Math.random() * 100000.0D + 900000.0D);
			SCard _card = new SCard();
			_card.setCardnumber(cardNo);
			int cont = cardMapper.selectCount(_card);
			if (0 == cont) {
				flag = false;
			}
		}

		card.setCardnumber(cardNo);
		card.setCardpwd(password);
		card.setFlag(false);

		cardMapper.insertSelective(card);// 增加卡号

		SRegister s = new SRegister();
		s.setCardid(card.getId());
		s.setFlag(false);
		s.setEmail("");
		s.setName(userName);
		s.setNodeid(1);
		s.setRegistertime(date);
		s.setRoleid(role);
		s.setPwd(PWDEncrypt.doEncryptByte("tfedu000000"));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONDAY, expNum);
		s.setReendtime(c.getTime());// 设置最后的有效期

		// 增加注册信息
		rMapper.addRegister(s);

		JUser user = new JUser();
		user.setId(s.getId());
		user.setName(userName);
		user.setTruename(trueName);
		user.setNickname(nickName);
		user.setFlag(false);
		user.setIsfirstlogin(false);
		user.setMale(sex);
		user.setRoleid(String.valueOf(role));
		user.setStatus(0);
		user.setSchoolid(schoolId);

		userMapper.insertSelective(user);

		// userInfoMapper
		JUserInfo userinfo = new JUserInfo();
		userinfo.setUserid(user.getId());
		if (StringUtils.isNotEmpty(birthDate)) {
			userinfo.setBirthdate(format.parse(birthDate));
		}
		userinfo.setResume(motto);
		userinfo.setFlag(false);
		userInfoMapper.insertSelective(userinfo);

		// 学段 学科
		JTerm term = new JTerm();
		term.setName(termName);
		term = termMapper.selectOne(term);

		if (term != null) {
			JUserTerm userTerm = new JUserTerm();
			userTerm.setUserid(user.getId());
			userTerm.setTermid(term.getId());
			userTermMapper.insertSelective(userTerm);
		}

		JSubject subject = new JSubject();
		subject.setName(subjectName);
		subject = subjectMapper.selectOne(subject);

		if (subject != null) {
			JTeacherSubject ts = new JTeacherSubject();
			ts.setUserid(user.getId());
			ts.setSubjectid(subject.getId());
			teachSubjectMapper.insertSelective(ts);
		}

		
		
		return user.getId();
	}
	
	
	
	
	
	
	/**
	 * 返回可以使用的schoolId
	 * @param form
	 * @return
	 */
	protected Long getSchoolId(RegisterAddForm form) {
		
		String provinceName = form.getProvinceName();
		String cityName = form.getCityName();
		String arealName = form.getArealName();
		String schoolName = form.getSchoolName();
		
		long provinceId = 0;
		long cityId = 0;
		long districtId = 0;
		long schoolId = 0;

		// 机构信息处理
		List<Province> proList = proMapper.queryProvinceByName(provinceName);
		if (proList == null || 0 == proList.size()) {
			Province record = new Province();
			record.setName(provinceName);
			record.setFlag(false);
			proMapper.insertSelective(record);
			provinceId = record.getId();
		} else {
			provinceId = proList.get(0).getId();
		}

		List<City> cityList = cityMapper.queryCityByProvinceIdANDName(provinceId, cityName);
		if (cityList == null || 0 == cityList.size()) {
			City record = new City();
			record.setName(cityName);
			record.setProvinceid(Integer.parseInt(provinceId + ""));
			record.setFlag(false);
			cityMapper.insertSelective(record);
			cityId = record.getId();
		} else {
			cityId = cityList.get(0).getId();
		}

		List<District> disList = disMapper.queryDistirctByCityIdANDName(cityId, arealName);
		if (disList == null || 0 == disList.size()) {
			District record = new District();
			record.setName(arealName);
			record.setCityid(String.valueOf(cityId));
			record.setFlag(false);
			disMapper.insertSelective(record);
			districtId = record.getId();
		} else {
			districtId = disList.get(0).getId();
		}

		List<JSchool> schList = schMapper.querySchoolByDistrictIdAndName(districtId, schoolName);
		if (schList == null || 0 == schList.size()) {
			Date date = Calendar.getInstance().getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			JSchool record = new JSchool();
			record.setName(schoolName);
			record.setCreatedate(date);
			record.setDistrictid(String.valueOf(districtId));
			schMapper.insertSelective(record);
			schoolId = record.getId();
		} else {
			schoolId = schList.get(0).getId();
		}
		
		return schoolId;
	}
	
	
	
}

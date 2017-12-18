package net.tfedu.zhl.sso.users.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.core.exception.OutOfDateException;
import net.tfedu.zhl.core.exception.RegisterCardError;
import net.tfedu.zhl.core.exception.WithoutUserException;
import net.tfedu.zhl.core.exception.WrongPassWordException;
import net.tfedu.zhl.core.exception.euam.RegisterCardErrorInfoEuam;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.th_register.dao.SThirdRegisterRelativeMapper;
import net.tfedu.zhl.sso.th_register.entity.SThirdRegisterRelative;
import net.tfedu.zhl.sso.thirdpartyrelation.dao.STPRelationMapper;
import net.tfedu.zhl.sso.thirdpartyrelation.entity.STPRelation;
import net.tfedu.zhl.sso.users.dao.SBatchMapper;
import net.tfedu.zhl.sso.users.dao.SCardMapper;
import net.tfedu.zhl.sso.users.dao.SRegisterMapper;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SBatch;
import net.tfedu.zhl.sso.users.entity.SCard;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.module.AccountRegisterWebForm;
import net.tfedu.zhl.sso.users.service.RegisterService;
import net.tfedu.zhl.sso.users.util.CardExcelForm;
import net.tfedu.zhl.userlayer.city.dao.CityMapper;
import net.tfedu.zhl.userlayer.city.entity.City;
import net.tfedu.zhl.userlayer.district.dao.DistrictMapper;
import net.tfedu.zhl.userlayer.district.entity.District;
import net.tfedu.zhl.userlayer.province.dao.ProvinceMapper;
import net.tfedu.zhl.userlayer.province.entity.Province;
import net.tfedu.zhl.userlayer.school.dao.JSchoolMapper;
import net.tfedu.zhl.userlayer.school.entity.JSchool;
import net.tfedu.zhl.userlayer.subject.dao.JSubjectMapper;
import net.tfedu.zhl.userlayer.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.userlayer.subject.entity.JSubject;
import net.tfedu.zhl.userlayer.subject.entity.JTeacherSubject;
import net.tfedu.zhl.userlayer.term.dao.JTermMapper;
import net.tfedu.zhl.userlayer.term.dao.JUserTermMapper;
import net.tfedu.zhl.userlayer.term.entity.JTerm;
import net.tfedu.zhl.userlayer.term.entity.JUserTerm;
import net.tfedu.zhl.userlayer.user.dao.JUserMapper;
import net.tfedu.zhl.userlayer.user.entity.JUser;
import net.tfedu.zhl.userlayer.userinfo.dao.JUserInfoMapper;
import net.tfedu.zhl.userlayer.userinfo.entity.JUserInfo;
import tk.mybatis.mapper.entity.Example;

/**
 * 注册表service
 * 
 * @author wangwr
 * 
 */
@Transactional(value="ssoTransactionManager")
@Service("registerService")
public class RegisterServiceImpl extends BaseServiceImpl<SRegister> implements RegisterService {

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
		s.setNodeid(null != form.getNodeId() && form.getNodeId()>0 ?form.getNodeId().intValue():1);
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
	public Long registerOrUpdateUserWithThirdPartyApp(RegisterAddForm form, SApp app) throws Exception {
		

		// 第三方编码为app中的前缀
		String thirdCode = app.getPrefix();

		// 当前username是否已经映射过
		SThirdRegisterRelative relative = relativeMapper.getThirdRelativeResult(form.getUserName(), thirdCode);

		if (relative == null || relative.getId() == 0) {
			// 注册时，增加前缀
			String zhl_username = thirdCode + "_" + form.getUserName();
			form.setUserName(zhl_username);

			// 是否已经注册了
			Long _tempId = rMapper.getRegisterIdByName(zhl_username);


			if(null !=  _tempId && _tempId > 0){
				return _tempId;
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
			
			
			form.setUserName(zhl_username);
			
			
			return userId;
		}else{
			
			//返回学校id
			long schoolId = getSchoolId(form);
			
			SRegister s =  rMapper.selectByPrimaryKey(Long.parseLong(relative.getZhlUserid()));
			
			//如果用户已经过期了 
			if(s.getReendtime().before(Calendar.getInstance().getTime())){
				throw new OutOfDateException();
			}
			
			if(null != form.getNodeId() && form.getNodeId() > 0 ){
				
				if(s.getNodeid().longValue() != form.getNodeId().longValue()){
					SRegister temp = new SRegister();
					temp.setId(s.getId());
					temp.setNodeid(form.getNodeId().intValue());
					rMapper.updateByPrimaryKeySelective(temp);
				}
			}
			
			//更新用户真实姓名等信息
			JUser user = userMapper.getUserByName(relative.getZhlUsername());
			
			//如果没有用户记录表
			if(null == user ){
				
				user = new JUser();
				user.setId(Long.parseLong(relative.getZhlUserid()));
				user.setName(relative.getZhlUsername());
				user.setTruename(form.getTrueName());
				user.setNickname(form.getNickName());
				user.setFlag(false);
				user.setIsfirstlogin(false);
				user.setMale(form.isSex());
				user.setRoleid(String.valueOf(form.getRole()));
				user.setStatus(0);
				user.setSchoolid(schoolId);

				userMapper.insertSelective(user);

				// userInfoMapper
				JUserInfo userinfo = new JUserInfo();
				userinfo.setUserid(user.getId());
				if (StringUtils.isNotEmpty(form.getBirthDate())) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					userinfo.setBirthdate(format.parse(form.getBirthDate()));
				}
				userinfo.setResume(form.getMotto());
				userinfo.setFlag(false);
				userInfoMapper.insertSelective(userinfo);

				// 学段 学科
				JTerm term = new JTerm();
				term.setName(form.getTermName());
				term = termMapper.selectOne(term);

				if (term != null) {
					JUserTerm userTerm = new JUserTerm();
					userTerm.setUserid(user.getId());
					userTerm.setTermid(term.getId());
					userTermMapper.insertSelective(userTerm);
				}

				JSubject subject = new JSubject();
				subject.setName(form.getSubjectName());
				subject = subjectMapper.selectOne(subject);

				if (subject != null) {
					JTeacherSubject ts = new JTeacherSubject();
					ts.setUserid(user.getId());
					ts.setSubjectid(subject.getId());
					teachSubjectMapper.insertSelective(ts);
				}

			}else if(!form.getTrueName().equals(user.getTruename())
					|| !form.getNickName().equals(user.getNickname())
					|| !user.getRoleid().equals(String.valueOf(form.getRole()))
					|| form.isSex() == user.getMale()
					|| schoolId!= user.getSchoolid()
					){
				String zhlUserId=relative.getZhlUserid();
				long userId =StringUtils.isNotEmpty(zhlUserId)?Long.parseLong(zhlUserId):0;
				user = new JUser();
				user.setId(userId);
				user.setTruename(form.getTrueName());
				user.setNickname(form.getNickName());
				user.setMale(form.isSex());
				user.setRoleid(String.valueOf(form.getRole()));
				user.setSchoolid(schoolId);
				userMapper.updateByPrimaryKeySelective(user);
			}
			
			
			//更新用戶的學段、學科
			
			String termName = form.getTermName();
			String subjectName = form.getSubjectName();
			
			
			termName = StringUtils.isEmpty(termName)?"初中":termName.trim();
			subjectName = StringUtils.isEmpty(subjectName)?"语文":subjectName.trim();
			
			JTerm term =  termMapper.getTermByName(termName);
			if(null!=term){
				
				Example e = new Example(JUserTerm.class);
				e.createCriteria().andCondition("userid="+user.getId()).andCondition("flag = false");
				List<JUserTerm> ls =  userTermMapper.selectByExample(e);
				
				if(ls!=null&& ls.size()>0 && ls.get(0).getTermid()!=term.getId()){
					userTermMapper.updateUserTerm(user.getId(), term.getId());
				}else{
					userTermMapper.updateUserTerm(user.getId(), term.getId());
				}
				
				
				
				
			}
			
			JSubject subject =  subjectMapper.getSubjectByName(subjectName);
			
			if(subject!=null){
				
				Example e = new Example(JTeacherSubject.class);
				e.createCriteria().andCondition("userid="+user.getId()).andCondition("flag = false");
				List<JTeacherSubject> ls2 =  teachSubjectMapper.selectByExample(e);
				
				if(ls2!=null && ls2.size()>0){
					
					boolean update = true ;
					for (Iterator<JTeacherSubject> iterator = ls2.iterator(); iterator.hasNext();) {
						JTeacherSubject jTeacherSubject = (JTeacherSubject) iterator.next();
						if(jTeacherSubject.getSubjectid() == subject.getId()){
							update = false ;
						}
					}
					if(update){
						teachSubjectMapper.addTeacherSubject(user.getId(), subject.getId());
					}
					
				}else{
					teachSubjectMapper.addTeacherSubject(user.getId(), subject.getId());
				}
				
			}
			
			form.setUserName(relative.getZhlUsername());
			return user.getId();
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
		s.setNodeid(null!=form.getNodeId()&& form.getNodeId() >0 ?form.getNodeId().intValue():1);
		s.setRegistertime(date);
		s.setRoleid(role);
		s.setPwd(PWDEncrypt.doEncryptByte(default_pwd));

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
		userinfo.setUserid(s.getId());
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
			userTerm.setUserid(s.getId());
			userTerm.setTermid(term.getId());
			userTermMapper.insertSelective(userTerm);
		}

		JSubject subject = new JSubject();
		subject.setName(subjectName);
		subject = subjectMapper.selectOne(subject);

		if (subject != null) {
			JTeacherSubject ts = new JTeacherSubject();
			ts.setUserid(s.getId());
			ts.setSubjectid(subject.getId());
			teachSubjectMapper.insertSelective(ts);
		}

		
		
		return s.getId();
	}
	
	
	
	
	
	
	/**
	 * 返回可以使用的schoolId
	 * @param form
	 * @return
	 */
	protected Long getSchoolId(RegisterAddForm form) {
		
		String provinceName =StringUtils.trim(form.getProvinceName().replaceAll(" ", ""));
		String cityName = StringUtils.trim(form.getCityName().replaceAll(" ", ""));
		String arealName = StringUtils.trim(form.getArealName().replaceAll(" ", ""));
		String schoolName = StringUtils.trim(form.getSchoolName().replaceAll(" ", ""));
		
		long provinceId = 0;
		long cityId = 0;
		long districtId = 0;
		long schoolId = 0;

		// 机构信息处理
		List<Province> proList = proMapper.queryProvinceByName(
				provinceName.replace("省","")
							.replace("市","")
							.replace("自治区","")
				);
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

	@Override
	public ResultJSON register(AccountRegisterWebForm form) throws Exception {
		
		SCard card =  cardMapper.selectByPrimaryKey(form.getCardId());
		
		
		SRegister s = new SRegister();
		s.setCardid(card.getId());
		s.setFlag(false);
		s.setEmail("");
		s.setName(form.getUserName());
		s.setNodeid(1);
		s.setRegistertime(Calendar.getInstance().getTime());
		s.setRoleid(card.getRoleid());
		s.setPwd(PWDEncrypt.doEncryptByte(form.getUserPassword()));

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONDAY, card.getExpNum());
		s.setReendtime(c.getTime());// 设置最后的有效期

		// 增加注册信息
		rMapper.addRegister(s);

		JUser user = new JUser();
		user.setId(s.getId());
		user.setName(form.getUserName());
		user.setTruename(form.getTrueName());
		user.setNickname(form.getTrueName());
		user.setFlag(false);
		user.setIsfirstlogin(false);
		user.setMale(form.getSex());
		user.setRoleid(String.valueOf(card.getRoleid()));
		user.setStatus(0);
		user.setSchoolid(form.getSchoolId());

		userMapper.insertSelective(user);

		// userInfoMapper
		JUserInfo userinfo = new JUserInfo();
		userinfo.setUserid(user.getId());
		userinfo.setResume("");
		userinfo.setFlag(false);
		userInfoMapper.insertSelective(userinfo);


		if (form.getTermId() != null && form.getTermId()>0) {
			JUserTerm userTerm = new JUserTerm();
			userTerm.setUserid(user.getId());
			userTerm.setTermid(form.getTermId());
			userTermMapper.insertSelective(userTerm);
		}


		if (form.getSubjectId() != null&& form.getSubjectId()>0) {
			JTeacherSubject ts = new JTeacherSubject();
			ts.setUserid(user.getId());
			ts.setSubjectid(form.getSubjectId());
			teachSubjectMapper.insertSelective(ts);
		}

		
		if(form.getClassId()!=null){
			
		}
		
		if(form.getGradeId()!=null){
			
		}
		
		
		return ResultJSON.getSuccess(s.getId());
		
	}

	@Override
	public SRegister addRegister(SRegister record) throws Exception {
		rMapper.addRegister(record);
		return record;
	}

	@Override
	public List<CardExcelForm> addRegister(List<CardExcelForm> list) throws Exception {
		//开始注册
		CardExcelForm form = null ;
		SRegister reg = null;
		JUser user = null ;
		JUserInfo userInfo = null;
		JUserTerm userTerm = null;
		JTeacherSubject ts = null;
		Calendar curr = Calendar.getInstance();
		Calendar end = null ;

		
		for (Iterator<CardExcelForm> iterator = list.iterator(); iterator.hasNext();) {
			 
			form = (CardExcelForm)iterator.next();
			
			
			if(0==form.getCardId() ||0 == form.getRoleId() ){
				//根据卡号获取roleid ,expnum
				Example example = new Example(SCard.class);
				example.createCriteria().andCondition(" cardnumber= ", form.getCardNumber())
				.andCondition(" cardpwd=",form.getCardPwd());
				
				List<SCard>  ls=  cardMapper.selectByExample(example);
				if(ls==null || ls.size()==0){
					//卡号不存在
					throw new RegisterCardError(RegisterCardErrorInfoEuam.NOEXIST);
				}else{
					form.setCardId(ls.get(0).getId());
					form.setRoleId(ls.get(0).getRoleid());
					form.setCardExpNum(ls.get(0).getExpNum());
				}
				
			}else if(form.getCardId()>0 && (0 == form.getRoleId() || form.getCardExpNum()==0)){
				SCard  c =  cardMapper.selectByPrimaryKey(form.getCardId());
				form.setCardId(c.getId());
				form.setRoleId(c.getRoleid());
				form.setCardExpNum(c.getExpNum());
			}
			
			 //用戶對象
			 reg= new SRegister();
			 reg.setCardid(form.getCardId());
			 reg.setEmail("");
			 reg.setFlag(false);
			 reg.setName(form.getUserName());
			 reg.setRoleid(form.getRoleId());
			 
			 reg.setNodeid(1);
			 reg.setPwd(PWDEncrypt.doEncryptByte(form.getUserPwd()));
			 reg.setRegistertime(curr.getTime());
			 
			 end = Calendar.getInstance();
			 end.add(Calendar.MONTH, form.getCardExpNum());
			 reg.setReendtime(end.getTime());
			 
			 //写入sso
			 rMapper.addRegister(reg);
			 
			 //JUser 
			 user = new JUser();
			 user.setId(reg.getId());
			 user.setMale(form.male!=null?form.male:"男".equals(form.getSexName())?false:true);
			 user.setFlag(false);
			 user.setName(form.getUserName());
			 user.setTruename(form.getTrueName());
			 user.setNickname(form.getNickName());
			 user.setRoleid(String.valueOf(form.getRoleId()));
			 user.setSchoolid(form.getSchoolId());
			 
			 userInfo = new JUserInfo();
			 userInfo.setUserid(reg.getId());
			 userInfo.setScouresum(0);
			 userInfo.setResume("");
			 userInfo.setOperatescore(0);
			 userInfo.setAnswerscore(0);
			 userInfo.setForumflowernum(0);
			 userInfo.setResume("");
			 userInfo.setFlag(false);
			 
			 userMapper.insertSelective(user);
			 userInfoMapper.insertSelective(userInfo);
			 
			 if(form.getTermId()>0){
				 userTerm = new JUserTerm();
				 userTerm.setUserid(reg.getId());
				 userTerm.setTermid(form.getTermId());
				 userTerm.setFlag(false);
				 
				 userTermMapper.insertSelective(userTerm);
			 }
			 
			 
			 if(form.getSubjectId()>0){
				 ts = new JTeacherSubject();
				 ts.setUserid(reg.getCardid());
				 ts.setSubjectid(form.getSubjectId());
				 ts.setFlag(false);
				 teachSubjectMapper.insertSelective(ts);
			 }
			 
			 form.setMessage("SUCCESS");
		}
		
		
		return list;
	}
	
	
	
}

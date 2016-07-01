package net.tfedu.zhl.sso.users.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.core.exception.OutOfDateException;
import net.tfedu.zhl.core.exception.WithoutUserException;
import net.tfedu.zhl.core.exception.WrongPassWordException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.sso.city.dao.CityMapper;
import net.tfedu.zhl.sso.district.dao.DistrictMapper;
import net.tfedu.zhl.sso.province.dao.ProvinceMapper;
import net.tfedu.zhl.sso.province.entity.Province;
import net.tfedu.zhl.sso.school.dao.JSchoolMapper;
import net.tfedu.zhl.sso.subject.dao.JTeacherSubjectMapper;
import net.tfedu.zhl.sso.term.dao.JUserTermMapper;
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
public class RegisterServiceImpl implements RegisterService {

	@Resource
	SRegisterMapper rMapper;
	
	
	@Resource
	SCardMapper cardMapper;
	
	@Resource
	SBatchMapper batchMapper;
	
	@Resource
	JUserMapper userMapper;

	@Resource
	JUserTermMapper userTermMapper;
	
	@Resource
	JTeacherSubjectMapper teachSubjectMapper;
	
	@Resource
	JUserInfoMapper userInfoMapper;
	
	
	@Resource
	ProvinceMapper  proMapper;
	
	@Resource
	CityMapper cityMapper;
	
	@Resource
	DistrictMapper disMapper;
	
	@Resource
	JSchoolMapper schMapper;
	
	
	
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
	public long addRegister(RegisterAddForm form) throws Exception {
	    String userName = form.getUserName() ;
	    String trueName  = form.getTrueName();
	    String nickName  = StringUtils.isEmpty(form.getNickName())?trueName:form.getNickName();
	    boolean sex  =  form.isSex();
		long role = form.getRole();	
		String motto = ControllerHelper.checkEmpty(form.getMotto());		
		String birthDate = form.getBirthDate();
		String provinceName = form.getProvinceName();
		String cityName = form.getCityName();
		String arealName = form.getArealName();
		String schoolName = form.getSchoolName();
		String termName = form.getTermName();
		String subjectName = form.getSubjectName();

		long schoolId= 0 ;
		
		
		
		
		
		
		
		SRegister register = new SRegister();
		// 卡有效期
		int expNum = 36;
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SBatch ben = new SBatch();
		String batchName = "PT"
				+ format.format(date) + "_" + userName + "_" + 1;
		ben.setBatchname(batchName);
		ben.setNumbercard(1);
		ben.setFlag(false);
		batchMapper.insert(ben);//增加批次


		SCard card = new SCard();
		card.setRoleid(role);
		card.setRegistkeytype("P");
		card.setExpNum(expNum);
		card.setBatchid(ben.getId());
		card.setCreatetime(date);
	    card.setState(0);
	    card.setFlag(false);
	    
	    String password ="";
        String cardNo = "";
        String prefix = ""+role;	
        
        boolean flag = true ;
        while(flag){
        	cardNo = prefix + 
                    (int)(Math.random() * 100000000.0D + 900000000.0D);
        	password = ""+(int)(Math.random() * 100000.0D + 900000.0D);
        	SCard _card = new SCard();
        	_card.setCardnumber(cardNo);
        	int cont =  cardMapper.selectCount(_card);
        	if(0== cont){
        		flag = false ;
        	}
        }
        
        card.setCardnumber(cardNo);
        card.setCardpwd(password);
        card.setFlag(false);
		
        cardMapper.insertSelective(card);//增加卡号
		
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
		s.setReendtime(c.getTime());//设置最后的有效期
		
		//增加注册信息
		rMapper.insertSelective(s);
		
		
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
		
		//userInfoMapper
		JUserInfo userinfo = new JUserInfo();
		userinfo.setUserid(user.getId());
		if(StringUtils.isNotEmpty(birthDate)){
			userinfo.setBirthdate(format.parse(birthDate));
		}
		userinfo.setResume(ControllerHelper.checkEmpty(motto));
		userinfo.setFlag(false);
		userInfoMapper.insertSelective(userinfo);
		
		
		
		
		
		
		
		return s.getId();
	}

	
	
	
}

package net.tfedu.zhl.sso.users.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.exception.RegisterCardError;
import net.tfedu.zhl.core.exception.euam.RegisterCardErrorInfoEuam;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.school.dao.JSchoolMapper;
import net.tfedu.zhl.sso.school.entity.JSchool;
import net.tfedu.zhl.sso.users.dao.SCardMapper;
import net.tfedu.zhl.sso.users.entity.SCard;
import net.tfedu.zhl.sso.users.service.SCardService;
import tk.mybatis.mapper.entity.Example;

/**
 
  
    @author wangwr
    @date 2016年12月26日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("cardService")
public class SCardServiceImpl extends BaseServiceImpl<SCard> implements SCardService {
	
	
	@Resource
	SCardMapper mapper;
	
	@Resource 
	JSchoolMapper schoolMapper;
	

	@Override
	public ResultJSON isCardAvailable(String cardNum, String cardPwd)  throws CustomException{

		Example example = new Example(SCard.class);
		example.createCriteria().andCondition(" cardnumber="+cardNum);
		List<SCard> list =  mapper.selectByExample(example);
		if(list == null || list.size() == 0 ){
			//卡号不存在
			throw new RegisterCardError(RegisterCardErrorInfoEuam.NOEXIST);
		}
		
		SCard  card = list.get(0);
		
		if(!cardPwd.equals(card.getCardpwd())){
			//密码错误
			throw new RegisterCardError(RegisterCardErrorInfoEuam.NOPWD);
		}
		
		if(card.getState()>0){
			//卡被使用
			throw new RegisterCardError(RegisterCardErrorInfoEuam.BEUSED);
		}
		

		if(null!= card.getEndtime() && card.getEndtime().before(Calendar.getInstance().getTime())){
			//卡已经过期
			throw new RegisterCardError(RegisterCardErrorInfoEuam.EXPIRE);
		}
		
		//是否設置學校
		if(null!=card.getPositionid() && card.getPositionid()>0){
			JSchool school =  schoolMapper.selectByPrimaryKey(card.getPositionid());
			if(school!=null){
				Map<String,Object> sch = new HashMap<String,Object>();
				sch.put("schoolId", school.getId());
				sch.put("schoolName", school.getName());
				return ResultJSON.getSuccess(sch);
			}
			
		}
		
		return ResultJSON.getSuccess("");
	}

	
	
}

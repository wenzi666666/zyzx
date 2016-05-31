package net.tfedu.zhl.cloud.resource.questionbank;

import net.tfedu.zhl.core.exception.NoTokenException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 题库对接接口
 * 
 * （复写3.0接口，原有的规范不变）
 * @author wangwr
 *
 */


@Controller
public class QuestionBankController{

	
	
	@RequestMapping(value="questionbankajax_queryUserBasicInfo.action",method=RequestMethod.GET)
	@ResponseBody
	public Object getBasicUserInfo(String user_name) throws Exception{
		
		
		
	
		return ResultJSON.getSuccess("") ;
	}
	
	
	@RequestMapping(value="questionbankajax_queryProvince.action",method=RequestMethod.GET)
	@ResponseBody
	public Object getProvince(Long pro_id)throws Exception {
		
		
	
		return ResultJSON.getSuccess("") ;
	}
	
	
	
	
	
	
	
	
}


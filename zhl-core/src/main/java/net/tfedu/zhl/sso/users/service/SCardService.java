package net.tfedu.zhl.sso.users.service;

import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

/**
 	卡号管理
  
    @author wangwr
    @date 2016年12月26日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface SCardService {
	
	   /** 卡号是否有效
	    * 
	    * @param cardNum 卡号
	    * @param cardPwd 卡密码
	    * 
	    */
	   public ResultJSON isCardAvailable(String cardNum, String cardPwd) throws CustomException;
	
	
}

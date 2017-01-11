package net.tfedu.zhl.sso.users.service;

import java.util.List;

import net.tfedu.zhl.core.exception.CustomException;
import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.users.entity.SBatch;
import net.tfedu.zhl.sso.users.entity.SCard;
import tk.mybatis.mapper.entity.Example;

/**
 	卡号管理
  
    @author wangwr
    @date 2016年12月26日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
public interface SCardService extends BaseService<SCard> {
	
	   /** 卡号是否有效
	    * 
	    * @param cardNum 卡号
	    * @param cardPwd 卡密码
	    * 
	    */
	   public ResultJSON isCardAvailable(String cardNum, String cardPwd) throws CustomException;
	
	   
	   
	   /**
	    * 根据卡号的模板生成卡号
	    * @param card 卡号的模板
	    * @return
	    * @throws CustomException
	    */
	   public ResultJSON createCardByCardModule(SCard card)throws CustomException;
	   
	   
	   
	   
	   /**
	    * 查询批次
	    * @param batchname 批次名称
	    * @return
	    * @throws CustomException
	    */
	   public SBatch  getBatchByName(String batchname)throws CustomException;
	   
	
	   
	   
	   /***
	    * 根据条件获取全部结果
	    * @param example
	    * @return
	    * @throws CustomException
	    */
	   public List<SCard> queryCardByExampe(Example example)throws CustomException;
 	   
	   
}

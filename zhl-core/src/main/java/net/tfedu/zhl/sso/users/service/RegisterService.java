package net.tfedu.zhl.sso.users.service;

import java.util.List;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;
import net.tfedu.zhl.sso.users.module.AccountRegisterWebForm;
import net.tfedu.zhl.sso.users.util.CardExcelForm;

public interface RegisterService extends BaseService<SRegister>{
	
	
	
    /**
     * id获取注册用户
     */
    public SRegister getRegister(Long id);

    /**
     * name获取注册用户
     */
    public SRegister getRegister(String userName);

    /**
     * 修改用户密码
     * 
     * @param userId
     * @param password
     */
    public void modifyRegisterPassword(Long userId, String password);

    /**
     * 登录
     * 
     * @param userName
     * @param password
     * @return
     */
    public SRegister login(String userName, String password)    throws Exception;
    
    
    /**
     * 增加用户注册
     * @param form  卡号批量注册的信息
     * @return     注冊成功
     * @throws Exception 
     */
    public List<CardExcelForm> addRegister(List<CardExcelForm> list)throws Exception;
    
    
    /**
     * 增加用户注册
     * @param form
     * @return
     */
    public SRegister addRegister(SRegister record)throws Exception;
    
    
    
    
    /**
     * 增加用户注册
     * @param form
     * @return
     */
    public SRegister addRegister(RegisterAddForm form)throws Exception;
    
    
   
    /**
     * 增加用户注册(第三方)，需提前将form中的username 改为（加前缀的）可用用户名
     * @param form                   
     * @param userName     第三方系统中的用户名 
     * @param platformcode 第三方系统的code即APP中的前缀
     * @return
     * @throws Exception
     */
    public SRegister addRegister(RegisterAddForm form,String userName, String platformcode)throws Exception;
    
    
   
    
	/**
	 * 注册(更新)第三方传递过来的用户
	 * @param form  注册用户的信息表单,其中的用户名为 第三方系统中的用户名 ,zhl系统中的用户名在方法内部生成
	 * @param app   第三方在知好乐系统中的APP信息
	 */
	public Long registerOrUpdateUserWithThirdPartyApp(RegisterAddForm form,SApp app)throws Exception;
    
    
   /** 实现注册
    * @param form 注册表单
   */
	public ResultJSON register(AccountRegisterWebForm form)throws Exception;
	
	
}

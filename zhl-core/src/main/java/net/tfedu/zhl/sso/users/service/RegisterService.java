package net.tfedu.zhl.sso.users.service;

import net.tfedu.zhl.sso.app.entity.SApp;
import net.tfedu.zhl.sso.users.entity.RegisterAddForm;
import net.tfedu.zhl.sso.users.entity.SRegister;

public interface RegisterService {
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
	public void registerOrUpdateUserWithThirdPartyApp(RegisterAddForm form,SApp app)throws Exception;
    
    

}

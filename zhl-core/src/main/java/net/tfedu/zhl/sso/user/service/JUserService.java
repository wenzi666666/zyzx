package net.tfedu.zhl.sso.user.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;

import net.tfedu.zhl.core.service.BaseService;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.UserAreaInfo;
import net.tfedu.zhl.sso.user.entity.UserEditForm;
import net.tfedu.zhl.sso.user.entity.UserQueryForm;
import net.tfedu.zhl.sso.user.entity.UserQueryResult;
import net.tfedu.zhl.sso.user.entity.UserSimple;

/**
 * 用户相关接口
 * 
 * @author wangwr
 *
 */
public interface JUserService extends BaseService<JUser>{

    /**
     * 根据id获取用户
     * 
     * @param id
     * @return
     */
    public JUser getUserById(long id);

    /**
     * 根据name获取用户
     * 
     * @param id
     * @return
     */
    public JUser getUserByName(String name);

    /**
     * 根据id获取用户
     * (第三方用戶自动登录专用)
     * @param id
     * @return
     */
    public UserSimple getUserSimpleByIdForThirdParty(long id, String model,boolean isRepeatLogin,String logoutUrl,String thirdPartyCode);
    
    /**
     * 根据id获取用户
     * 
     * @param id
     * @return
     */
    public UserSimple getUserSimpleById(long id, String model,boolean isRepeatLogin);
    
    /**
     * 根据id获取用户
     * 
     * @param id
     * @return
     */
    public UserSimple getUserSimpleById(long id, String model,boolean isRepeatLogin,boolean isCache);
    /**
     * 根据id获取用户
     * 
     * @param id
     * @return
     */
    public UserSimple getUserSimpleById(long id, String model);

    /**
     * 根据name获取用户
     * 
     * @param id
     * @return
     */
    public UserSimple getUserSimpleByName(String name);

    /**
     * 修改用户的信息
     * 
     * @param userId
     * @param trueName
     * @param male
     * @param termId
     * @param subjectId
     */
    public void updateUserInfo(Long userId, String trueName, Boolean male, Long termId, Long subjectId);
    
    
    /**
     * 修改用户的信息
     * @param form
     */
    public void updateUserInfo(UserEditForm form );

    /**
     * 修改用户头像
     * 
     * @param userId
     * @param userImage
     */
    public void updateUserImage(Long userId, String userImage);

    /**
     * 登出
     * @param token
     */
    void logout(String token,boolean isRepeatLogin);
    
    

	/**
	 * 获取用户地区信息
	 * @return
	 */
	public HashMap<String,String> getUserTermAndSubject(Long userId);
	
	
	/**
	 * 根据角色、姓名分页获取用户列表(教研平台使用)
	 * @param roleId
	 * @param name
	 * @param model
	 * @param page
	 * @param perPage
	 * @return
	 * @throws Exception
	 */
	public ResultJSON queryUserWithRoleAndName(long roleId,String name,String model,int page,int perPage) throws Exception;
	
	
	/**
	 * 获取maincenter_yun数据库j_user表中缺少的jx用户的注册信息
	 * @return
	 * @throws Exception
	 */
	public List<Long> getMissUserBetweenJXAndSSO() throws Exception;

	
	
	/**
	 * 增加user记录
	 * @param user
	 */
	public void addUser(JUser user);
	
	
	/**
	 * 获取用户的地区信息
	 * @param userId  用户主键
	 * @return    用户地区信息对象
	 */
	public UserAreaInfo getUserAreaInfo(long userId);
	

	/**
	 * 增加用户的网页登录状态
	 * @param userId   登录的用户
	 * @param token    用户的token
	 * @param registerNodeid    用户的注册节点
	 * @param request  需要根据request获取ip、设备信息等
	 */
	public void addUserLoginStatusWeb(long userId,long registerNodeid,String token,HttpServletRequest request);
	
	
	/**
	 * 修改用户的状态为退出登录
	 * @param userId  登录的用户
	 * @param token   用户的token
	 */
	public void updateUserStatutToLogout(long userId,String token);
	
	
	
	
	/**
	 * 根据条件分页查询用户
	 * @param scopeList  管理員的管理范围
	 * @param form
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageInfo<UserQueryResult> queryUserByForm(List<SBackUserScope> scopeList, UserQueryForm form ,int pageNum, int pageSize)throws Exception;
	
	
	
	
	
	
	
}

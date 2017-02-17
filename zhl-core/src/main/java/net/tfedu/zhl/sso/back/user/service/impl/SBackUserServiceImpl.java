package net.tfedu.zhl.sso.back.user.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.tfedu.zhl.cloud.utils.datatype.IdUtil;
import net.tfedu.zhl.cloud.utils.security.PWDEncrypt;
import net.tfedu.zhl.core.exception.UnusualErrorException;
import net.tfedu.zhl.core.exception.WithoutAuthorizationException;
import net.tfedu.zhl.core.exception.WithoutUserException;
import net.tfedu.zhl.core.service.impl.BaseServiceImpl;
import net.tfedu.zhl.helper.BackManagerTokenCacheUtil;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.ZhlOnlineUtil;
import net.tfedu.zhl.sso.back.func.dao.SProductBackFunclistDetailMapper;
import net.tfedu.zhl.sso.back.func.dao.SProductBackFunclistMapper;
import net.tfedu.zhl.sso.back.func.entity.SProductBackFunclist;
import net.tfedu.zhl.sso.back.online.dao.SOnlineManagerMapper;
import net.tfedu.zhl.sso.back.online.entity.SOnlineManager;
import net.tfedu.zhl.sso.back.role.dao.SProductBackRoleMapper;
import net.tfedu.zhl.sso.back.role.entity.SProductBackRole;
import net.tfedu.zhl.sso.back.user.bean.FuncInfo;
import net.tfedu.zhl.sso.back.user.bean.ManagerSimple;
import net.tfedu.zhl.sso.back.user.dao.SBackUserMapper;
import net.tfedu.zhl.sso.back.user.dao.SBackUserScopeMapper;
import net.tfedu.zhl.sso.back.user.dao.SProductBackUserRoleMapper;
import net.tfedu.zhl.sso.back.user.entity.SBackUser;
import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;
import net.tfedu.zhl.sso.back.user.entity.SProductBackUserRole;
import net.tfedu.zhl.sso.back.user.service.SBackUserService;

/**
 
  	管理员业务
    @author wangwr
    @date 2017年1月5日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Service("sBackUserService")
@Transactional("ssoTransactionManager")
public class SBackUserServiceImpl extends BaseServiceImpl<SBackUser> implements SBackUserService {

	@Autowired
	SBackUserMapper  mapper;	
	@Autowired
	SBackUserScopeMapper  scopeMapper;
	
	@Autowired
	SProductBackUserRoleMapper userRoleMapper;
	
	@Autowired
	SProductBackRoleMapper  roleMapper;
	@Autowired
	SProductBackFunclistMapper funcMapper;
	@Autowired
	SProductBackFunclistDetailMapper funcDetailMapper;
	@Autowired
	SOnlineManagerMapper onlineMapper;
    @Autowired
    CacheManager cacheManager;
	
	@Override
	public ResultJSON login(String username, String password,String productCode) throws Exception {
		//准备条件
		SBackUser _user  = new SBackUser();
		_user.setName(username);
		
		//查询
		List<SBackUser> list =  mapper.select(_user);
		
		//用户不存在
		if(null == list || list.size()==0){
			throw new WithoutAuthorizationException(username);
		}
		
		//用户信息异常
		if(list.size()>1){
			throw new UnusualErrorException();
		}
		
		SBackUser currentUser = list.get(0);
		
		
		ManagerSimple simple = getBackUserSimpleInfo(productCode, currentUser, cacheManager, true);
		
		
		return ResultJSON.getSuccess(simple);
	}

	
	/**
	 * 获取管理员的页面缓存信息，并决定是否缓存到服务器cache
	 * @param productCode   登录的产品
	 * @param currentUser   当前用户
	 * @param cacheManager  缓存管理
	 * @param cacheFlag     是否缓存到服务器cache
	 * @return
	 * @throws Exception
	 */
	private ManagerSimple  getBackUserSimpleInfo(String productCode, SBackUser currentUser,CacheManager cacheManager,boolean cacheFlag)throws Exception{
		List<SBackUserScope> scopeList = null;
		List<FuncInfo> funcList = null;
		SProductBackRole role = null;
		
		if(null!=currentUser){
			
			SBackUserScope record = new SBackUserScope();
			record.setUserid( currentUser.getId());
			scopeList = scopeMapper.select(record);
			
			//获取管理员在当前产品下的角色
			List<SProductBackRole> ls = roleMapper.getManagerRole(currentUser.getId(), productCode);
			if(ls==null  || ls.size()==0){
				throw new WithoutUserException();
			}
			role = ls.get(0);	
			
			//获取管理员的权限
			funcList = getManagerFuncList(role.getId(), productCode);
		}
		
		
		
		ManagerSimple simple = new ManagerSimple();
		simple.setUserId(currentUser.getId());
		simple.setUserName(currentUser.getName());
		simple.setTrueName(currentUser.getTruename());
		simple.setNickName(currentUser.getNickname());
		simple.setMale(currentUser.getMale()?"女":"男");
		simple.setNodeId(currentUser.getNodeid());
		simple.setRoleId(role.getId());
		simple.setRoleName(role.getName());
		simple.setLoginTime(Calendar.getInstance().getTime());
		simple.setModel("");
		simple.setScopeList(scopeList);
		simple.setFuncList(funcList);
		
		String token = IdUtil.getUUID();
		simple.setToken(token);
		
		if(cacheFlag){
			//写入缓存
			BackManagerTokenCacheUtil.addUserInfoCache("", cacheManager, token, simple, false);
		}
			
		
		return simple;
		
	}
	
	
	

	/**
	 * 获取管理员的权限
	 * @param id    角色id
	 * @param productCode  產品id
	 * @return
	 */
	private List<FuncInfo> getManagerFuncList(Long id, String productCode) {
		List<SProductBackFunclist>  list =  funcMapper.getFuncListForRole(id, productCode);
		List<FuncInfo> result = new ArrayList<FuncInfo>();
		SProductBackFunclist f  = null;
		SProductBackFunclist fc  = null;
		FuncInfo info = null;
		FuncInfo _info = null;
		for (Iterator<SProductBackFunclist> iterator = list.iterator(); iterator.hasNext();) {
			f = iterator.next();
			
			info = new FuncInfo();
			info.setId(f.getId());
			info.setIconPath(f.getIconpath());
			info.setName(f.getName());
			info.setOrderNum(f.getOrdernum());
			info.setPath(f.getPath());
						
			List<SProductBackFunclist> temp = funcMapper.getFuncListByTheParent(f.getId());
			List<FuncInfo> child = new ArrayList<FuncInfo>();
			for (Iterator<SProductBackFunclist> iterator2 = temp.iterator(); iterator2.hasNext();) {
				fc =  iterator2.next();
				
				_info = new FuncInfo();
				_info.setId(fc.getId());
				_info.setIconPath(fc.getIconpath());
				_info.setName(fc.getName());
				_info.setOrderNum(fc.getOrdernum());
				_info.setPath(fc.getPath());
				
				_info.setDetails(funcDetailMapper.getFuncDetailList(fc.getId(), id));
				
				child.add(_info);
			}
			
			info.setChildren(child);
			result.add(info);
		}
		
		return result;
	}


	@Override
	public void addUserLoginStatusWeb(long userId, long registerNodeid, String token, HttpServletRequest request)
			throws Exception {

		int clientType = ZhlOnlineUtil.ONLINE_CLIENTTYPE_BROWSER;
		//当前时间
		Date currentDate = ControllerHelper.getCurrentDate();
		//ip
		String ip =  ZhlOnlineUtil.getIpAddr(request);
		//设备信息
		String deviceInfo  =ZhlOnlineUtil.getDeviceInfoWeb(request, userId,registerNodeid, clientType);
		// 客户端的版本
		String clientVersion = ZhlOnlineUtil.getClientVersion(request);

		
		//将之前的登录置为 “踢出”状态
		//将之前的登录置为 “踢出”状态
		onlineMapper.clearRepeatUserLogin(userId, clientType, registerNodeid);
		
		
		SOnlineManager record = new SOnlineManager();
		
		record.setClienttype(clientType);
		record.setClientversion(clientVersion);
		record.setDeviceinfo(deviceInfo);
		record.setFlag(false);
		record.setLastopertime(currentDate);
		record.setLoginip(ip);
		record.setLoginnodeid(1l);
		record.setLogintime(currentDate);
		record.setStatus(ZhlOnlineUtil.ONLINE_STATUS_ONLINE);
		record.setToken(token);
		record.setUserid(userId);
		
		//增加在线记录
		onlineMapper.insertUseGeneratedKeys(record);
		
		//修改user表中的web登录时间
		SBackUser _record = new SBackUser();
		_record.setId(userId);
		_record.setLogintime(currentDate);
		mapper.updateByPrimaryKeySelective(_record);
		
		
		
	}


	@Override
	public ManagerSimple getManagerSimpleById(long id, String model, boolean isRepeatLogin,String productCode) throws Exception {
		SBackUser currentUser = mapper.selectByPrimaryKey(id);
		//用户不存在
		if(null == currentUser ){
			throw new WithoutAuthorizationException();
		}
				
		return getBackUserSimpleInfo(productCode, currentUser, cacheManager, false);
	}


	@Override
	public void logout(String token,long userId,boolean isRepeatLogin) throws Exception {
		//清理緩存
		BackManagerTokenCacheUtil.clearUserInfoCache(cacheManager, token,isRepeatLogin);
		//設置online状态
		onlineMapper.updateOnlineStatus(token, ZhlOnlineUtil.ONLINE_STATUS_LOGOUT);
		//修改用户退出时间
		SBackUser user = new SBackUser();
		user.setId(userId);
		user.setLogouttime(Calendar.getInstance().getTime());
		
		mapper.updateByPrimaryKeySelective(user);
	}


	@Override
	public ResultJSON addManagerRoleAndScope(Long userId,Long roleId, SBackUserScope userScope) throws Exception {
		
		SProductBackUserRole record = new SProductBackUserRole();
		
		record.setFlag(false);
		record.setRoleid(roleId);
		record.setUserid(userId);
		
		userRoleMapper.insertSelective(record);
		
		scopeMapper.insertSelective(userScope);
		
		return ResultJSON.getSuccess("");
	}


	@Override
	public ResultJSON resetPwd(Long userid, String pwd) throws Exception {
		
		mapper.updatePwd(userid, PWDEncrypt.doEncryptByte(pwd));
		return defaultSuccess("");
	}


	@Override
	public ResultJSON addManager(SBackUser user) throws Exception {
		
		mapper.addManager(user);
		
		return defaultSuccess("");
	}

	
	
	
	

}

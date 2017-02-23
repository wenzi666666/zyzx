package net.tfedu.zhl.sso.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import net.tfedu.zhl.helper.CoreMapper;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.entity.JUserTeachingQueryEntity;
import net.tfedu.zhl.sso.user.entity.UserAreaInfo;
import net.tfedu.zhl.sso.user.entity.UserQueryForm;
import net.tfedu.zhl.sso.user.entity.UserQueryResult;
import net.tfedu.zhl.sso.user.entity.UserSimple;
import net.tfedu.zhl.sso.user.entity.UsersEntity;

/**
 * 用户业务表
 * 
 * @author wangwr
 *
 */
public interface JUserMapper extends CoreMapper<JUser> {

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
	 * 
	 * @param id
	 * @return
	 */
	public UserSimple getUserSimpleById(long id);

	/**
	 * 根据name获取用户
	 * 
	 * @param id
	 * @return
	 */
	public UserSimple getUserSimpleByName(String name);

	/**
	 * 修改用户头像信息
	 * 
	 * @param userId
	 * @param userImage用户头像的相对路径
	 */
	public void updateUserImage(Long userId, String userImage);

	/**
	 * 获取用户地区信息
	 * 
	 * @return
	 */
	public HashMap<String, Object> getUserAreaInfo(Long userId);

	/**
	 * 获取用户地区信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getUserTermAndSubject(Long userId);

	/**
	 * 题库对接，根据name查询所有用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public List<UsersEntity> queryUserBasicInfo(@Param("userName") String userName);

	/**
	 * 根据userId查询用户的truename
	 * 
	 * @param userId
	 * @return
	 */
	public String getTrueNameById(@Param("userId") long userId);

	/**
	 * 根据角色、姓名分页获取用户列表(教研平台使用)
	 * 
	 * @param roleId
	 * @param name
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<JUserTeachingQueryEntity> queryUserWithRoleAndName(@Param("roleId") long roleId,
			@Param("name") String name, @Param("model") String model) throws Exception;
	
	
	/**
	 * 获取maincenter_yun数据库j_user表中缺少的jx用户的注册信息
	 * @return
	 * @throws Exception
	 */
	public List<Long> getMissUserBetweenJXAndSSO() throws Exception;
	
	
	
	/**
	 * 获取用户的地区信息
	 * @param userId  用户主键
	 * @return    用户地区信息对象
	 */
	public UserAreaInfo getUserAreaALLInfo(@Param("userId") long userId);
	
	
	/**
	 * 获取学校的所有指定的用户
	 * @param schoolId   学校Id 
	 * @param roleId     指定的用角色，0 为全部，1 为学生 2为老师     
	 * @return
	 */
	public List<Long> getSchoolUserIds(@Param("schoolId")long schoolId,@Param("roleId")long roleId);
	
	/**
	 * 获取学校的所有指定的用户
	 * @param districtId 地区id
	 * @param roleId     指定的用角色，0 为全部，1 为学生 2为老师     
	 * @return
	 */
	public List<Long> getDistrictIdUserIds(@Param("districtId")long districtId,@Param("roleId")long roleId);
	
	
	/**
	 * 获取用户的真实姓名和学校名称
	 * @param userId
	 * @return
	 */
	public Map<String,Object>getUserTrueNameAndSchoolName(@Param("userId")long userId);
	
	
	
	/**
	 * 根据用户的指定范围（全部、指定区、指定校）查询用户
	 * 
	 *   全部范围同时为空时，认为是查询全部
	 * @param form         查询提交
	 * @param provinceIds  省级管理员的管理范围
	 * @param cityIds	         市级管理员的管理范围
	 * @param districtIds  区级管理员的管理范围
	 * @param schoolIds    校级管理员的管理范围
	 * @return
	 */
	public List<UserQueryResult> queryUserByForm(@Param("form")UserQueryForm form
			,@Param("provinceIds") Long[] provinceIds,@Param("cityIds") Long[] cityIds
			,@Param("districtIds") Long[] districtIds,@Param("schoolIds") Long[] schoolIds);
	
	
	
	

}
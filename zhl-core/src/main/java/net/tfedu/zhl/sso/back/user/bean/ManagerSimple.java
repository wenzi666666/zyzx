package net.tfedu.zhl.sso.back.user.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import net.tfedu.zhl.sso.back.user.entity.SBackUserScope;

/**
copyRight@ 同方知好乐教育科技(北京)有限公司
@author　wangwr
@date 　
@desc 　　管理员简易信息，后台管理员登录缓存
*/

public class ManagerSimple implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private Long userId;
	private String trueName;
	private String nickName;
	private String roleName;
	private Long roleId;
	private String male;
	private List<SBackUserScope> scopeList;
	private List<FuncInfo> funcList;
	private String token;
	private String model;
	
	private Integer nodeId;
	private Date loginTime;
	
	
	
	
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getNodeId() {
		return nodeId;
	}
	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getMale() {
		return male;
	}
	public void setMale(String male) {
		this.male = male;
	}
	public List<SBackUserScope> getScopeList() {
		return scopeList;
	}
	public void setScopeList(List<SBackUserScope> scopeList) {
		this.scopeList = scopeList;
	}
	public List<FuncInfo> getFuncList() {
		return funcList;
	}
	public void setFuncList(List<FuncInfo> funcList) {
		this.funcList = funcList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	
	
	
}
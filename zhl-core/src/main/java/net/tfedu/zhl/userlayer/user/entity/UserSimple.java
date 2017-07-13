package net.tfedu.zhl.userlayer.user.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

import net.tfedu.zhl.userlayer.funclist.entity.FuncListSimple;

/**
 * 用户信息简易版
 * 
 * @author wangwr
 *
 */
public class UserSimple implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4154529847915546034L;
    
    String userName;
    Long userId;
    String trueName;
    String roleName;
    String schoolName;
    String male;
    String termName;
    String userImage;
    Long roleId;
    String token;
    Date logintime;
    Long schoolId;
    Long districtId ; 
    
    
    
    
    
    /**
     * 第三方平台编码 
     */
    String thirdParyCode;
    
    /**
     * 调用退出接口之后前往的（第三方）页面
     */
    String logoutTarget;
    
    
    
    
    /**
     * 登录产品
     */
    String  model;
    
    /**
     * 获取用户学科ids
     */
    private String subjectIds;

    /**
     * 获取用户学科
     */
    private String subjectNames;
    
    /**
     * 用户附加角色
     */
    @Transient
    private Set<Long> roleIds;
    
    /**
     * 用户权限
     */
    private List<FuncListSimple> funcList;

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(String subjectNames) {
        this.subjectNames = subjectNames;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }



    public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

  

  

	public List<FuncListSimple> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<FuncListSimple> funcList) {
		this.funcList = funcList;
	}

	
	
	public String getThirdParyCode() {
		return thirdParyCode;
	}

	public void setThirdParyCode(String thirdParyCode) {
		this.thirdParyCode = thirdParyCode;
	}

	
	

	public String getLogoutTarget() {
		return logoutTarget;
	}

	public void setLogoutTarget(String logoutTarget) {
		this.logoutTarget = logoutTarget;
	}
	
	

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	@Override
    public String toString() {
        return "UserSimple [userName=" + userName + ", userId=" + userId + ", trueName=" + trueName + ", roleName="
                + roleName + ", schoolName=" + schoolName + ", male=" + male + ", termName=" + termName + ", userImage="
                + userImage + ", roleId=" + roleId + ", token=" + token + ", logintime=" + logintime + ", model="
                + model + ", subjectIds=" + subjectIds + ", subjectNames=" + subjectNames + ", roleIds=" + roleIds
                +",schoolId="+schoolId+",districtId="+districtId
                + ", funcList=" + (funcList==null?"":funcList.toString()) + ",thirdParyCode="+thirdParyCode+",logoutTarget="+logoutTarget+"]";
    }

    
}

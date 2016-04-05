package net.tfedu.zhl.sso.user.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

import net.tfedu.zhl.sso.role.entity.JRole;
import net.tfedu.zhl.sso.users.entity.FuncList;

/**
 * 用户信息简易版
 * 
 * @author wangwr
 *
 */
public class UserSimple implements Serializable {

    String userName;
    Long userId;
    String trueName;
    String roleName;
    String schoolName;
    String male;
    String termName;
    String userImage;
    Long roleId;
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
    private List<JRole> roles;
    
    /**
     * 用户权限
     */
    @Transient
    private List<FuncList> funcs;

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

    public List<JRole> getRoles() {
        return roles;
    }

    public void setRoles(List<JRole> roles) {
        this.roles = roles;
    }

    public List<FuncList> getFuncs() {
        return funcs;
    }

    public void setFuncs(List<FuncList> funcs) {
        this.funcs = funcs;
    }

    @Override
    public String toString() {
        return "UserSimple [userName=" + userName + ", userId=" + userId + ", trueName=" + trueName + ", roleName="
                + roleName + ", schoolName=" + schoolName + ", male=" + male + ", termName=" + termName + ", userImage="
                + userImage + ", roleId=" + roleId + ", subjectIds=" + subjectIds + ", subjectNames=" + subjectNames
                + ", roles=" + roles + ", funcs=" + funcs + "]";
    }

    
}

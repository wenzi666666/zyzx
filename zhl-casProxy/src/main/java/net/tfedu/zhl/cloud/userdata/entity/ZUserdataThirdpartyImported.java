package net.tfedu.zhl.cloud.userdata.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "z_userdata_thirdparty_imported")
public class ZUserdataThirdpartyImported {
    /**
     * 用户在第三方平台的唯一主键
     */
    @Id
    private String uuid;

    /**
     * 对接app的注册主键
     */
    @Id
    private Integer appid;

    /**
     * 登录名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String truename;

    /**
     * 昵称
     */
    private String nickname;

    /**
     *  true 女  false  男  性别
     */
    private Boolean sex;

    /**
     * 角色（老师、学生）
     */
    private String role;

    /**
     * 人生格言
     */
    private String motto;

    /**
     * 生日
     */
    private Date birthdate;

    /**
     * 省
     */
    private String provincename;

    /**
     * 市
     */
    private String cityname;

    /**
     * 区
     */
    private String areaname;

    /**
     * 校
     */
    private String schoolname;

    /**
     * 学段
     */
    private String termname;

    /**
     * 学科
     */
    private String subjectname;

    public ZUserdataThirdpartyImported(String uuid, Integer appid, String username, String truename, String nickname, Boolean sex, String role, String motto, Date birthdate, String provincename, String cityname, String areaname, String schoolname, String termname, String subjectname) {
        this.uuid = uuid;
        this.appid = appid;
        this.username = username;
        this.truename = truename;
        this.nickname = nickname;
        this.sex = sex;
        this.role = role;
        this.motto = motto;
        this.birthdate = birthdate;
        this.provincename = provincename;
        this.cityname = cityname;
        this.areaname = areaname;
        this.schoolname = schoolname;
        this.termname = termname;
        this.subjectname = subjectname;
    }

    public ZUserdataThirdpartyImported() {
        super();
    }

    /**
     * 获取用户在第三方平台的唯一主键
     *
     * @return uuid - 用户在第三方平台的唯一主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置用户在第三方平台的唯一主键
     *
     * @param uuid 用户在第三方平台的唯一主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取对接app的注册主键
     *
     * @return appid - 对接app的注册主键
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置对接app的注册主键
     *
     * @param appid 对接app的注册主键
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * 获取登录名
     *
     * @return username - 登录名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登录名
     *
     * @param username 登录名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return truename - 真实姓名
     */
    public String getTruename() {
        return truename;
    }

    /**
     * 设置真实姓名
     *
     * @param truename 真实姓名
     */
    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取 true 女  false  男  性别
     *
     * @return sex -  true 女  false  男  性别
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置 true 女  false  男  性别
     *
     * @param sex  true 女  false  男  性别
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 获取角色（老师、学生）
     *
     * @return role - 角色（老师、学生）
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置角色（老师、学生）
     *
     * @param role 角色（老师、学生）
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 获取人生格言
     *
     * @return motto - 人生格言
     */
    public String getMotto() {
        return motto;
    }

    /**
     * 设置人生格言
     *
     * @param motto 人生格言
     */
    public void setMotto(String motto) {
        this.motto = motto == null ? null : motto.trim();
    }

    /**
     * 获取生日
     *
     * @return birthdate - 生日
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * 设置生日
     *
     * @param birthdate 生日
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * 获取省
     *
     * @return provincename - 省
     */
    public String getProvincename() {
        return provincename;
    }

    /**
     * 设置省
     *
     * @param provincename 省
     */
    public void setProvincename(String provincename) {
        this.provincename = provincename == null ? null : provincename.trim();
    }

    /**
     * 获取市
     *
     * @return cityname - 市
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * 设置市
     *
     * @param cityname 市
     */
    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    /**
     * 获取区
     *
     * @return areaname - 区
     */
    public String getAreaname() {
        return areaname;
    }

    /**
     * 设置区
     *
     * @param areaname 区
     */
    public void setAreaname(String areaname) {
        this.areaname = areaname == null ? null : areaname.trim();
    }

    /**
     * 获取校
     *
     * @return schoolname - 校
     */
    public String getSchoolname() {
        return schoolname;
    }

    /**
     * 设置校
     *
     * @param schoolname 校
     */
    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname == null ? null : schoolname.trim();
    }

    /**
     * 获取学段
     *
     * @return termname - 学段
     */
    public String getTermname() {
        return termname;
    }

    /**
     * 设置学段
     *
     * @param termname 学段
     */
    public void setTermname(String termname) {
        this.termname = termname == null ? null : termname.trim();
    }

    /**
     * 获取学科
     *
     * @return subjectname - 学科
     */
    public String getSubjectname() {
        return subjectname;
    }

    /**
     * 设置学科
     *
     * @param subjectname 学科
     */
    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname == null ? null : subjectname.trim();
    }
}
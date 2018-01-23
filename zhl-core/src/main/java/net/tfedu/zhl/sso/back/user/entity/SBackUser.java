package net.tfedu.zhl.sso.back.user.entity;


import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "s_back_user")
public class SBackUser {
    /**
     * 非自增的主键id，来保存sso中registerId
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message="用户名不能为空")
    @Size(min=2,max=20,message="用户名的长度在2-20个字符之间")
    private String name;

    
    @NotEmpty(message="真实姓名不能为空")
    @Size(min=2,max=40,message="真实姓名的长度在2-40个字符之间")
    @Column(name = "TrueName")
    private String truename;

    @Column(name = "NickName")
    @Size(min=2,max=40,message="昵称的长度在2-40个字符之间")
    private String nickname;

    /**
     * 性别，0——男，1——女 
     */
    @Column(name = "Male")
    @NotNull(message="必选指明用户的性别")
    private Boolean male;

    /**
     * 用户是否启用状态0:启用 1:不启用 
     */
    private Integer status;

    /**
     * 节点id
     */
    @Column(name = "NodeId")
    private Integer nodeid;

    @Column(name = "PhoneNumber")
    private String phonenumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "QQ")
    private String qq;

    /**
     * ֤
     */
    @Column(name = "IdCard")
    private String idcard;

    /**
     * ֤
     */
    @Column(name = "IdType")
    private String idtype;

    /**
     * 用户查看的最后提示ID
     */
    @Column(name = "lastTipId")
    private Long lasttipid;

    /**
     * 生日
     */
    @Column(name = "BirthDate")
    private Date birthdate;

    @Column(name = "Address")
    private String address;

    @Column(name = "UserImage")
    private String userimage;

    /**
     * 注册时间 
     */
    @Column(name = "RegisterTime")
    private Date registertime;

    /**
     * 账号使用的最后期限
     */
    @NotNull(message="账号使用的最后期限不能为空")
    @Column(name = "reEndTime")
    private Date reendtime;

    /**
     * 最后登录时间 
     */
    @Column(name = "LoginTime")
    private Date logintime;

    /**
     * 退出时间 
     */
    @Column(name = "LogoutTime")
    private Date logouttime;

    /**
     * 是否首次登录  1是  2否 
     */
    @Column(name = "firstLogin")
    private Integer firstlogin;

    /**
     * 是否删除标识，0----否；1-----是
     */
    @Column(name = "Flag")
    private Boolean flag;

    /**
     * 登录密码
     */
    @Column(name = "Pwd")
    private byte[] pwd;

    public SBackUser(Long id, String name, String truename, String nickname, Boolean male, Integer status, Integer nodeid, String phonenumber, String email, String qq, String idcard, String idtype, Long lasttipid, Date birthdate, String address, String userimage, Date registertime, Date reendtime, Date logintime, Date logouttime, Integer firstlogin, Boolean flag, byte[] pwd) {
        this.id = id;
        this.name = name;
        this.truename = truename;
        this.nickname = nickname;
        this.male = male;
        this.status = status;
        this.nodeid = nodeid;
        this.phonenumber = phonenumber;
        this.email = email;
        this.qq = qq;
        this.idcard = idcard;
        this.idtype = idtype;
        this.lasttipid = lasttipid;
        this.birthdate = birthdate;
        this.address = address;
        this.userimage = userimage;
        this.registertime = registertime;
        this.reendtime = reendtime;
        this.logintime = logintime;
        this.logouttime = logouttime;
        this.firstlogin = firstlogin;
        this.flag = flag;
        this.pwd = pwd;
    }

    public SBackUser() {
        super();
    }

    /**
     * 获取非自增的主键id，来保存sso中registerId
     *
     * @return Id - 非自增的主键id，来保存sso中registerId
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置非自增的主键id，来保存sso中registerId
     *
     * @param id 非自增的主键id，来保存sso中registerId
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return TrueName
     */
    public String getTruename() {
        return truename;
    }

    /**
     * @param truename
     */
    public void setTruename(String truename) {
        this.truename = truename == null ? null : truename.trim();
    }

    /**
     * @return NickName
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取性别，0——男，1——女 
     *
     * @return Male - 性别，0——男，1——女 
     */
    public Boolean getMale() {
        return male;
    }

    /**
     * 设置性别，0——男，1——女 
     *
     * @param male 性别，0——男，1——女 
     */
    public void setMale(Boolean male) {
        this.male = male;
    }

    /**
     * 获取用户是否启用状态0:启用 1:不启用 
     *
     * @return status - 用户是否启用状态0:启用 1:不启用 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置用户是否启用状态0:启用 1:不启用 
     *
     * @param status 用户是否启用状态0:启用 1:不启用 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取节点id
     *
     * @return NodeId - 节点id
     */
    public Integer getNodeid() {
        return nodeid;
    }

    /**
     * 设置节点id
     *
     * @param nodeid 节点id
     */
    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    /**
     * @return PhoneNumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * 获取֤
     *
     * @return IdCard - ֤
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * 设置֤
     *
     * @param idcard ֤
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * 获取֤
     *
     * @return IdType - ֤
     */
    public String getIdtype() {
        return idtype;
    }

    /**
     * 设置֤
     *
     * @param idtype ֤
     */
    public void setIdtype(String idtype) {
        this.idtype = idtype == null ? null : idtype.trim();
    }

    /**
     * 获取用户查看的最后提示ID
     *
     * @return lastTipId - 用户查看的最后提示ID
     */
    public Long getLasttipid() {
        return lasttipid;
    }

    /**
     * 设置用户查看的最后提示ID
     *
     * @param lasttipid 用户查看的最后提示ID
     */
    public void setLasttipid(Long lasttipid) {
        this.lasttipid = lasttipid;
    }

    /**
     * 获取生日
     *
     * @return BirthDate - 生日
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
     * @return Address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return UserImage
     */
    public String getUserimage() {
        return userimage;
    }

    /**
     * @param userimage
     */
    public void setUserimage(String userimage) {
        this.userimage = userimage == null ? null : userimage.trim();
    }

    /**
     * 获取注册时间 
     *
     * @return RegisterTime - 注册时间 
     */
    public Date getRegistertime() {
        return registertime;
    }

    /**
     * 设置注册时间 
     *
     * @param registertime 注册时间 
     */
    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    /**
     * 获取账号使用的最后期限
     *
     * @return reEndTime - 账号使用的最后期限
     */
    public Date getReendtime() {
        return reendtime;
    }

    /**
     * 设置账号使用的最后期限
     *
     * @param reendtime 账号使用的最后期限
     */
    public void setReendtime(Date reendtime) {
        this.reendtime = reendtime;
    }

    /**
     * 获取最后登录时间 
     *
     * @return LoginTime - 最后登录时间 
     */
    public Date getLogintime() {
        return logintime;
    }

    /**
     * 设置最后登录时间 
     *
     * @param logintime 最后登录时间 
     */
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    /**
     * 获取退出时间 
     *
     * @return LogoutTime - 退出时间 
     */
    public Date getLogouttime() {
        return logouttime;
    }

    /**
     * 设置退出时间 
     *
     * @param logouttime 退出时间 
     */
    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    /**
     * 获取是否首次登录  1是  2否 
     *
     * @return firstLogin - 是否首次登录  1是  2否 
     */
    public Integer getFirstlogin() {
        return firstlogin;
    }

    /**
     * 设置是否首次登录  1是  2否 
     *
     * @param firstlogin 是否首次登录  1是  2否 
     */
    public void setFirstlogin(Integer firstlogin) {
        this.firstlogin = firstlogin;
    }

    /**
     * 获取是否删除标识，0----否；1-----是
     *
     * @return Flag - 是否删除标识，0----否；1-----是
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否删除标识，0----否；1-----是
     *
     * @param flag 是否删除标识，0----否；1-----是
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 获取登录密码
     *
     * @return Pwd - 登录密码
     */
    public byte[] getPwd() {
        return pwd;
    }

    /**
     * 设置登录密码
     *
     * @param pwd 登录密码
     */
    public void setPwd(byte[] pwd) {
        this.pwd = pwd;
    }
}
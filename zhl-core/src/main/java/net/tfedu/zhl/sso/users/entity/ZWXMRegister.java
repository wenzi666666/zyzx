package net.tfedu.zhl.sso.users.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "m_register")
public class ZWXMRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userName")
    private String username;

    /**
     * 1 系统用户 2qq用户3微信用户  4微博用户5游客账户
     */
    private Integer type;

    @Column(name = "bindPhone")
    private String bindphone;

    @Column(name = "thirdPartyNumber")
    private String thirdpartynumber;

    @Column(name = "registerTime")
    private Date registertime;

    private Boolean flag;

    /**
     * 账号来源:0--系统用户;1--电子书包用户
     */
    @Column(name = "fromFlag")
    private Integer fromflag;

    /**
     * 来自哪个项目
     */
    @Column(name = "fromProject")
    private String fromproject;

    @Column(name = "userPwd")
    private byte[] userpwd;

    public ZWXMRegister(Integer id, String username, Integer type, String bindphone, String thirdpartynumber, Date registertime, Boolean flag, Integer fromflag, String fromproject, byte[] userpwd) {
        this.id = id;
        this.username = username;
        this.type = type;
        this.bindphone = bindphone;
        this.thirdpartynumber = thirdpartynumber;
        this.registertime = registertime;
        this.flag = flag;
        this.fromflag = fromflag;
        this.fromproject = fromproject;
        this.userpwd = userpwd;
    }

    public ZWXMRegister() {
        super();
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取1 系统用户 2qq用户3微信用户  4微博用户5游客账户
     *
     * @return type - 1 系统用户 2qq用户3微信用户  4微博用户5游客账户
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1 系统用户 2qq用户3微信用户  4微博用户5游客账户
     *
     * @param type 1 系统用户 2qq用户3微信用户  4微博用户5游客账户
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return bindPhone
     */
    public String getBindphone() {
        return bindphone;
    }

    /**
     * @param bindphone
     */
    public void setBindphone(String bindphone) {
        this.bindphone = bindphone == null ? null : bindphone.trim();
    }

    /**
     * @return thirdPartyNumber
     */
    public String getThirdpartynumber() {
        return thirdpartynumber;
    }

    /**
     * @param thirdpartynumber
     */
    public void setThirdpartynumber(String thirdpartynumber) {
        this.thirdpartynumber = thirdpartynumber == null ? null : thirdpartynumber.trim();
    }

    /**
     * @return registerTime
     */
    public Date getRegistertime() {
        return registertime;
    }

    /**
     * @param registertime
     */
    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    /**
     * @return flag
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 获取账号来源:0--系统用户;1--电子书包用户
     *
     * @return fromFlag - 账号来源:0--系统用户;1--电子书包用户
     */
    public Integer getFromflag() {
        return fromflag;
    }

    /**
     * 设置账号来源:0--系统用户;1--电子书包用户
     *
     * @param fromflag 账号来源:0--系统用户;1--电子书包用户
     */
    public void setFromflag(Integer fromflag) {
        this.fromflag = fromflag;
    }

    /**
     * 获取来自哪个项目
     *
     * @return fromProject - 来自哪个项目
     */
    public String getFromproject() {
        return fromproject;
    }

    /**
     * 设置来自哪个项目
     *
     * @param fromproject 来自哪个项目
     */
    public void setFromproject(String fromproject) {
        this.fromproject = fromproject == null ? null : fromproject.trim();
    }

    /**
     * @return userPwd
     */
    public byte[] getUserpwd() {
        return userpwd;
    }

    /**
     * @param userpwd
     */
    public void setUserpwd(byte[] userpwd) {
        this.userpwd = userpwd;
    }
}
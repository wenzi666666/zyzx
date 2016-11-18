package net.tfedu.zhl.sso.app.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_tp_app")
public class SApp {
    /**
     * 开发者id
     */
    @Id
    @Column(name = "appId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appid;

    @Column(name = "appKey")
    private String appkey;

    /**
     * 开发者id
     */
    @Column(name = "developerId")
    private Integer developerid;

    /**
     * 基础数据访问网址（第三方接口适配器）
     */
    @Column(name = "baseDataURL")
    private String basedataurl;

    /**
     * 第三方基础数据访问网址
     */
    @Column(name = "thirdPartyBaseDataURL")
    private String thirdpartybasedataurl;

    /**
     * 第三方登录网址
     */
    @Column(name = "thirdPartyLoginURL")
    private String thirdpartyloginurl;

    /**
     * 第三方退出地址
     */
    @Column(name = "thirdPartyLogoutURL")
    private String thirdpartylogouturl;

    /**
     * 有效期（单位月）
     */
    @Column(name = "usefulLife")
    private Integer usefullife;

    /**
     * 最大用户数
     */
    @Column(name = "maxCount")
    private Integer maxcount;

    /**
     * 用户名前缀，防止对接用户跟本服务器用户冲突
     */
    private String prefix;

    /**
     * 第三方用户在我们平台的默认密码
     */
    @Column(name = "userDefaultPwd")
    private String userdefaultpwd;

    /**
     * 登录时自动同步基础信息  1同步  0不同步
     */
    @Column(name = "isLoginAutoSynBaseData")
    private Integer isloginautosynbasedata;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private Date createtime;

    /**
     * 逻辑删除 0正常 1删除
     */
    private Boolean flag;

    /**
     * 知好乐主页定制logo（最大64K）
     */
    @Column(name = "mainLogo")
    private byte[] mainlogo;

    public SApp(Integer appid, String appkey, Integer developerid, String basedataurl, String thirdpartybasedataurl, String thirdpartyloginurl, String thirdpartylogouturl, Integer usefullife, Integer maxcount, String prefix, String userdefaultpwd, Integer isloginautosynbasedata, Date createtime, Boolean flag, byte[] mainlogo) {
        this.appid = appid;
        this.appkey = appkey;
        this.developerid = developerid;
        this.basedataurl = basedataurl;
        this.thirdpartybasedataurl = thirdpartybasedataurl;
        this.thirdpartyloginurl = thirdpartyloginurl;
        this.thirdpartylogouturl = thirdpartylogouturl;
        this.usefullife = usefullife;
        this.maxcount = maxcount;
        this.prefix = prefix;
        this.userdefaultpwd = userdefaultpwd;
        this.isloginautosynbasedata = isloginautosynbasedata;
        this.createtime = createtime;
        this.flag = flag;
        this.mainlogo = mainlogo;
    }

    public SApp() {
        super();
    }

    /**
     * 获取开发者id
     *
     * @return appId - 开发者id
     */
    public Integer getAppid() {
        return appid;
    }

    /**
     * 设置开发者id
     *
     * @param appid 开发者id
     */
    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    /**
     * @return appKey
     */
    public String getAppkey() {
        return appkey;
    }

    /**
     * @param appkey
     */
    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    /**
     * 获取开发者id
     *
     * @return developerId - 开发者id
     */
    public Integer getDeveloperid() {
        return developerid;
    }

    /**
     * 设置开发者id
     *
     * @param developerid 开发者id
     */
    public void setDeveloperid(Integer developerid) {
        this.developerid = developerid;
    }

    /**
     * 获取基础数据访问网址（第三方接口适配器）
     *
     * @return baseDataURL - 基础数据访问网址（第三方接口适配器）
     */
    public String getBasedataurl() {
        return basedataurl;
    }

    /**
     * 设置基础数据访问网址（第三方接口适配器）
     *
     * @param basedataurl 基础数据访问网址（第三方接口适配器）
     */
    public void setBasedataurl(String basedataurl) {
        this.basedataurl = basedataurl == null ? null : basedataurl.trim();
    }

    /**
     * 获取第三方基础数据访问网址
     *
     * @return thirdPartyBaseDataURL - 第三方基础数据访问网址
     */
    public String getThirdpartybasedataurl() {
        return thirdpartybasedataurl;
    }

    /**
     * 设置第三方基础数据访问网址
     *
     * @param thirdpartybasedataurl 第三方基础数据访问网址
     */
    public void setThirdpartybasedataurl(String thirdpartybasedataurl) {
        this.thirdpartybasedataurl = thirdpartybasedataurl == null ? null : thirdpartybasedataurl.trim();
    }

    /**
     * 获取第三方登录网址
     *
     * @return thirdPartyLoginURL - 第三方登录网址
     */
    public String getThirdpartyloginurl() {
        return thirdpartyloginurl;
    }

    /**
     * 设置第三方登录网址
     *
     * @param thirdpartyloginurl 第三方登录网址
     */
    public void setThirdpartyloginurl(String thirdpartyloginurl) {
        this.thirdpartyloginurl = thirdpartyloginurl == null ? null : thirdpartyloginurl.trim();
    }

    /**
     * 获取第三方退出地址
     *
     * @return thirdPartyLogoutURL - 第三方退出地址
     */
    public String getThirdpartylogouturl() {
        return thirdpartylogouturl;
    }

    /**
     * 设置第三方退出地址
     *
     * @param thirdpartylogouturl 第三方退出地址
     */
    public void setThirdpartylogouturl(String thirdpartylogouturl) {
        this.thirdpartylogouturl = thirdpartylogouturl == null ? null : thirdpartylogouturl.trim();
    }

    /**
     * 获取有效期（单位月）
     *
     * @return usefulLife - 有效期（单位月）
     */
    public Integer getUsefullife() {
        return usefullife;
    }

    /**
     * 设置有效期（单位月）
     *
     * @param usefullife 有效期（单位月）
     */
    public void setUsefullife(Integer usefullife) {
        this.usefullife = usefullife;
    }

    /**
     * 获取最大用户数
     *
     * @return maxCount - 最大用户数
     */
    public Integer getMaxcount() {
        return maxcount;
    }

    /**
     * 设置最大用户数
     *
     * @param maxcount 最大用户数
     */
    public void setMaxcount(Integer maxcount) {
        this.maxcount = maxcount;
    }

    /**
     * 获取用户名前缀，防止对接用户跟本服务器用户冲突
     *
     * @return prefix - 用户名前缀，防止对接用户跟本服务器用户冲突
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * 设置用户名前缀，防止对接用户跟本服务器用户冲突
     *
     * @param prefix 用户名前缀，防止对接用户跟本服务器用户冲突
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix == null ? null : prefix.trim();
    }

    /**
     * 获取第三方用户在我们平台的默认密码
     *
     * @return userDefaultPwd - 第三方用户在我们平台的默认密码
     */
    public String getUserdefaultpwd() {
        return userdefaultpwd;
    }

    /**
     * 设置第三方用户在我们平台的默认密码
     *
     * @param userdefaultpwd 第三方用户在我们平台的默认密码
     */
    public void setUserdefaultpwd(String userdefaultpwd) {
        this.userdefaultpwd = userdefaultpwd == null ? null : userdefaultpwd.trim();
    }

    /**
     * 获取登录时自动同步基础信息  1同步  0不同步
     *
     * @return isLoginAutoSynBaseData - 登录时自动同步基础信息  1同步  0不同步
     */
    public Integer getIsloginautosynbasedata() {
        return isloginautosynbasedata;
    }

    /**
     * 设置登录时自动同步基础信息  1同步  0不同步
     *
     * @param isloginautosynbasedata 登录时自动同步基础信息  1同步  0不同步
     */
    public void setIsloginautosynbasedata(Integer isloginautosynbasedata) {
        this.isloginautosynbasedata = isloginautosynbasedata;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取逻辑删除 0正常 1删除
     *
     * @return flag - 逻辑删除 0正常 1删除
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置逻辑删除 0正常 1删除
     *
     * @param flag 逻辑删除 0正常 1删除
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 获取知好乐主页定制logo（最大64K）
     *
     * @return mainLogo - 知好乐主页定制logo（最大64K）
     */
    public byte[] getMainlogo() {
        return mainlogo;
    }

    /**
     * 设置知好乐主页定制logo（最大64K）
     *
     * @param mainlogo 知好乐主页定制logo（最大64K）
     */
    public void setMainlogo(byte[] mainlogo) {
        this.mainlogo = mainlogo;
    }
}
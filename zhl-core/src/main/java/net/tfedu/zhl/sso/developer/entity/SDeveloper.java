package net.tfedu.zhl.sso.developer.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "s_tp_developer")
public class SDeveloper {
    /**
     * 开发者id
     */
    @Id
    @Column(name = "developerId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer developerid;

    /**
     * 公司名称
     */
    @Column(name = "companyName")
    private String companyname;

    /**
     * 公司地址
     */
    @Column(name = "companyAddr")
    private String companyaddr;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号码
     */
    private Integer tel;

    /**
     * 邮箱
     */
    private String email;

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
     * 公司logo（最大16M）
     */
    @Column(name = "companyLogo")
    private byte[] companylogo;

    public SDeveloper(Integer developerid, String companyname, String companyaddr, String contact, Integer tel, String email, Date createtime, Boolean flag, byte[] companylogo) {
        this.developerid = developerid;
        this.companyname = companyname;
        this.companyaddr = companyaddr;
        this.contact = contact;
        this.tel = tel;
        this.email = email;
        this.createtime = createtime;
        this.flag = flag;
        this.companylogo = companylogo;
    }

    public SDeveloper() {
        super();
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
     * 获取公司名称
     *
     * @return companyName - 公司名称
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * 设置公司名称
     *
     * @param companyname 公司名称
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
    }

    /**
     * 获取公司地址
     *
     * @return companyAddr - 公司地址
     */
    public String getCompanyaddr() {
        return companyaddr;
    }

    /**
     * 设置公司地址
     *
     * @param companyaddr 公司地址
     */
    public void setCompanyaddr(String companyaddr) {
        this.companyaddr = companyaddr == null ? null : companyaddr.trim();
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * 获取手机号码
     *
     * @return tel - 手机号码
     */
    public Integer getTel() {
        return tel;
    }

    /**
     * 设置手机号码
     *
     * @param tel 手机号码
     */
    public void setTel(Integer tel) {
        this.tel = tel;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
     * 获取公司logo（最大16M）
     *
     * @return companyLogo - 公司logo（最大16M）
     */
    public byte[] getCompanylogo() {
        return companylogo;
    }

    /**
     * 设置公司logo（最大16M）
     *
     * @param companylogo 公司logo（最大16M）
     */
    public void setCompanylogo(byte[] companylogo) {
        this.companylogo = companylogo;
    }
}
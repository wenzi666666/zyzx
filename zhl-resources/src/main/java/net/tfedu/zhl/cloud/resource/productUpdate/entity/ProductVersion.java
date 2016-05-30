package net.tfedu.zhl.cloud.resource.productUpdate.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ProductVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String versionid;

    private String productcode;

    private Long versioncode;

    private String versionname;

    /**
     * 创建时间
     */
    private Date createtime;

    private String description;

    public ProductVersion(String versionid, String productcode, Long versioncode, String versionname, Date createtime, String description) {
        this.versionid = versionid;
        this.productcode = productcode;
        this.versioncode = versioncode;
        this.versionname = versionname;
        this.createtime = createtime;
        this.description = description;
    }

    public ProductVersion() {
        super();
    }

    /**
     * @return versionid
     */
    public String getVersionid() {
        return versionid;
    }

    /**
     * @param versionid
     */
    public void setVersionid(String versionid) {
        this.versionid = versionid == null ? null : versionid.trim();
    }

    /**
     * @return productcode
     */
    public String getProductcode() {
        return productcode;
    }

    /**
     * @param productcode
     */
    public void setProductcode(String productcode) {
        this.productcode = productcode == null ? null : productcode.trim();
    }

    /**
     * @return versioncode
     */
    public Long getVersioncode() {
        return versioncode;
    }

    /**
     * @param versioncode
     */
    public void setVersioncode(Long versioncode) {
        this.versioncode = versioncode;
    }

    /**
     * @return versionname
     */
    public String getVersionname() {
        return versionname;
    }

    /**
     * @param versionname
     */
    public void setVersionname(String versionname) {
        this.versionname = versionname == null ? null : versionname.trim();
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}
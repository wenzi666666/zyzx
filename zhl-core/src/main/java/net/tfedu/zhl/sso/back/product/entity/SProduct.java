package net.tfedu.zhl.sso.back.product.entity;

import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Table(name = "s_product")
public class SProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品名称
     */
    @NotEmpty(message="产品名称不能为空")
    private String name;

    /**
     * 产品编码
     */
    @NotEmpty(message="产品编码不能为空")
    private String code;

    private Boolean flag;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 创建人
     */
    private Long createman;

    /**
     * 注释
     */
    private String note;

    public SProduct(Long id, String name, String code, Boolean flag, Date createtime, Long createman, String note) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.flag = flag;
        this.createtime = createtime;
        this.createman = createman;
        this.note = note;
    }

    public SProduct() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取产品名称
     *
     * @return name - 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名称
     *
     * @param name 产品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取产品编码
     *
     * @return code - 产品编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置产品编码
     *
     * @param code 产品编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 获取创建人
     *
     * @return createman - 创建人
     */
    public Long getCreateman() {
        return createman;
    }

    /**
     * 设置创建人
     *
     * @param createman 创建人
     */
    public void setCreateman(Long createman) {
        this.createman = createman;
    }

    /**
     * 获取注释
     *
     * @return note - 注释
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置注释
     *
     * @param note 注释
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}
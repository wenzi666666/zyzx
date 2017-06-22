package net.tfedu.zhl.cloud.teaching.personalblog.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "personal_blog_praise_record")
public class PersonalBlogPraiseRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8283922931048232373L;

	/**
     * 记录主键
     */
    @Id
    @SequenceGenerator(name="uuid",sequenceName="SELECT LAST_INSERT_ID()")
    private String uuid;

    /**
     * 博客主键
     */
    @Column(name = "blog_uuid")
    private String blogUuid;

    /**
     * 赞扬人
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 赞扬时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标识默认未删除0
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    public PersonalBlogPraiseRecord(String uuid, String blogUuid, Long userId, Date createTime, Boolean deleteFlag) {
        this.uuid = uuid;
        this.blogUuid = blogUuid;
        this.userId = userId;
        this.createTime = createTime;
        this.deleteFlag = deleteFlag;
    }

    public PersonalBlogPraiseRecord() {
        super();
    }

    /**
     * 获取记录主键
     *
     * @return uuid - 记录主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置记录主键
     *
     * @param uuid 记录主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取博客主键
     *
     * @return blog_uuid - 博客主键
     */
    public String getBlogUuid() {
        return blogUuid;
    }

    /**
     * 设置博客主键
     *
     * @param blogUuid 博客主键
     */
    public void setBlogUuid(String blogUuid) {
        this.blogUuid = blogUuid == null ? null : blogUuid.trim();
    }

    /**
     * 获取赞扬人
     *
     * @return user_id - 赞扬人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置赞扬人
     *
     * @param userId 赞扬人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取赞扬时间
     *
     * @return create_time - 赞扬时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置赞扬时间
     *
     * @param createTime 赞扬时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取删除标识默认未删除0
     *
     * @return delete_flag - 删除标识默认未删除0
     */
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标识默认未删除0
     *
     * @param deleteFlag 删除标识默认未删除0
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
package net.tfedu.zhl.cloud.teaching.personalblog.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "personal_blog_comment")
public class PersonalBlogComment implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4520064530804978331L;

	/**
     * 评论主键
     */
    @Id
    @GeneratedValue(generator="UUID")
    private String uuid;

    /**
     * 博客主键
     */
    @Column(name = "blog_uuid")
    @NotNull(message="评论的个人反思主键不能为空")
    private String blogUuid;

    /**
     * 评论人
     */
    @Column(name = "user_id")
    @NotNull(message="评论人不能为空")
   private Long userId;

    /**
     * 评论时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 删除标识默认未删除0
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    /**
     * 内容
     */
    @NotNull(message="评论内容不能为空")
   private String content;
    

    public PersonalBlogComment(String uuid, String blogUuid, Long userId, Date createTime, Boolean deleteFlag, String content) {
        this.uuid = uuid;
        this.blogUuid = blogUuid;
        this.userId = userId;
        this.createTime = createTime;
        this.deleteFlag = deleteFlag;
        this.content = content;
    }

    public PersonalBlogComment() {
        super();
    }

    /**
     * 获取评论主键
     *
     * @return uuid - 评论主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置评论主键
     *
     * @param uuid 评论主键
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
     * 获取评论人
     *
     * @return user_id - 评论人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置评论人
     *
     * @param userId 评论人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取评论时间
     *
     * @return create_time - 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置评论时间
     *
     * @param createTime 评论时间
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

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
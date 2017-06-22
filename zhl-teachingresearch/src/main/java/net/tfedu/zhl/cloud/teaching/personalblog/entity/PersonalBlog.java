package net.tfedu.zhl.cloud.teaching.personalblog.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "personal_blog")
public class PersonalBlog  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2195226315736409833L;

	/**
     * 博客主键
     */
    @Id
    @SequenceGenerator(name="uuid",sequenceName="SELECT LAST_INSERT_ID()")
    private String uuid;

    /**
     * 创建人
     */
    @Column(name = "user_id")
    @NotNull(message= "创建人id不能为空")
    private Long userId;

    /**
     * 标题
     */
    @NotNull(message= "标题不能为空")
    private String title;

    /**
     * 开放范围P私密、S校、D区
     */
    @NotNull(message= "开放范围不能为空")
    private String scope;

    /**
     * 地区或学校的id
     */
    @NotNull(message= "开放范围id不能为空")
    private Long scopeid;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除标识默认未删除0
     */
    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    /**
     * 浏览次数
     */
    @Column(name = "click_num")
    private Integer clickNum;

    /**
     * 点赞次数
     */
    @Column(name = "praise_num")
    private Integer praiseNum;

    /**
     * 评论次数
     */
    @Column(name = "comment_num")
    private Integer commentNum;

    /**
     * 内容
     */
    private String content;

    public PersonalBlog(String uuid, Long userId, String title, String scope, Long scopeid, Date createTime, Date updateTime, Boolean deleteFlag, Integer clickNum, Integer praiseNum, Integer commentNum, String content) {
        this.uuid = uuid;
        this.userId = userId;
        this.title = title;
        this.scope = scope;
        this.scopeid = scopeid;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteFlag = deleteFlag;
        this.clickNum = clickNum;
        this.praiseNum = praiseNum;
        this.commentNum = commentNum;
        this.content = content;
    }

    public PersonalBlog() {
        super();
    }

    /**
     * 获取博客主键
     *
     * @return uuid - 博客主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置博客主键
     *
     * @param uuid 博客主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取创建人
     *
     * @return user_id - 创建人
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置创建人
     *
     * @param userId 创建人
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取开放范围P私密、S校、D区
     *
     * @return scope - 开放范围P私密、S校、D区
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置开放范围P私密、S校、D区
     *
     * @param scope 开放范围P私密、S校、D区
     */
    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    /**
     * 获取地区或学校的id
     *
     * @return scopeid - 地区或学校的id
     */
    public Long getScopeid() {
        return scopeid;
    }

    /**
     * 设置地区或学校的id
     *
     * @param scopeid 地区或学校的id
     */
    public void setScopeid(Long scopeid) {
        this.scopeid = scopeid;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
     * 获取浏览次数
     *
     * @return click_num - 浏览次数
     */
    public Integer getClickNum() {
        return clickNum;
    }

    /**
     * 设置浏览次数
     *
     * @param clickNum 浏览次数
     */
    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    /**
     * 获取点赞次数
     *
     * @return praise_num - 点赞次数
     */
    public Integer getPraiseNum() {
        return praiseNum;
    }

    /**
     * 设置点赞次数
     *
     * @param praiseNum 点赞次数
     */
    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    /**
     * 获取评论次数
     *
     * @return comment_num - 评论次数
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * 设置评论次数
     *
     * @param commentNum 评论次数
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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
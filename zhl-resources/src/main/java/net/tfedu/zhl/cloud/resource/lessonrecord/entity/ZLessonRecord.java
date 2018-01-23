package net.tfedu.zhl.cloud.resource.lessonrecord.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "z_lesson_record")
public class ZLessonRecord {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 课程标题
     */
    @Column(name = "lesson_title")
    private String lessonTitle;

    @Column(name = "teacher_truename")
    private String teacherTruename;

    @Column(name = "lesson_time")
    private Date lessonTime;

    @Column(name = "lesson_place")
    private String lessonPlace;

    @Column(name = "lesson_tfcode")
    private String lessonTfcode;

    /**
     * 刪除标示
     */
    private Boolean flag;

    @Column(name = "lesson_content")
    private String lessonContent;

    public ZLessonRecord(Long id, Long userId, Date createTime, String lessonTitle, String teacherTruename, Date lessonTime, String lessonPlace, String lessonTfcode, Boolean flag, String lessonContent) {
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.lessonTitle = lessonTitle;
        this.teacherTruename = teacherTruename;
        this.lessonTime = lessonTime;
        this.lessonPlace = lessonPlace;
        this.lessonTfcode = lessonTfcode;
        this.flag = flag;
        this.lessonContent = lessonContent;
    }

    public ZLessonRecord() {
        super();
    }

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
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
     * 获取课程标题
     *
     * @return lesson_title - 课程标题
     */
    public String getLessonTitle() {
        return lessonTitle;
    }

    /**
     * 设置课程标题
     *
     * @param lessonTitle 课程标题
     */
    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle == null ? null : lessonTitle.trim();
    }

    /**
     * @return teacher_truename
     */
    public String getTeacherTruename() {
        return teacherTruename;
    }

    /**
     * @param teacherTruename
     */
    public void setTeacherTruename(String teacherTruename) {
        this.teacherTruename = teacherTruename == null ? null : teacherTruename.trim();
    }

    /**
     * @return lesson_time
     */
    public Date getLessonTime() {
        return lessonTime;
    }

    /**
     * @param lessonTime
     */
    public void setLessonTime(Date lessonTime) {
        this.lessonTime = lessonTime;
    }

    /**
     * @return lesson_place
     */
    public String getLessonPlace() {
        return lessonPlace;
    }

    /**
     * @param lessonPlace
     */
    public void setLessonPlace(String lessonPlace) {
        this.lessonPlace = lessonPlace == null ? null : lessonPlace.trim();
    }

    /**
     * @return lesson_tfcode
     */
    public String getLessonTfcode() {
        return lessonTfcode;
    }

    /**
     * @param lessonTfcode
     */
    public void setLessonTfcode(String lessonTfcode) {
        this.lessonTfcode = lessonTfcode == null ? null : lessonTfcode.trim();
    }

    /**
     * 获取刪除标示
     *
     * @return flag - 刪除标示
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置刪除标示
     *
     * @param flag 刪除标示
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * @return lesson_content
     */
    public String getLessonContent() {
        return lessonContent;
    }

    /**
     * @param lessonContent
     */
    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent == null ? null : lessonContent.trim();
    }
}
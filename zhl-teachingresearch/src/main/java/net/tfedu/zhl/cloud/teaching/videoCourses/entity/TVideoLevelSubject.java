package net.tfedu.zhl.cloud.teaching.videoCourses.entity;

import javax.persistence.*;

@Table(name = "t_videolevel_subject")
public class TVideoLevelSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频课程等级id
     */
    @Column(name = "levelId")
    private Integer levelid;

    /**
     * 学科id
     */
    @Column(name = "subjectId")
    private Integer subjectid;

    public TVideoLevelSubject(Integer id, Integer levelid, Integer subjectid) {
        this.id = id;
        this.levelid = levelid;
        this.subjectid = subjectid;
    }

    public TVideoLevelSubject() {
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
     * 获取视频课程等级id
     *
     * @return levelId - 视频课程等级id
     */
    public Integer getLevelid() {
        return levelid;
    }

    /**
     * 设置视频课程等级id
     *
     * @param levelid 视频课程等级id
     */
    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    /**
     * 获取学科id
     *
     * @return subjectId - 学科id
     */
    public Integer getSubjectid() {
        return subjectid;
    }

    /**
     * 设置学科id
     *
     * @param subjectid 学科id
     */
    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }
}
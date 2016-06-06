package net.tfedu.zhl.cloud.teaching.videoCourses.entity;

import javax.persistence.*;

@Table(name = "t_videolevel")
public class TVideoLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 视频课程等级名称
     */
    private String name;

    public TVideoLevel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TVideoLevel() {
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
     * 获取视频课程等级名称
     *
     * @return name - 视频课程等级名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置视频课程等级名称
     *
     * @param name 视频课程等级名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}
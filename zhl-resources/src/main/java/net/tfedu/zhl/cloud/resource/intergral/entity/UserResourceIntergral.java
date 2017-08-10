package net.tfedu.zhl.cloud.resource.intergral.entity;

import javax.persistence.*;

@Table(name = "user_resource_intergral")
public class UserResourceIntergral {
    /**
     * 用户唯一主键
     */
    @Id
    private Long userid;

    /**
     * 总积分
     */
    @Column(name = "total_score")
    private Integer totalScore;

    /**
     * 可用积分
     */
    @Column(name = "useable_score")
    private Integer useableScore;

    public UserResourceIntergral(Long userid, Integer totalScore, Integer useableScore) {
        this.userid = userid;
        this.totalScore = totalScore;
        this.useableScore = useableScore;
    }

    public UserResourceIntergral() {
        super();
    }

    /**
     * 获取用户唯一主键
     *
     * @return userid - 用户唯一主键
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置用户唯一主键
     *
     * @param userid 用户唯一主键
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取总积分
     *
     * @return total_score - 总积分
     */
    public Integer getTotalScore() {
        return totalScore;
    }

    /**
     * 设置总积分
     *
     * @param totalScore 总积分
     */
    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * 获取可用积分
     *
     * @return useable_score - 可用积分
     */
    public Integer getUseableScore() {
        return useableScore;
    }

    /**
     * 设置可用积分
     *
     * @param useableScore 可用积分
     */
    public void setUseableScore(Integer useableScore) {
        this.useableScore = useableScore;
    }
}
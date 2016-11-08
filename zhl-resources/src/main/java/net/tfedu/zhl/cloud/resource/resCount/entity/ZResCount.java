package net.tfedu.zhl.cloud.resource.resCount.entity;

import javax.persistence.*;

@Table(name = "z_res_count")
public class ZResCount {
    @Id
    private String tfcode;

    /**
     * 动画数量
     */
    @Column(name = "dh_count")
    private Integer dhCount;

    /**
     * 微课数量
     */
    @Column(name = "wk_count")
    private Integer wkCount;

    /**
     * 实验视频数量
     */
    @Column(name = "sy_count")
    private Integer syCount;

    /**
     * 素材数量
     */
    @Column(name = "sc_count")
    private Integer scCount;

    /**
     * 教辅数量
     */
    @Column(name = "jf_count")
    private Integer jfCount;

    /**
     * 案例数量
     */
    @Column(name = "al_count")
    private Integer alCount;

    public ZResCount(String tfcode, Integer dhCount, Integer wkCount, Integer syCount, Integer scCount, Integer jfCount, Integer alCount) {
        this.tfcode = tfcode;
        this.dhCount = dhCount;
        this.wkCount = wkCount;
        this.syCount = syCount;
        this.scCount = scCount;
        this.jfCount = jfCount;
        this.alCount = alCount;
    }

    public ZResCount() {
        super();
    }

    /**
     * @return tfcode
     */
    public String getTfcode() {
        return tfcode;
    }

    /**
     * @param tfcode
     */
    public void setTfcode(String tfcode) {
        this.tfcode = tfcode == null ? null : tfcode.trim();
    }

    /**
     * 获取动画数量
     *
     * @return dh_count - 动画数量
     */
    public Integer getDhCount() {
        return dhCount;
    }

    /**
     * 设置动画数量
     *
     * @param dhCount 动画数量
     */
    public void setDhCount(Integer dhCount) {
        this.dhCount = dhCount;
    }

    /**
     * 获取微课数量
     *
     * @return wk_count - 微课数量
     */
    public Integer getWkCount() {
        return wkCount;
    }

    /**
     * 设置微课数量
     *
     * @param wkCount 微课数量
     */
    public void setWkCount(Integer wkCount) {
        this.wkCount = wkCount;
    }

    /**
     * 获取实验视频数量
     *
     * @return sy_count - 实验视频数量
     */
    public Integer getSyCount() {
        return syCount;
    }

    /**
     * 设置实验视频数量
     *
     * @param syCount 实验视频数量
     */
    public void setSyCount(Integer syCount) {
        this.syCount = syCount;
    }

    /**
     * 获取素材数量
     *
     * @return sc_count - 素材数量
     */
    public Integer getScCount() {
        return scCount;
    }

    /**
     * 设置素材数量
     *
     * @param scCount 素材数量
     */
    public void setScCount(Integer scCount) {
        this.scCount = scCount;
    }

    /**
     * 获取教辅数量
     *
     * @return jf_count - 教辅数量
     */
    public Integer getJfCount() {
        return jfCount;
    }

    /**
     * 设置教辅数量
     *
     * @param jfCount 教辅数量
     */
    public void setJfCount(Integer jfCount) {
        this.jfCount = jfCount;
    }

    /**
     * 获取案例数量
     *
     * @return al_count - 案例数量
     */
    public Integer getAlCount() {
        return alCount;
    }

    /**
     * 设置案例数量
     *
     * @param alCount 案例数量
     */
    public void setAlCount(Integer alCount) {
        this.alCount = alCount;
    }
}
package net.tfedu.zhl.cloud.resource.asset.entity;

import javax.persistence.*;

@Table(name = "z_teaching_plan_content")
public class ZTeachingPlanContent {
    /**
     * 自增主键
     */
    @Id
    private Long assetid;

    /**
     * 教案内容
     */
    private String content;

    public ZTeachingPlanContent(Long assetid, String content) {
        this.assetid = assetid;
        this.content = content;
    }

    public ZTeachingPlanContent() {
        super();
    }

    /**
     * 获取自增主键
     *
     * @return assetid - 自增主键
     */
    public Long getAssetid() {
        return assetid;
    }

    /**
     * 设置自增主键
     *
     * @param assetid 自增主键
     */
    public void setAssetid(Long assetid) {
        this.assetid = assetid;
    }

    /**
     * 获取教案内容
     *
     * @return content - 教案内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置教案内容
     *
     * @param content 教案内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
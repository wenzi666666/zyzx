package net.tfedu.zhl.cloud.resource.intergral.entity;

import javax.persistence.*;

@Table(name = "resource_intergral_operate_type")
public class ResourceIntergralOperateType {
    /**
     * 操作编码
     */
    @Id
    private String code;

    /**
     * 操作
     */
    private String name;

    /**
     * 操作解释
     */
    private String decipher;

    /**
     * 操作默认消耗积分，默认为0，即根据实际情况判断
     */
    private Integer defaultscore;

    public ResourceIntergralOperateType(String code, String name, String decipher, Integer defaultscore) {
        this.code = code;
        this.name = name;
        this.decipher = decipher;
        this.defaultscore = defaultscore;
    }

    public ResourceIntergralOperateType() {
        super();
    }

    /**
     * 获取操作编码
     *
     * @return code - 操作编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置操作编码
     *
     * @param code 操作编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取操作
     *
     * @return name - 操作
     */
    public String getName() {
        return name;
    }

    /**
     * 设置操作
     *
     * @param name 操作
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取操作解释
     *
     * @return decipher - 操作解释
     */
    public String getDecipher() {
        return decipher;
    }

    /**
     * 设置操作解释
     *
     * @param decipher 操作解释
     */
    public void setDecipher(String decipher) {
        this.decipher = decipher == null ? null : decipher.trim();
    }

    /**
     * 获取操作默认消耗积分，默认为0，即根据实际情况判断
     *
     * @return defaultscore - 操作默认消耗积分，默认为0，即根据实际情况判断
     */
    public Integer getDefaultscore() {
        return defaultscore;
    }

    /**
     * 设置操作默认消耗积分，默认为0，即根据实际情况判断
     *
     * @param defaultscore 操作默认消耗积分，默认为0，即根据实际情况判断
     */
    public void setDefaultscore(Integer defaultscore) {
        this.defaultscore = defaultscore;
    }
}
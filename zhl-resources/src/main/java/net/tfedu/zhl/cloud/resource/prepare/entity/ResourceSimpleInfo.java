package net.tfedu.zhl.cloud.resource.prepare.entity;

/**
 * 资源（自建、系统、区校本资源）的简版信息 用于 mpper中返回resultMap
 * 
 * @author wangwr
 *
 */
public class ResourceSimpleInfo {

    /**
     * 资源id
     */
    private Long resid;

    /**
     * 资源code
     */
    private String rescode;

    /**
     * 文件名称
     */
    private String title;

    /**
     * 资源来源0系统资源，1自建资源，2共享资源,3校本资源,4区本资源
     */
    private Integer fromflag;

    /**
     * 是否网络资源
     */
    private Boolean isnet;

    /**
     * 是否 多文件
     */
    private Boolean isdwj;

    /**
     * 文件路径
     */
    private String path;

    public String getRescode() {
        return rescode;
    }

    public void setRescode(String rescode) {
        this.rescode = rescode;
    }

    public Boolean getIsnet() {
        return isnet;
    }

    public void setIsnet(Boolean isnet) {
        this.isnet = isnet;
    }

    public Boolean getIsdwj() {
        return isdwj;
    }

    public void setIsdwj(Boolean isdwj) {
        this.isdwj = isdwj;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getFromflag() {
        return fromflag;
    }

    public void setFromflag(Integer fromflag) {
        this.fromflag = fromflag;
    }

    public Long getResid() {
        return resid;
    }

    public void setResid(Long resid) {
        this.resid = resid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new StringBuffer().append("[\"ResourceSimpleInfo\":{").append("\"resid\":\"" + resid + "\";")
                .append("\"rescode\":\"" + rescode + "\";").append("\"title\":\"" + title + "\";")
                .append("\"fromflag\":\"" + fromflag + "\";").append("\"isnet\":\"" + isnet + "\";")
                .append("\"isdwj\":\"" + isdwj + "\";").append("\"path\":\"" + path + "\"").append("}]").toString();
    }

}

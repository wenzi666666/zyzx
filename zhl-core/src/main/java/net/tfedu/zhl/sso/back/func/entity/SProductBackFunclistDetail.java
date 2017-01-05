package net.tfedu.zhl.sso.back.func.entity;

import javax.persistence.*;

@Table(name = "s_product_back_funclist_detail")
public class SProductBackFunclistDetail {
    /**
     * 自增id
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 功能Id  属于哪个功能
     */
    @Column(name = "funcId")
    private Long funcid;

    /**
     * 功能名称
     */
    @Column(name = "Name")
    private String name;

    /**
     * code码
     */
    private String code;

    /**
     * 功能描述
     */
    @Column(name = "FuncDesc")
    private String funcdesc;

    /**
     * 路径
     */
    @Column(name = "Path")
    private String path;

    /**
     * 功能所属区域
     */
    @Column(name = "Area")
    private String area;

    /**
     * 范围 1全国 2省 3市 4区 5校
     */
    private Integer scope;

    /**
     * 0-----基础功能；1------不是基础功能
     */
    @Column(name = "IsBase")
    private Boolean isbase;

    /**
     * 是否免费，0----免费；1-----不免费
     */
    @Column(name = "IsFree")
    private Boolean isfree;

    /**
     * 功能表，0----可用；1-----不可用
     */
    @Column(name = "IsEnabled")
    private Boolean isenabled;

    /**
     * 是否删除标示，0-----未删除；1----删除
     */
    @Column(name = "Flag")
    private Boolean flag;

    /**
     * 能功子集（父节点）
     */
    private Long pid;

    /**
     * 图标路径
     */
    @Column(name = "iconPath")
    private String iconpath;

    public SProductBackFunclistDetail(Long id, Long funcid, String name, String code, String funcdesc, String path, String area, Integer scope, Boolean isbase, Boolean isfree, Boolean isenabled, Boolean flag, Long pid, String iconpath) {
        this.id = id;
        this.funcid = funcid;
        this.name = name;
        this.code = code;
        this.funcdesc = funcdesc;
        this.path = path;
        this.area = area;
        this.scope = scope;
        this.isbase = isbase;
        this.isfree = isfree;
        this.isenabled = isenabled;
        this.flag = flag;
        this.pid = pid;
        this.iconpath = iconpath;
    }

    public SProductBackFunclistDetail() {
        super();
    }

    /**
     * 获取自增id
     *
     * @return Id - 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取功能Id  属于哪个功能
     *
     * @return funcId - 功能Id  属于哪个功能
     */
    public Long getFuncid() {
        return funcid;
    }

    /**
     * 设置功能Id  属于哪个功能
     *
     * @param funcid 功能Id  属于哪个功能
     */
    public void setFuncid(Long funcid) {
        this.funcid = funcid;
    }

    /**
     * 获取功能名称
     *
     * @return Name - 功能名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置功能名称
     *
     * @param name 功能名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取code码
     *
     * @return code - code码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code码
     *
     * @param code code码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取功能描述
     *
     * @return FuncDesc - 功能描述
     */
    public String getFuncdesc() {
        return funcdesc;
    }

    /**
     * 设置功能描述
     *
     * @param funcdesc 功能描述
     */
    public void setFuncdesc(String funcdesc) {
        this.funcdesc = funcdesc == null ? null : funcdesc.trim();
    }

    /**
     * 获取路径
     *
     * @return Path - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取功能所属区域
     *
     * @return Area - 功能所属区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置功能所属区域
     *
     * @param area 功能所属区域
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 获取范围 1全国 2省 3市 4区 5校
     *
     * @return scope - 范围 1全国 2省 3市 4区 5校
     */
    public Integer getScope() {
        return scope;
    }

    /**
     * 设置范围 1全国 2省 3市 4区 5校
     *
     * @param scope 范围 1全国 2省 3市 4区 5校
     */
    public void setScope(Integer scope) {
        this.scope = scope;
    }

    /**
     * 获取0-----基础功能；1------不是基础功能
     *
     * @return IsBase - 0-----基础功能；1------不是基础功能
     */
    public Boolean getIsbase() {
        return isbase;
    }

    /**
     * 设置0-----基础功能；1------不是基础功能
     *
     * @param isbase 0-----基础功能；1------不是基础功能
     */
    public void setIsbase(Boolean isbase) {
        this.isbase = isbase;
    }

    /**
     * 获取是否免费，0----免费；1-----不免费
     *
     * @return IsFree - 是否免费，0----免费；1-----不免费
     */
    public Boolean getIsfree() {
        return isfree;
    }

    /**
     * 设置是否免费，0----免费；1-----不免费
     *
     * @param isfree 是否免费，0----免费；1-----不免费
     */
    public void setIsfree(Boolean isfree) {
        this.isfree = isfree;
    }

    /**
     * 获取功能表，0----可用；1-----不可用
     *
     * @return IsEnabled - 功能表，0----可用；1-----不可用
     */
    public Boolean getIsenabled() {
        return isenabled;
    }

    /**
     * 设置功能表，0----可用；1-----不可用
     *
     * @param isenabled 功能表，0----可用；1-----不可用
     */
    public void setIsenabled(Boolean isenabled) {
        this.isenabled = isenabled;
    }

    /**
     * 获取是否删除标示，0-----未删除；1----删除
     *
     * @return Flag - 是否删除标示，0-----未删除；1----删除
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否删除标示，0-----未删除；1----删除
     *
     * @param flag 是否删除标示，0-----未删除；1----删除
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * 获取能功子集（父节点）
     *
     * @return pid - 能功子集（父节点）
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置能功子集（父节点）
     *
     * @param pid 能功子集（父节点）
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取图标路径
     *
     * @return iconPath - 图标路径
     */
    public String getIconpath() {
        return iconpath;
    }

    /**
     * 设置图标路径
     *
     * @param iconpath 图标路径
     */
    public void setIconpath(String iconpath) {
        this.iconpath = iconpath == null ? null : iconpath.trim();
    }
}
package net.tfedu.zhl.cloud.resource.navigation.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.tfedu.zhl.sso.subject.entity.JSubject;

@Table(name = "j_term")
public class Term implements Serializable {
    private static final long serialVersionUID = 8160030266758656476L;

    /**
     * 自增id
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 学段名称
     */
    @Column(name = "Name")
    private String name;

    /**
     * 备注
     */
    @Column(name = "Notes")
    private String notes;

    /**
     * 是否删除标示，0-----未删除；1----删除
     */
    private Boolean flag;

    private String code;

    // 学段下对应的学科
    @Transient
    private List<JSubject> subjects;

    public List<JSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<JSubject> subjects) {
        this.subjects = subjects;
    }

    public Term(Long id, String name, String notes, Boolean flag, String code) {
        this.id = id;
        this.name = name;
        this.notes = notes;
        this.flag = flag;
        this.code = code;
    }

    public Term() {
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
     * @param id
     *            自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取学段名称
     *
     * @return Name - 学段名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置学段名称
     *
     * @param name
     *            学段名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取备注
     *
     * @return Notes - 备注
     */
    public String getNotes() {
        return notes;
    }

    /**
     * 设置备注
     *
     * @param notes
     *            备注
     */
    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    /**
     * 获取是否删除标示，0-----未删除；1----删除
     *
     * @return flag - 是否删除标示，0-----未删除；1----删除
     */
    public Boolean getFlag() {
        return flag;
    }

    /**
     * 设置是否删除标示，0-----未删除；1----删除
     *
     * @param flag
     *            是否删除标示，0-----未删除；1----删除
     */
    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 提供toString方法，方便单元测试时，直接输出对象。
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", subjects=" + subjects + "]";
    }
}

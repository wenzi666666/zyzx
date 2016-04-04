package net.tfedu.zhl.cloud.resource.resPreview.entity;

public class ResNavEntity {

    // 目录id
    private long id;

    // 目录名称
    private String name;

    // tfcode
    private String tfcode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTfcode() {
        return tfcode;
    }

    public void setTfcode(String tfcode) {
        this.tfcode = tfcode;
    }

    @Override
    public String toString() {
        return "resNav[id=" + id + ",name=" + name + ",tfcode=" + tfcode + "]";
    }
}

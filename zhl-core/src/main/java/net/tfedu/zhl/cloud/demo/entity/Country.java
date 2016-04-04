package net.tfedu.zhl.cloud.demo.entity;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "zhl_country")
public class Country implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8160030266758656476L;
    @Id
    private Integer id;
    private String ccode;
    private String cname;

    public Country() {

    }

    public Country(Integer id, String ccode, String cname) {
        super();
        this.id = id;
        this.ccode = ccode;
        this.cname = cname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
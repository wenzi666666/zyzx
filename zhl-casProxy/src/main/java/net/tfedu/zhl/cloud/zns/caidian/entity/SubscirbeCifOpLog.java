package net.tfedu.zhl.cloud.zns.caidian.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "subscribe_cif_oplog")
public class SubscirbeCifOpLog {
    @Id
    private Integer olgid;

    private String objectname;

    private Date opdatetime;

    private String optype;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public SubscirbeCifOpLog(Integer olgid, String objectname, Date opdatetime, String optype, String guid) {
        this.olgid = olgid;
        this.objectname = objectname;
        this.opdatetime = opdatetime;
        this.optype = optype;
        this.guid = guid;
    }

    public SubscirbeCifOpLog() {
        super();
    }

    /**
     * @return olgid
     */
    public Integer getOlgid() {
        return olgid;
    }

    /**
     * @param olgid
     */
    public void setOlgid(Integer olgid) {
        this.olgid = olgid;
    }

    /**
     * @return objectname
     */
    public String getObjectname() {
        return objectname;
    }

    /**
     * @param objectname
     */
    public void setObjectname(String objectname) {
        this.objectname = objectname == null ? null : objectname.trim();
    }

    /**
     * @return opdatetime
     */
    public Date getOpdatetime() {
        return opdatetime;
    }

    /**
     * @param opdatetime
     */
    public void setOpdatetime(Date opdatetime) {
        this.opdatetime = opdatetime;
    }

    /**
     * @return optype
     */
    public String getOptype() {
        return optype;
    }

    /**
     * @param optype
     */
    public void setOptype(String optype) {
        this.optype = optype == null ? null : optype.trim();
    }

    /**
     * @return guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }
}
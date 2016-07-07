package net.tfedu.zhl.sso.th_register.entity;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "s_th_register_relative")
public class SThirdRegisterRelative implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对接过程中第三方用户的用户名
     */
    @Column(name = "th_username")
    private String thUsername;

    /**
     * 对接后第三方用户在知好乐系统中的用户名
     */
    @Column(name = "zhl_username")
    private String zhlUsername;

    /**
     * 第三方系统的编码
     */
    @Column(name = "th_code")
    private String thCode;
    
    
    /**
     * 对接后第三方用户在知好乐系统中的用户id
     */
    private String zhlUserid;
    
    

    public SThirdRegisterRelative(Long id, String thUsername, String zhlUsername, String thCode,String zhlUserid) {
        this.id = id;
        this.thUsername = thUsername;
        this.zhlUsername = zhlUsername;
        this.thCode = thCode;
        this.zhlUserid = zhlUserid;
    }

    public SThirdRegisterRelative() {
        super();
    }

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取对接过程中第三方用户的用户名
     *
     * @return th_username - 对接过程中第三方用户的用户名
     */
    public String getThUsername() {
        return thUsername;
    }

    /**
     * 设置对接过程中第三方用户的用户名
     *
     * @param thUsername 对接过程中第三方用户的用户名
     */
    public void setThUsername(String thUsername) {
        this.thUsername = thUsername == null ? null : thUsername.trim();
    }

    /**
     * 获取对接后第三方用户在知好乐系统中的用户名
     *
     * @return zhl_username - 对接后第三方用户在知好乐系统中的用户名
     */
    public String getZhlUsername() {
        return zhlUsername;
    }

    /**
     * 设置对接后第三方用户在知好乐系统中的用户名
     *
     * @param zhlUsername 对接后第三方用户在知好乐系统中的用户名
     */
    public void setZhlUsername(String zhlUsername) {
        this.zhlUsername = zhlUsername == null ? null : zhlUsername.trim();
    }

    /**
     * 获取第三方系统的编码
     *
     * @return th_code - 第三方系统的编码
     */
    public String getThCode() {
        return thCode;
    }

    /**
     * 设置第三方系统的编码
     *
     * @param thCode 第三方系统的编码
     */
    public void setThCode(String thCode) {
        this.thCode = thCode == null ? null : thCode.trim();
    }

	public String getZhlUserid() {
		return zhlUserid;
	}

	public void setZhlUserid(String zhlUserid) {
		this.zhlUserid = zhlUserid;
	}
    
    
    
    
    
}
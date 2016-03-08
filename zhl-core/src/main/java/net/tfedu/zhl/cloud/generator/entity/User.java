package net.tfedu.zhl.cloud.generator.entity;

import javax.persistence.*;

@Table(name = "zhl_user")
public class User {
	
    @Id
    private Integer userid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String email;

    public User(Integer userid, String username, String password, String email) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * @return userid
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }
}
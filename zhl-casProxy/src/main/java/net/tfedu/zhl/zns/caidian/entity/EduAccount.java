package net.tfedu.zhl.zns.caidian.entity;

import javax.persistence.*;

@Table(name = "edu_account")
public class EduAccount {
    @Id
    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "person_guid")
    private String personGuid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "user_status")
    private String userStatus;

    @Column(name = "is_admin")
    private String isAdmin;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "data_context")
    private String dataContext;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduAccount(Integer accountId, String personGuid, String userName, String userType, String userStatus, String isAdmin, String isDelete, String dataContext, String guid) {
        this.accountId = accountId;
        this.personGuid = personGuid;
        this.userName = userName;
        this.userType = userType;
        this.userStatus = userStatus;
        this.isAdmin = isAdmin;
        this.isDelete = isDelete;
        this.dataContext = dataContext;
        this.guid = guid;
    }

    public EduAccount() {
        super();
    }

    /**
     * @return account_id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return person_guid
     */
    public String getPersonGuid() {
        return personGuid;
    }

    /**
     * @param personGuid
     */
    public void setPersonGuid(String personGuid) {
        this.personGuid = personGuid == null ? null : personGuid.trim();
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return user_type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * @return user_status
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    /**
     * @return is_admin
     */
    public String getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin
     */
    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    /**
     * @return is_delete
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete == null ? null : isDelete.trim();
    }

    /**
     * @return data_context
     */
    public String getDataContext() {
        return dataContext;
    }

    /**
     * @param dataContext
     */
    public void setDataContext(String dataContext) {
        this.dataContext = dataContext == null ? null : dataContext.trim();
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
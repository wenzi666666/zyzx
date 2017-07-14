package net.tfedu.zhl.zns.caidian.entity;

import javax.persistence.*;

@Table(name = "edu_teacher")
public class EduTeacher {
    @Id
    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "school_guid")
    private String schoolGuid;

    @Column(name = "job_no")
    private String jobNo;

    private String name;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "chinese_name")
    private String chineseName;

    @Column(name = "former_name")
    private String formerName;

    private String gender;

    private String birthday;

    private String birthplace;

    private String nation;

    private String country;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_period")
    private String cardPeriod;

    @Column(name = "marital_code")
    private String maritalCode;

    @Column(name = "overseas_code")
    private String overseasCode;

    @Column(name = "religion_code")
    private String religionCode;

    @Column(name = "political_code")
    private String politicalCode;

    @Column(name = "health_code")
    private String healthCode;

    @Column(name = "blood_type")
    private String bloodType;

    private String photo;

    private String hometown;

    private String address;

    @Column(name = "nature_address")
    private String natureAddress;

    @Column(name = "nature_type")
    private String natureType;

    @Column(name = "authorized_type")
    private String authorizedType;

    @Column(name = "archive_no")
    private String archiveNo;

    @Column(name = "archive_text")
    private String archiveText;

    @Column(name = "address_comm")
    private String addressComm;

    private String qq;

    private String phone;

    @Column(name = "work_phone")
    private String workPhone;

    private String telephone;

    @Column(name = "post_code")
    private String postCode;

    private String email;

    private String website;

    private String special;

    @Column(name = "personal_nature")
    private String personalNature;

    @Column(name = "partymembership_date")
    private String partymembershipDate;

    @Column(name = "teacher_certificatetype")
    private String teacherCertificatetype;

    @Column(name = "teacher_status")
    private String teacherStatus;

    @Column(name = "state_effctive_date")
    private String stateEffctiveDate;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "data_context")
    private String dataContext;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduTeacher(Integer teacherId, String schoolGuid, String jobNo, String name, String englishName, String chineseName, String formerName, String gender, String birthday, String birthplace, String nation, String country, String cardNo, String cardType, String cardPeriod, String maritalCode, String overseasCode, String religionCode, String politicalCode, String healthCode, String bloodType, String photo, String hometown, String address, String natureAddress, String natureType, String authorizedType, String archiveNo, String archiveText, String addressComm, String qq, String phone, String workPhone, String telephone, String postCode, String email, String website, String special, String personalNature, String partymembershipDate, String teacherCertificatetype, String teacherStatus, String stateEffctiveDate, String isDelete, String dataContext, String guid) {
        this.teacherId = teacherId;
        this.schoolGuid = schoolGuid;
        this.jobNo = jobNo;
        this.name = name;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.formerName = formerName;
        this.gender = gender;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.nation = nation;
        this.country = country;
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.cardPeriod = cardPeriod;
        this.maritalCode = maritalCode;
        this.overseasCode = overseasCode;
        this.religionCode = religionCode;
        this.politicalCode = politicalCode;
        this.healthCode = healthCode;
        this.bloodType = bloodType;
        this.photo = photo;
        this.hometown = hometown;
        this.address = address;
        this.natureAddress = natureAddress;
        this.natureType = natureType;
        this.authorizedType = authorizedType;
        this.archiveNo = archiveNo;
        this.archiveText = archiveText;
        this.addressComm = addressComm;
        this.qq = qq;
        this.phone = phone;
        this.workPhone = workPhone;
        this.telephone = telephone;
        this.postCode = postCode;
        this.email = email;
        this.website = website;
        this.special = special;
        this.personalNature = personalNature;
        this.partymembershipDate = partymembershipDate;
        this.teacherCertificatetype = teacherCertificatetype;
        this.teacherStatus = teacherStatus;
        this.stateEffctiveDate = stateEffctiveDate;
        this.isDelete = isDelete;
        this.dataContext = dataContext;
        this.guid = guid;
    }

    public EduTeacher() {
        super();
    }

    /**
     * @return teacher_id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return school_guid
     */
    public String getSchoolGuid() {
        return schoolGuid;
    }

    /**
     * @param schoolGuid
     */
    public void setSchoolGuid(String schoolGuid) {
        this.schoolGuid = schoolGuid == null ? null : schoolGuid.trim();
    }

    /**
     * @return job_no
     */
    public String getJobNo() {
        return jobNo;
    }

    /**
     * @param jobNo
     */
    public void setJobNo(String jobNo) {
        this.jobNo = jobNo == null ? null : jobNo.trim();
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return english_name
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * @param englishName
     */
    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    /**
     * @return chinese_name
     */
    public String getChineseName() {
        return chineseName;
    }

    /**
     * @param chineseName
     */
    public void setChineseName(String chineseName) {
        this.chineseName = chineseName == null ? null : chineseName.trim();
    }

    /**
     * @return former_name
     */
    public String getFormerName() {
        return formerName;
    }

    /**
     * @param formerName
     */
    public void setFormerName(String formerName) {
        this.formerName = formerName == null ? null : formerName.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * @return birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * @return birthplace
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * @param birthplace
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace == null ? null : birthplace.trim();
    }

    /**
     * @return nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return card_no
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * @return card_type
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     */
    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    /**
     * @return card_period
     */
    public String getCardPeriod() {
        return cardPeriod;
    }

    /**
     * @param cardPeriod
     */
    public void setCardPeriod(String cardPeriod) {
        this.cardPeriod = cardPeriod == null ? null : cardPeriod.trim();
    }

    /**
     * @return marital_code
     */
    public String getMaritalCode() {
        return maritalCode;
    }

    /**
     * @param maritalCode
     */
    public void setMaritalCode(String maritalCode) {
        this.maritalCode = maritalCode == null ? null : maritalCode.trim();
    }

    /**
     * @return overseas_code
     */
    public String getOverseasCode() {
        return overseasCode;
    }

    /**
     * @param overseasCode
     */
    public void setOverseasCode(String overseasCode) {
        this.overseasCode = overseasCode == null ? null : overseasCode.trim();
    }

    /**
     * @return religion_code
     */
    public String getReligionCode() {
        return religionCode;
    }

    /**
     * @param religionCode
     */
    public void setReligionCode(String religionCode) {
        this.religionCode = religionCode == null ? null : religionCode.trim();
    }

    /**
     * @return political_code
     */
    public String getPoliticalCode() {
        return politicalCode;
    }

    /**
     * @param politicalCode
     */
    public void setPoliticalCode(String politicalCode) {
        this.politicalCode = politicalCode == null ? null : politicalCode.trim();
    }

    /**
     * @return health_code
     */
    public String getHealthCode() {
        return healthCode;
    }

    /**
     * @param healthCode
     */
    public void setHealthCode(String healthCode) {
        this.healthCode = healthCode == null ? null : healthCode.trim();
    }

    /**
     * @return blood_type
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * @param bloodType
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType == null ? null : bloodType.trim();
    }

    /**
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * @return hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown
     */
    public void setHometown(String hometown) {
        this.hometown = hometown == null ? null : hometown.trim();
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return nature_address
     */
    public String getNatureAddress() {
        return natureAddress;
    }

    /**
     * @param natureAddress
     */
    public void setNatureAddress(String natureAddress) {
        this.natureAddress = natureAddress == null ? null : natureAddress.trim();
    }

    /**
     * @return nature_type
     */
    public String getNatureType() {
        return natureType;
    }

    /**
     * @param natureType
     */
    public void setNatureType(String natureType) {
        this.natureType = natureType == null ? null : natureType.trim();
    }

    /**
     * @return authorized_type
     */
    public String getAuthorizedType() {
        return authorizedType;
    }

    /**
     * @param authorizedType
     */
    public void setAuthorizedType(String authorizedType) {
        this.authorizedType = authorizedType == null ? null : authorizedType.trim();
    }

    /**
     * @return archive_no
     */
    public String getArchiveNo() {
        return archiveNo;
    }

    /**
     * @param archiveNo
     */
    public void setArchiveNo(String archiveNo) {
        this.archiveNo = archiveNo == null ? null : archiveNo.trim();
    }

    /**
     * @return archive_text
     */
    public String getArchiveText() {
        return archiveText;
    }

    /**
     * @param archiveText
     */
    public void setArchiveText(String archiveText) {
        this.archiveText = archiveText == null ? null : archiveText.trim();
    }

    /**
     * @return address_comm
     */
    public String getAddressComm() {
        return addressComm;
    }

    /**
     * @param addressComm
     */
    public void setAddressComm(String addressComm) {
        this.addressComm = addressComm == null ? null : addressComm.trim();
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return work_phone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * @param workPhone
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone == null ? null : workPhone.trim();
    }

    /**
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return post_code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * @return special
     */
    public String getSpecial() {
        return special;
    }

    /**
     * @param special
     */
    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    /**
     * @return personal_nature
     */
    public String getPersonalNature() {
        return personalNature;
    }

    /**
     * @param personalNature
     */
    public void setPersonalNature(String personalNature) {
        this.personalNature = personalNature == null ? null : personalNature.trim();
    }

    /**
     * @return partymembership_date
     */
    public String getPartymembershipDate() {
        return partymembershipDate;
    }

    /**
     * @param partymembershipDate
     */
    public void setPartymembershipDate(String partymembershipDate) {
        this.partymembershipDate = partymembershipDate == null ? null : partymembershipDate.trim();
    }

    /**
     * @return teacher_certificatetype
     */
    public String getTeacherCertificatetype() {
        return teacherCertificatetype;
    }

    /**
     * @param teacherCertificatetype
     */
    public void setTeacherCertificatetype(String teacherCertificatetype) {
        this.teacherCertificatetype = teacherCertificatetype == null ? null : teacherCertificatetype.trim();
    }

    /**
     * @return teacher_status
     */
    public String getTeacherStatus() {
        return teacherStatus;
    }

    /**
     * @param teacherStatus
     */
    public void setTeacherStatus(String teacherStatus) {
        this.teacherStatus = teacherStatus == null ? null : teacherStatus.trim();
    }

    /**
     * @return state_effctive_date
     */
    public String getStateEffctiveDate() {
        return stateEffctiveDate;
    }

    /**
     * @param stateEffctiveDate
     */
    public void setStateEffctiveDate(String stateEffctiveDate) {
        this.stateEffctiveDate = stateEffctiveDate == null ? null : stateEffctiveDate.trim();
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
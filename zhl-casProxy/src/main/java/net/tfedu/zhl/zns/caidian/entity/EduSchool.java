package net.tfedu.zhl.zns.caidian.entity;

import javax.persistence.*;

@Table(name = "edu_school")
public class EduSchool {
    @Id
    @Column(name = "school_id")
    private Integer schoolId;

    @Column(name = "parent_school_guid")
    private String parentSchoolGuid;

    @Column(name = "category_guid")
    private String categoryGuid;

    @Column(name = "data_context")
    private String dataContext;

    private String name;

    @Column(name = "english_name")
    private String englishName;

    private String address;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "org_type")
    private String orgType;

    @Column(name = "org_code")
    private String orgCode;

    @Column(name = "school_type_code")
    private String schoolTypeCode;

    @Column(name = "main_lang_code")
    private String mainLangCode;

    @Column(name = "auxiliary_lang_code")
    private String auxiliaryLangCode;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "admin_division")
    private String adminDivision;

    @Column(name = "economic_property")
    private String economicProperty;

    @Column(name = "national_property")
    private String nationalProperty;

    @Column(name = "town_type")
    private String townType;

    @Column(name = "town_name")
    private String townName;

    private String contacts;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "fax_phone")
    private String faxPhone;

    private String email;

    @Column(name = "school_status")
    private String schoolStatus;

    @Column(name = "party_org_charger")
    private String partyOrgCharger;

    @Column(name = "legal_no")
    private String legalNo;

    @Column(name = "registration_no")
    private String registrationNo;

    @Column(name = "principal_no")
    private String principalNo;

    @Column(name = "principal_phone")
    private String principalPhone;

    @Column(name = "principal_name")
    private String principalName;

    @Column(name = "scene_entrance_age")
    private String sceneEntranceAge;

    @Column(name = "scene_edu_system")
    private String sceneEduSystem;

    @Column(name = "high_edu_system")
    private String highEduSystem;

    @Column(name = "primary_entrance_age")
    private String primaryEntranceAge;

    @Column(name = "primary_edu_system")
    private String primaryEduSystem;

    @Column(name = "school_year")
    private String schoolYear;

    @Column(name = "decoration_day")
    private String decorationDay;

    @Column(name = "historical_evolution")
    private String historicalEvolution;

    @Column(name = "is_independent")
    private String isIndependent;

    private String website;

    @Column(name = "school_altitude")
    private String schoolAltitude;

    private String longitude;

    private String latitude;

    @Column(name = "enrollment_radius")
    private String enrollmentRadius;

    @Column(name = "is_delete")
    private String isDelete;

    @SequenceGenerator(name="",sequenceName="SELECT LAST_INSERT_ID()")
    private String guid;

    public EduSchool(Integer schoolId, String parentSchoolGuid, String categoryGuid, String dataContext, String name, String englishName, String address, String postCode, String orgType, String orgCode, String schoolTypeCode, String mainLangCode, String auxiliaryLangCode, String areaName, String adminDivision, String economicProperty, String nationalProperty, String townType, String townName, String contacts, String contactNo, String faxPhone, String email, String schoolStatus, String partyOrgCharger, String legalNo, String registrationNo, String principalNo, String principalPhone, String principalName, String sceneEntranceAge, String sceneEduSystem, String highEduSystem, String primaryEntranceAge, String primaryEduSystem, String schoolYear, String decorationDay, String historicalEvolution, String isIndependent, String website, String schoolAltitude, String longitude, String latitude, String enrollmentRadius, String isDelete, String guid) {
        this.schoolId = schoolId;
        this.parentSchoolGuid = parentSchoolGuid;
        this.categoryGuid = categoryGuid;
        this.dataContext = dataContext;
        this.name = name;
        this.englishName = englishName;
        this.address = address;
        this.postCode = postCode;
        this.orgType = orgType;
        this.orgCode = orgCode;
        this.schoolTypeCode = schoolTypeCode;
        this.mainLangCode = mainLangCode;
        this.auxiliaryLangCode = auxiliaryLangCode;
        this.areaName = areaName;
        this.adminDivision = adminDivision;
        this.economicProperty = economicProperty;
        this.nationalProperty = nationalProperty;
        this.townType = townType;
        this.townName = townName;
        this.contacts = contacts;
        this.contactNo = contactNo;
        this.faxPhone = faxPhone;
        this.email = email;
        this.schoolStatus = schoolStatus;
        this.partyOrgCharger = partyOrgCharger;
        this.legalNo = legalNo;
        this.registrationNo = registrationNo;
        this.principalNo = principalNo;
        this.principalPhone = principalPhone;
        this.principalName = principalName;
        this.sceneEntranceAge = sceneEntranceAge;
        this.sceneEduSystem = sceneEduSystem;
        this.highEduSystem = highEduSystem;
        this.primaryEntranceAge = primaryEntranceAge;
        this.primaryEduSystem = primaryEduSystem;
        this.schoolYear = schoolYear;
        this.decorationDay = decorationDay;
        this.historicalEvolution = historicalEvolution;
        this.isIndependent = isIndependent;
        this.website = website;
        this.schoolAltitude = schoolAltitude;
        this.longitude = longitude;
        this.latitude = latitude;
        this.enrollmentRadius = enrollmentRadius;
        this.isDelete = isDelete;
        this.guid = guid;
    }

    public EduSchool() {
        super();
    }

    /**
     * @return school_id
     */
    public Integer getSchoolId() {
        return schoolId;
    }

    /**
     * @param schoolId
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * @return parent_school_guid
     */
    public String getParentSchoolGuid() {
        return parentSchoolGuid;
    }

    /**
     * @param parentSchoolGuid
     */
    public void setParentSchoolGuid(String parentSchoolGuid) {
        this.parentSchoolGuid = parentSchoolGuid == null ? null : parentSchoolGuid.trim();
    }

    /**
     * @return category_guid
     */
    public String getCategoryGuid() {
        return categoryGuid;
    }

    /**
     * @param categoryGuid
     */
    public void setCategoryGuid(String categoryGuid) {
        this.categoryGuid = categoryGuid == null ? null : categoryGuid.trim();
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
     * @return org_type
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    /**
     * @return org_code
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * @return school_type_code
     */
    public String getSchoolTypeCode() {
        return schoolTypeCode;
    }

    /**
     * @param schoolTypeCode
     */
    public void setSchoolTypeCode(String schoolTypeCode) {
        this.schoolTypeCode = schoolTypeCode == null ? null : schoolTypeCode.trim();
    }

    /**
     * @return main_lang_code
     */
    public String getMainLangCode() {
        return mainLangCode;
    }

    /**
     * @param mainLangCode
     */
    public void setMainLangCode(String mainLangCode) {
        this.mainLangCode = mainLangCode == null ? null : mainLangCode.trim();
    }

    /**
     * @return auxiliary_lang_code
     */
    public String getAuxiliaryLangCode() {
        return auxiliaryLangCode;
    }

    /**
     * @param auxiliaryLangCode
     */
    public void setAuxiliaryLangCode(String auxiliaryLangCode) {
        this.auxiliaryLangCode = auxiliaryLangCode == null ? null : auxiliaryLangCode.trim();
    }

    /**
     * @return area_name
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * @return admin_division
     */
    public String getAdminDivision() {
        return adminDivision;
    }

    /**
     * @param adminDivision
     */
    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision == null ? null : adminDivision.trim();
    }

    /**
     * @return economic_property
     */
    public String getEconomicProperty() {
        return economicProperty;
    }

    /**
     * @param economicProperty
     */
    public void setEconomicProperty(String economicProperty) {
        this.economicProperty = economicProperty == null ? null : economicProperty.trim();
    }

    /**
     * @return national_property
     */
    public String getNationalProperty() {
        return nationalProperty;
    }

    /**
     * @param nationalProperty
     */
    public void setNationalProperty(String nationalProperty) {
        this.nationalProperty = nationalProperty == null ? null : nationalProperty.trim();
    }

    /**
     * @return town_type
     */
    public String getTownType() {
        return townType;
    }

    /**
     * @param townType
     */
    public void setTownType(String townType) {
        this.townType = townType == null ? null : townType.trim();
    }

    /**
     * @return town_name
     */
    public String getTownName() {
        return townName;
    }

    /**
     * @param townName
     */
    public void setTownName(String townName) {
        this.townName = townName == null ? null : townName.trim();
    }

    /**
     * @return contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    /**
     * @return contact_no
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo == null ? null : contactNo.trim();
    }

    /**
     * @return fax_phone
     */
    public String getFaxPhone() {
        return faxPhone;
    }

    /**
     * @param faxPhone
     */
    public void setFaxPhone(String faxPhone) {
        this.faxPhone = faxPhone == null ? null : faxPhone.trim();
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
     * @return school_status
     */
    public String getSchoolStatus() {
        return schoolStatus;
    }

    /**
     * @param schoolStatus
     */
    public void setSchoolStatus(String schoolStatus) {
        this.schoolStatus = schoolStatus == null ? null : schoolStatus.trim();
    }

    /**
     * @return party_org_charger
     */
    public String getPartyOrgCharger() {
        return partyOrgCharger;
    }

    /**
     * @param partyOrgCharger
     */
    public void setPartyOrgCharger(String partyOrgCharger) {
        this.partyOrgCharger = partyOrgCharger == null ? null : partyOrgCharger.trim();
    }

    /**
     * @return legal_no
     */
    public String getLegalNo() {
        return legalNo;
    }

    /**
     * @param legalNo
     */
    public void setLegalNo(String legalNo) {
        this.legalNo = legalNo == null ? null : legalNo.trim();
    }

    /**
     * @return registration_no
     */
    public String getRegistrationNo() {
        return registrationNo;
    }

    /**
     * @param registrationNo
     */
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo == null ? null : registrationNo.trim();
    }

    /**
     * @return principal_no
     */
    public String getPrincipalNo() {
        return principalNo;
    }

    /**
     * @param principalNo
     */
    public void setPrincipalNo(String principalNo) {
        this.principalNo = principalNo == null ? null : principalNo.trim();
    }

    /**
     * @return principal_phone
     */
    public String getPrincipalPhone() {
        return principalPhone;
    }

    /**
     * @param principalPhone
     */
    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone == null ? null : principalPhone.trim();
    }

    /**
     * @return principal_name
     */
    public String getPrincipalName() {
        return principalName;
    }

    /**
     * @param principalName
     */
    public void setPrincipalName(String principalName) {
        this.principalName = principalName == null ? null : principalName.trim();
    }

    /**
     * @return scene_entrance_age
     */
    public String getSceneEntranceAge() {
        return sceneEntranceAge;
    }

    /**
     * @param sceneEntranceAge
     */
    public void setSceneEntranceAge(String sceneEntranceAge) {
        this.sceneEntranceAge = sceneEntranceAge == null ? null : sceneEntranceAge.trim();
    }

    /**
     * @return scene_edu_system
     */
    public String getSceneEduSystem() {
        return sceneEduSystem;
    }

    /**
     * @param sceneEduSystem
     */
    public void setSceneEduSystem(String sceneEduSystem) {
        this.sceneEduSystem = sceneEduSystem == null ? null : sceneEduSystem.trim();
    }

    /**
     * @return high_edu_system
     */
    public String getHighEduSystem() {
        return highEduSystem;
    }

    /**
     * @param highEduSystem
     */
    public void setHighEduSystem(String highEduSystem) {
        this.highEduSystem = highEduSystem == null ? null : highEduSystem.trim();
    }

    /**
     * @return primary_entrance_age
     */
    public String getPrimaryEntranceAge() {
        return primaryEntranceAge;
    }

    /**
     * @param primaryEntranceAge
     */
    public void setPrimaryEntranceAge(String primaryEntranceAge) {
        this.primaryEntranceAge = primaryEntranceAge == null ? null : primaryEntranceAge.trim();
    }

    /**
     * @return primary_edu_system
     */
    public String getPrimaryEduSystem() {
        return primaryEduSystem;
    }

    /**
     * @param primaryEduSystem
     */
    public void setPrimaryEduSystem(String primaryEduSystem) {
        this.primaryEduSystem = primaryEduSystem == null ? null : primaryEduSystem.trim();
    }

    /**
     * @return school_year
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    /**
     * @param schoolYear
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear == null ? null : schoolYear.trim();
    }

    /**
     * @return decoration_day
     */
    public String getDecorationDay() {
        return decorationDay;
    }

    /**
     * @param decorationDay
     */
    public void setDecorationDay(String decorationDay) {
        this.decorationDay = decorationDay == null ? null : decorationDay.trim();
    }

    /**
     * @return historical_evolution
     */
    public String getHistoricalEvolution() {
        return historicalEvolution;
    }

    /**
     * @param historicalEvolution
     */
    public void setHistoricalEvolution(String historicalEvolution) {
        this.historicalEvolution = historicalEvolution == null ? null : historicalEvolution.trim();
    }

    /**
     * @return is_independent
     */
    public String getIsIndependent() {
        return isIndependent;
    }

    /**
     * @param isIndependent
     */
    public void setIsIndependent(String isIndependent) {
        this.isIndependent = isIndependent == null ? null : isIndependent.trim();
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
     * @return school_altitude
     */
    public String getSchoolAltitude() {
        return schoolAltitude;
    }

    /**
     * @param schoolAltitude
     */
    public void setSchoolAltitude(String schoolAltitude) {
        this.schoolAltitude = schoolAltitude == null ? null : schoolAltitude.trim();
    }

    /**
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * @return enrollment_radius
     */
    public String getEnrollmentRadius() {
        return enrollmentRadius;
    }

    /**
     * @param enrollmentRadius
     */
    public void setEnrollmentRadius(String enrollmentRadius) {
        this.enrollmentRadius = enrollmentRadius == null ? null : enrollmentRadius.trim();
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
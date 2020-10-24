package com.ccb.portal.entity.publicity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/*
* 红黑名单
* */

public class XyynHhmdInfo {
   

	private String id;

    private String originalId;

    private String region;

    private String subjectType;

    private String listType;

    private String memorandum;

    private String matter;

    private String matterType;

    private String isExecuted;

    private String isNative;

    private String remarks;

    private String name;

    private String creditCode;

    private String behavior;

    private Date recordUpdateTime;
    private Date periodOfValidityT0;

    private String isCountryImport;

    private String addUserId;
    private Date addTime;

    private String updateUserId;
    private Date updatTime;

    private String isDelete;

    private String addDepartment;

    private String cognizanceState;

    private String publicityState;

    private String executeState;

    private String duration;
    private Date publicityStartTime;
    private Date publicityEndTime;
    private Date cognizanceTime;
    private Date rejectTime;

    private String rejectBasis;

    private String legalRepresentative;

    private String includedReason;

    private String adjudication;

    private String adjudicationBasis;
    private Date adjudicationTime;

    private Long age;

    private String sex;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getMemorandum() {
        return memorandum;
    }

    public void setMemorandum(String memorandum) {
        this.memorandum = memorandum;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getMatterType() {
        return matterType;
    }

    public void setMatterType(String matterType) {
        this.matterType = matterType;
    }

    public String getIsExecuted() {
        return isExecuted;
    }

    public void setIsExecuted(String isExecuted) {
        this.isExecuted = isExecuted;
    }

    public String getIsNative() {
        return isNative;
    }

    public void setIsNative(String isNative) {
        this.isNative = isNative;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getRecordUpdateTime() {
        return recordUpdateTime;
    }

    public void setRecordUpdateTime(Date recordUpdateTime) {
        this.recordUpdateTime = recordUpdateTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPeriodOfValidityT0() {
        return periodOfValidityT0;
    }

    public void setPeriodOfValidityT0(Date periodOfValidityT0) {
        this.periodOfValidityT0 = periodOfValidityT0;
    }

    public String getIsCountryImport() {
        return isCountryImport;
    }

    public void setIsCountryImport(String isCountryImport) {
        this.isCountryImport = isCountryImport;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdatTime() {
        return updatTime;
    }

    public void setUpdatTime(Date updatTime) {
        this.updatTime = updatTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getAddDepartment() {
        return addDepartment;
    }

    public void setAddDepartment(String addDepartment) {
        this.addDepartment = addDepartment;
    }

    public String getCognizanceState() {
        return cognizanceState;
    }

    public void setCognizanceState(String cognizanceState) {
        this.cognizanceState = cognizanceState;
    }

    public String getPublicityState() {
        return publicityState;
    }

    public void setPublicityState(String publicityState) {
        this.publicityState = publicityState;
    }

    public String getExecuteState() {
        return executeState;
    }

    public void setExecuteState(String executeState) {
        this.executeState = executeState;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPublicityStartTime() {
        return publicityStartTime;
    }

    public void setPublicityStartTime(Date publicityStartTime) {
        this.publicityStartTime = publicityStartTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPublicityEndTime() {
        return publicityEndTime;
    }

    public void setPublicityEndTime(Date publicityEndTime) {
        this.publicityEndTime = publicityEndTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    public Date getCognizanceTime() {
        return cognizanceTime;
    }

    public void setCognizanceTime(Date cognizanceTime) {
        this.cognizanceTime = cognizanceTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getRejectTime() {
        return rejectTime;
    }

    public void setRejectTime(Date rejectTime) {
        this.rejectTime = rejectTime;
    }

    public String getRejectBasis() {
        return rejectBasis;
    }

    public void setRejectBasis(String rejectBasis) {
        this.rejectBasis = rejectBasis;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getIncludedReason() {
        return includedReason;
    }

    public void setIncludedReason(String includedReason) {
        this.includedReason = includedReason;
    }

    public String getAdjudication() {
        return adjudication;
    }

    public void setAdjudication(String adjudication) {
        this.adjudication = adjudication;
    }

    public String getAdjudicationBasis() {
        return adjudicationBasis;
    }

    public void setAdjudicationBasis(String adjudicationBasis) {
        this.adjudicationBasis = adjudicationBasis;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getAdjudicationTime() {
        return adjudicationTime;
    }

    public void setAdjudicationTime(Date adjudicationTime) {
        this.adjudicationTime = adjudicationTime;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
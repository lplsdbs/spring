package com.ccb.portal.entity.Credit;

import java.util.Date;

public class CreditUserinf {
    private String userId;//url后的

    private String userName;

    private String corpName;

    private String usco;

    private String legalPerson;

    private String idcard;

    private String moble;

    private String userNum;//深开

    private Date createDate;

    private String cstNo;

    private String cardType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getUsco() {
        return usco;
    }

    public void setUsco(String usco) {
        this.usco = usco;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMoble() {
        return moble;
    }

    public void setMoble(String moble) {
        this.moble = moble;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCstNo() {
        return cstNo;
    }

    public void setCstNo(String cstNo) {
        this.cstNo = cstNo;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "CreditUserinf{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", corpName='" + corpName + '\'' +
                ", usco='" + usco + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", idcard='" + idcard + '\'' +
                ", moble='" + moble + '\'' +
                ", userNum='" + userNum + '\'' +
                ", createDate=" + createDate +
                ", cstNo='" + cstNo + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
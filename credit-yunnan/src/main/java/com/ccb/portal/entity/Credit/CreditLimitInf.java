package com.ccb.portal.entity.Credit;

import java.util.Date;
import java.util.List;

public class CreditLimitInf {
    private String id;

    private String userNum;

    private String loanDate;

    private String loanNum;

    private String loanLimit;

    private String loanBalance;

    private String unnSocCrCd;

    private Date createDate;

    private List<CreditLimitInf>limitList;

    public List<CreditLimitInf> getLimitList() {
        return limitList;
    }

    public void setLimitList(List<CreditLimitInf> limitList) {
        this.limitList = limitList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanNum() {
        return loanNum;
    }

    public void setLoanNum(String loanNum) {
        this.loanNum = loanNum;
    }

    public String getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(String loanLimit) {
        this.loanLimit = loanLimit;
    }

    public String getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(String loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getUnnSocCrCd() {
        return unnSocCrCd;
    }

    public void setUnnSocCrCd(String unnSocCrCd) {
        this.unnSocCrCd = unnSocCrCd;
    }
}
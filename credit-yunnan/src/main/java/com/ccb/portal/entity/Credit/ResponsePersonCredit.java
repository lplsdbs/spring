package com.ccb.portal.entity.Credit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePersonCredit {

    private  String Cst_ID;

    private String Crdt_TpCd;

    private String Crdt_No;

    private String Cst_Nm;

    private String Scor_Crd_Scor;

    private String Rsk_Qot;

    private String Scor_Rel_Lo_Dsc;

    private String MULTI_TENANCY_ID;

    private String ScsInd;

    private String Prmpt_Inf_Topc_Dsc;

    private String type;
    @JsonIgnore
    private String AgncBnkVchsonMRpCntnt;


    @JsonIgnore
    private String AfTVO;

    private List<CreditDmsn>dmsn_grplist;

    public List<CreditDmsn> getDmsn_grplist() {
        return dmsn_grplist;
    }

    public void setDmsn_grplist(List<CreditDmsn> dmsn_grplist) {
        this.dmsn_grplist = dmsn_grplist;
    }

    @JsonIgnore
    public String getCrdt_TpCd() {
        return Crdt_TpCd;
    }

    @Override
    public String toString() {
        return "ResponsePersonCredit{" +
                "Crdt_TpCd='" + Crdt_TpCd + '\'' +
                ", Crdt_No='" + Crdt_No + '\'' +
                ", Cst_Nm='" + Cst_Nm + '\'' +
                ", Scor_Crd_Scor='" + Scor_Crd_Scor + '\'' +
                ", Scor_Rel_Lo_Dsc='" + Scor_Rel_Lo_Dsc + '\'' +
                ", MULTI_TENANCY_ID='" + MULTI_TENANCY_ID + '\'' +
                ", ScsInd='" + ScsInd + '\'' +
                ", Prmpt_Inf_Topc_Dsc='" + Prmpt_Inf_Topc_Dsc + '\'' +
                ", type='" + type + '\'' +
                ", AgncBnkVchsonMRpCntnt='" + AgncBnkVchsonMRpCntnt + '\'' +
                ", Rsk_Qot=" + Rsk_Qot +
                '}';
    }

    public String getCst_ID() {
        return Cst_ID;
    }

    public void setCst_ID(String cst_ID) {
        Cst_ID = cst_ID;
    }

    public void setCrdt_TpCd(String crdt_TpCd) {
        Crdt_TpCd = crdt_TpCd;
    }
    @JsonIgnore
    public String getCrdt_No() {
        return Crdt_No;
    }
    @JsonIgnore
    public String getAfTVO() {
        return AfTVO;
    }

    public void setAfTVO(String afTVO) {
        AfTVO = afTVO;
    }
    @JsonIgnore
    public void setCrdt_No(String crdt_No) {
        Crdt_No = crdt_No;
    }
    @JsonIgnore
    public String getCst_Nm() {
        return Cst_Nm;
    }

    public void setCst_Nm(String cst_Nm) {
        Cst_Nm = cst_Nm;
    }
    @JsonIgnore
    public String getScor_Crd_Scor() {
        return Scor_Crd_Scor;
    }

    public void setScor_Crd_Scor(String scor_Crd_Scor) {
        Scor_Crd_Scor = scor_Crd_Scor;
    }
    @JsonIgnore
    public String getScor_Rel_Lo_Dsc() {
        return Scor_Rel_Lo_Dsc;
    }

    public void setScor_Rel_Lo_Dsc(String scor_Rel_Lo_Dsc) {
        Scor_Rel_Lo_Dsc = scor_Rel_Lo_Dsc;
    }
    @JsonIgnore
    public String getMULTI_TENANCY_ID() {
        return MULTI_TENANCY_ID;
    }

    public void setMULTI_TENANCY_ID(String MULTI_TENANCY_ID) {
        this.MULTI_TENANCY_ID = MULTI_TENANCY_ID;
    }
    @JsonIgnore
    public String getScsInd() {
        return ScsInd;
    }

    public void setScsInd(String scsInd) {
        ScsInd = scsInd;
    }
    @JsonIgnore
    public String getPrmpt_Inf_Topc_Dsc() {
        return Prmpt_Inf_Topc_Dsc;
    }

    public void setPrmpt_Inf_Topc_Dsc(String prmpt_Inf_Topc_Dsc) {
        Prmpt_Inf_Topc_Dsc = prmpt_Inf_Topc_Dsc;
    }
    @JsonIgnore
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @JsonIgnore
    public String getAgncBnkVchsonMRpCntnt() {
        return AgncBnkVchsonMRpCntnt;
    }

    public void setAgncBnkVchsonMRpCntnt(String agncBnkVchsonMRpCntnt) {
        AgncBnkVchsonMRpCntnt = agncBnkVchsonMRpCntnt;
    }
    @JsonIgnore
    public String getRsk_Qot() {
        return Rsk_Qot;
    }

    public void setRsk_Qot(String rsk_Qot) {
        Rsk_Qot = rsk_Qot;
    }
}

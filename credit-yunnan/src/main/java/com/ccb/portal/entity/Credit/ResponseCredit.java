package com.ccb.portal.entity.Credit;

import java.util.List;

public class ResponseCredit {

    private String cst_id;

    private String Cst_type;

    private String credit_value;

    private String model_type;

    private String comment_limit;

    private String credit_rating_level;

    private List<ResponseDmsn> dmsn_grplist;

    private List<CreditRecommendType>Credit_recommend_type;

    public List<CreditRecommendType> getCredit_recommend_type() {
        return Credit_recommend_type;
    }

    public void setCredit_recommend_type(List<CreditRecommendType> credit_recommend_type) {
        Credit_recommend_type = credit_recommend_type;
    }

    public String getCst_id() {
        return cst_id;
    }

    public void setCst_id(String cst_id) {
        this.cst_id = cst_id;
    }

    public String getCredit_value() {
        return credit_value;
    }

    public void setCredit_value(String credit_value) {
        this.credit_value = credit_value;
    }

    public String getComment_limit() {
        return comment_limit;
    }

    public void setComment_limit(String comment_limit) {
        this.comment_limit = comment_limit;
    }

    public String getCst_type() {
        return Cst_type;
    }

    public void setCst_type(String cst_type) {
        Cst_type = cst_type;
    }



    public String getModel_type() {
        return model_type;
    }

    public void setModel_type(String model_type) {
        this.model_type = model_type;
    }


    public String getCredit_rating_level() {
        return credit_rating_level;
    }

    public void setCredit_rating_level(String credit_rating_level) {
        this.credit_rating_level = credit_rating_level;
    }

    public List<ResponseDmsn> getDmsn_grplist() {
        return dmsn_grplist;
    }

    public void setDmsn_grplist(List<ResponseDmsn> dmsn_grplist) {
        this.dmsn_grplist = dmsn_grplist;
    }
}

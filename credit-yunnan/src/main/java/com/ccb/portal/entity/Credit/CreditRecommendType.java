package com.ccb.portal.entity.Credit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CreditRecommendType {

    private BigDecimal id;
    @JsonProperty(value = "type_name")
    private String typeName;
    @JsonIgnore
    private BigDecimal leveScore;

    private YunnanCredit yunnanCredit;
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getLeveScore() {
        return leveScore;
    }

    public void setLeveScore(BigDecimal leveScore) {
        this.leveScore = leveScore;
    }
}
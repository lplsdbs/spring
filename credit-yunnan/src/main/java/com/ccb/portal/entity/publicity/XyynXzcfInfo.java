package com.ccb.portal.entity.publicity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/*
* 行政处罚
* */
public class XyynXzcfInfo {
    private String id;

    private String cfWsh;

    private String cfCfmc;

    private String cfCflb1;

    private String cfCflb2;

    private String cfSy;

    private String cfYj;

    private String cfXdrMc;

    private String cfXdrShxym;

    private String cfXdrZdm;

    private String cfXdrGsdj;

    private String cfXdrSwdj;

    private String cfXdrSfz;

    private String cfFr;

    private String cfJg;

    private Date cfJdrq;

    private String cfXzjg;

    private String cfXzbm;

    private String cfZt;

    private Date cfSjc;

    private String cfBz;

    private String cfDfbm;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCfWsh() {
        return cfWsh;
    }

    public void setCfWsh(String cfWsh) {
        this.cfWsh = cfWsh;
    }

    public String getCfCfmc() {
        return cfCfmc;
    }

    public void setCfCfmc(String cfCfmc) {
        this.cfCfmc = cfCfmc;
    }

    public String getCfCflb1() {
        return cfCflb1;
    }

    public void setCfCflb1(String cfCflb1) {
        this.cfCflb1 = cfCflb1;
    }

    public String getCfCflb2() {
        return cfCflb2;
    }

    public void setCfCflb2(String cfCflb2) {
        this.cfCflb2 = cfCflb2;
    }

    public String getCfSy() {
        return cfSy;
    }

    public void setCfSy(String cfSy) {
        this.cfSy = cfSy;
    }

    public String getCfYj() {
        return cfYj;
    }

    public void setCfYj(String cfYj) {
        this.cfYj = cfYj;
    }

    public String getCfXdrMc() {
        return cfXdrMc;
    }

    public void setCfXdrMc(String cfXdrMc) {
        this.cfXdrMc = cfXdrMc;
    }

    public String getCfXdrShxym() {
        return cfXdrShxym;
    }

    public void setCfXdrShxym(String cfXdrShxym) {
        this.cfXdrShxym = cfXdrShxym;
    }

    public String getCfXdrZdm() {
        return cfXdrZdm;
    }

    public void setCfXdrZdm(String cfXdrZdm) {
        this.cfXdrZdm = cfXdrZdm;
    }

    public String getCfXdrGsdj() {
        return cfXdrGsdj;
    }

    public void setCfXdrGsdj(String cfXdrGsdj) {
        this.cfXdrGsdj = cfXdrGsdj;
    }

    public String getCfXdrSwdj() {
        return cfXdrSwdj;
    }

    public void setCfXdrSwdj(String cfXdrSwdj) {
        this.cfXdrSwdj = cfXdrSwdj;
    }

    public String getCfXdrSfz() {
        return cfXdrSfz;
    }

    public void setCfXdrSfz(String cfXdrSfz) {
        this.cfXdrSfz = cfXdrSfz;
    }

    public String getCfFr() {
        return cfFr;
    }

    public void setCfFr(String cfFr) {
        this.cfFr = cfFr;
    }

    public String getCfJg() {
        return cfJg;
    }

    public void setCfJg(String cfJg) {
        this.cfJg = cfJg;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCfJdrq() {
        return cfJdrq;
    }

    public void setCfJdrq(Date cfJdrq) {
        this.cfJdrq = cfJdrq;
    }

    public String getCfXzjg() {
        return cfXzjg;
    }

    public void setCfXzjg(String cfXzjg) {
        this.cfXzjg = cfXzjg;
    }

    public String getCfXzbm() {
        return cfXzbm;
    }

    public void setCfXzbm(String cfXzbm) {
        this.cfXzbm = cfXzbm;
    }

    public String getCfZt() {
        return cfZt;
    }

    public void setCfZt(String cfZt) {
        this.cfZt = cfZt;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCfSjc() {
        return cfSjc;
    }

    public void setCfSjc(Date cfSjc) {
        this.cfSjc = cfSjc;
    }

    public String getCfBz() {
        return cfBz;
    }

    public void setCfBz(String cfBz) {
        this.cfBz = cfBz;
    }

    public String getCfDfbm() {
        return cfDfbm;
    }

    public void setCfDfbm(String cfDfbm) {
        this.cfDfbm = cfDfbm;
    }
}
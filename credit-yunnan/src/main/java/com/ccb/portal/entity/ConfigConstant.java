package com.ccb.portal.entity;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
/**
 *config.properties配置文件映射实体
 * 使用@Autowired注解引入
 */

@Configuration
@ConfigurationProperties(prefix = "config",ignoreUnknownFields = false)
@PropertySource("file:./sysconfig/config.properties")
public class ConfigConstant {
    private String grade;
    private String pgrade;
    private String produceLevel;
    private String produceId;
    private String priKey;
    private String IFSPLnDfaltRgst;
    private String IFSPVldEntpInf;
    private String IFSPLnAplDtc;
    private String IFSPLnLmtEst;
    private String IFSPRcvPymtAccNoInfSynz;
    private String IFSPEsttCltlLmtMsr;
    private String IFSPEntCrdLmtMsr;
    private String IFSPEnqrUrbnDstcInf;
    private String IFSPEnqrCommLst;
    private String IFSPAplySMSVldCd;
    private String IFSPVerfSMSVldCd;
    private String webPsaaword;
    private String tokenToUser;
    private String IFSPEntpAhnFrtVerf;
    private String IFSPShrhInfQry;
    private String IFSPAhnAprvAply;
    private String IFSPAhnAprvPrgrsListEnqr;

    public String getIFSPAhnAprvPrgrsListEnqr() {
        return IFSPAhnAprvPrgrsListEnqr;
    }

    public void setIFSPAhnAprvPrgrsListEnqr(String IFSPAhnAprvPrgrsListEnqr) {
        this.IFSPAhnAprvPrgrsListEnqr = IFSPAhnAprvPrgrsListEnqr;
    }

    public String getIFSPAhnAprvAply() {
        return IFSPAhnAprvAply;
    }

    public void setIFSPAhnAprvAply(String IFSPAhnAprvAply) {
        this.IFSPAhnAprvAply = IFSPAhnAprvAply;
    }

    public String getIFSPShrhInfQry() {
        return IFSPShrhInfQry;
    }

    public void setIFSPShrhInfQry(String IFSPShrhInfQry) {
        this.IFSPShrhInfQry = IFSPShrhInfQry;
    }

    public String getIFSPEntpAhnFrtVerf() {
        return IFSPEntpAhnFrtVerf;
    }

    public void setIFSPEntpAhnFrtVerf(String IFSPEntpAhnFrtVerf) {
        this.IFSPEntpAhnFrtVerf = IFSPEntpAhnFrtVerf;
    }

    public String getTokenToUser() {
        return tokenToUser;
    }

    public void setTokenToUser(String tokenToUser) {
        this.tokenToUser = tokenToUser;
    }

    public String getWebPsaaword() {
        return webPsaaword;
    }

    public String getPgrade() {
        return pgrade;
    }

    public void setPgrade(String pgrade) {
        this.pgrade = pgrade;
    }

    public void setWebPsaaword(String webPsaaword) {
        this.webPsaaword = webPsaaword;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProduceLevel() {
        return produceLevel;
    }

    public void setProduceLevel(String produceLevel) {
        this.produceLevel = produceLevel;
    }

    public String getProduceId() {
        return produceId;
    }

    public void setProduceId(String produceId) {
        this.produceId = produceId;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }
    public String getIFSPLnDfaltRgst() {
        return IFSPLnDfaltRgst;
    }

    public void setIFSPLnDfaltRgst(String IFSPLnDfaltRgst) {
        this.IFSPLnDfaltRgst = IFSPLnDfaltRgst;
    }

    public String getIFSPVldEntpInf() {
        return IFSPVldEntpInf;
    }

    public void setIFSPVldEntpInf(String IFSPVldEntpInf) {
        this.IFSPVldEntpInf = IFSPVldEntpInf;
    }

    public String getIFSPLnAplDtc() {
        return IFSPLnAplDtc;
    }

    public void setIFSPLnAplDtc(String IFSPLnAplDtc) {
        this.IFSPLnAplDtc = IFSPLnAplDtc;
    }

    public String getIFSPLnLmtEst() {
        return IFSPLnLmtEst;
    }

    public void setIFSPLnLmtEst(String IFSPLnLmtEst) {
        this.IFSPLnLmtEst = IFSPLnLmtEst;
    }

    public String getIFSPRcvPymtAccNoInfSynz() {
        return IFSPRcvPymtAccNoInfSynz;
    }

    public void setIFSPRcvPymtAccNoInfSynz(String IFSPRcvPymtAccNoInfSynz) {
        this.IFSPRcvPymtAccNoInfSynz = IFSPRcvPymtAccNoInfSynz;
    }

    public String getIFSPEsttCltlLmtMsr() {
        return IFSPEsttCltlLmtMsr;
    }

    public void setIFSPEsttCltlLmtMsr(String IFSPEsttCltlLmtMsr) {
        this.IFSPEsttCltlLmtMsr = IFSPEsttCltlLmtMsr;
    }

    public String getIFSPEntCrdLmtMsr() {
        return IFSPEntCrdLmtMsr;
    }

    public void setIFSPEntCrdLmtMsr(String IFSPEntCrdLmtMsr) {
        this.IFSPEntCrdLmtMsr = IFSPEntCrdLmtMsr;
    }

    public String getIFSPEnqrUrbnDstcInf() {
        return IFSPEnqrUrbnDstcInf;
    }

    public void setIFSPEnqrUrbnDstcInf(String IFSPEnqrUrbnDstcInf) {
        this.IFSPEnqrUrbnDstcInf = IFSPEnqrUrbnDstcInf;
    }

    public String getIFSPEnqrCommLst() {
        return IFSPEnqrCommLst;
    }

    public void setIFSPEnqrCommLst(String IFSPEnqrCommLst) {
        this.IFSPEnqrCommLst = IFSPEnqrCommLst;
    }

    public String getIFSPAplySMSVldCd() {
        return IFSPAplySMSVldCd;
    }

    public void setIFSPAplySMSVldCd(String IFSPAplySMSVldCd) {
        this.IFSPAplySMSVldCd = IFSPAplySMSVldCd;
    }

    public String getIFSPVerfSMSVldCd() {
        return IFSPVerfSMSVldCd;
    }

    public void setIFSPVerfSMSVldCd(String IFSPVerfSMSVldCd) {
        this.IFSPVerfSMSVldCd = IFSPVerfSMSVldCd;
    }
}

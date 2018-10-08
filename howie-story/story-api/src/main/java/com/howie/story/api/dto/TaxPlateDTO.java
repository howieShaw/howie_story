package com.howie.story.api.dto;

/**
 * @Author:xiaohaoyun
 * @Description： 税盘对象
 * @Date：created in 下午2:15 2018/9/3
 * @Modified by:xiaohaoyun
 */
public class TaxPlateDTO {
    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业工商注册号
     */
    private String bizRegNo;

    /**
     * 法人名称
     */
    private String legalName;

    /**
     * 企业税务登记号
     */
    private String taxRegNo;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBizRegNo() {
        return bizRegNo;
    }

    public void setBizRegNo(String bizRegNo) {
        this.bizRegNo = bizRegNo;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTaxRegNo() {
        return taxRegNo;
    }

    public void setTaxRegNo(String taxRegNo) {
        this.taxRegNo = taxRegNo;
    }
}

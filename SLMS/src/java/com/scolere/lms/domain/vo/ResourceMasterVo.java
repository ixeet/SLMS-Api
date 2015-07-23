/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo;

/**
 *
 * @author admin
 */
public class ResourceMasterVo {
     private int resourceId;
    private String resourceName;
    private String resourceAuthor;
    private String resourceDuration;
    private String createdDt;
    private String descTxt;
    private String metadata;
    private String DeletedFl;
    private int displayNo;
    private String enableFl;
    private String createdBy;
    private String lastUserIdCd;
    private String lastUpdtTm;

    public ResourceMasterVo() {
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceAuthor() {
        return resourceAuthor;
    }

    public void setResourceAuthor(String resourceAuthor) {
        this.resourceAuthor = resourceAuthor;
    }

    public String getResourceDuration() {
        return resourceDuration;
    }

    public void setResourceDuration(String resourceDuration) {
        this.resourceDuration = resourceDuration;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getDeletedFl() {
        return DeletedFl;
    }

    public void setDeletedFl(String DeletedFl) {
        this.DeletedFl = DeletedFl;
    }

    public int getDisplayNo() {
        return displayNo;
    }

    public void setDisplayNo(int displayNo) {
        this.displayNo = displayNo;
    }

    public String getEnableFl() {
        return enableFl;
    }

    public void setEnableFl(String enableFl) {
        this.enableFl = enableFl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUserIdCd() {
        return lastUserIdCd;
    }

    public void setLastUserIdCd(String lastUserIdCd) {
        this.lastUserIdCd = lastUserIdCd;
    }

    public String getLastUpdtTm() {
        return lastUpdtTm;
    }

    public void setLastUpdtTm(String lastUpdtTm) {
        this.lastUpdtTm = lastUpdtTm;
    }

   
}

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
public class ResourceTagVo {
    private int tagId;
    private int resourceId;
    private String  tagNm;
    private  int tagWtAdmin;
    private  int tagWtUser;
    private String lastUserIdCd;
    private String lastupdtTm;

    public ResourceTagVo() {
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTagNm() {
        return tagNm;
    }

    public void setTagNm(String tagNm) {
        this.tagNm = tagNm;
    }

    public int getTagWtAdmin() {
        return tagWtAdmin;
    }

    public void setTagWtAdmin(int tagWtAdmin) {
        this.tagWtAdmin = tagWtAdmin;
    }

    public int getTagWtUser() {
        return tagWtUser;
    }

    public void setTagWtUser(int tagWtUser) {
        this.tagWtUser = tagWtUser;
    }

    public String getLastUserIdCd() {
        return lastUserIdCd;
    }

    public void setLastUserIdCd(String lastUserIdCd) {
        this.lastUserIdCd = lastUserIdCd;
    }

    public String getLastupdtTm() {
        return lastupdtTm;
    }

    public void setLastupdtTm(String lastupdtTm) {
        this.lastupdtTm = lastupdtTm;
    }
    
}

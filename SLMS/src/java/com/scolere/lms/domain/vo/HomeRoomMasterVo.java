/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.java.interfac;

/**
 *
 * @author admin
 */
public class HomeRoomMasterVo {
     private int homeRoomMstrId;
    private String homeRoomMstrName;
    private String descTxt;
    private String metadata;
    private String deletedFl;
    private int displayNo;
    private String enableFl;
    private String createdBy;
    private String lastuserIdCd;
    private String lastUpdtTm;

    public HomeRoomMasterVo() {
    }

    public int getHomeRoomMstrId() {
        return homeRoomMstrId;
    }

    public void setHomeRoomMstrId(int homeRoomMstrId) {
        this.homeRoomMstrId = homeRoomMstrId;
    }

    public String getHomeRoomMstrName() {
        return homeRoomMstrName;
    }

    public void setHomeRoomMstrName(String homeRoomMstrName) {
        this.homeRoomMstrName = homeRoomMstrName;
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
        return deletedFl;
    }

    public void setDeletedFl(String deletedFl) {
        this.deletedFl = deletedFl;
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

    public String getLastuserIdCd() {
        return lastuserIdCd;
    }

    public void setLastuserIdCd(String lastuserIdCd) {
        this.lastuserIdCd = lastuserIdCd;
    }

    public String getLastUpdtTm() {
        return lastUpdtTm;
    }

    public void setLastUpdtTm(String lastUpdtTm) {
        this.lastUpdtTm = lastUpdtTm;
    }
    
}

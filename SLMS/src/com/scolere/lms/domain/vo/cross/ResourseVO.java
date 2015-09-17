/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo.cross;

/**
 *
 * @author dell
 */
public class ResourseVO {
    
    private int resourceSessionId;
    private int resourceId;
    private String resourceName;
    private String resourceUrl;
    private String thumbUrl="default.png";
    private String resourceDesc;
    private String startedOn;
    private String completedOn;
    private String uploadedBy;
    private String uploadedDate;
    private String authorName;
    private String authorImg;
    private int likeCounts;
    private int shareCounts;
    private int commentCounts;
    private boolean isLiked;
    private String completedStatus;

    
    
   	public int getResourceSessionId() {
		return resourceSessionId;
	}

	public void setResourceSessionId(int resourceSessionId) {
		this.resourceSessionId = resourceSessionId;
	}

	public String getCompletedStatus() {
		return completedStatus;
	}

	public void setCompletedStatus(String completedStatus) {
		this.completedStatus = completedStatus;
	}

	public boolean isIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }
    
    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
   
    
    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public int getShareCounts() {
        return shareCounts;
    }

    public void setShareCounts(int shareCounts) {
        this.shareCounts = shareCounts;
    }

    public int getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
    }
   
    
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
    
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(String completedOn) {
        this.completedOn = completedOn;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    
    
}

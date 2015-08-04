/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author dell
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class ResourceRespTO extends CountsTO{
    private String resourceId;
    private String resourceName;
    private String resourceUrl;
    private String resourceDesc;
    private String startedOn;
    private String completedOn;
    private String uploadedBy;
    private String uploadedDate;
    private String authorName;
    private String authorImg;
    private String thumbImg;
    private List<CommentRespTO> commentList;
    private List<ResourceRespTO> relatedVideoList;

    
    
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public List<CommentRespTO> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentRespTO> commentList) {
        this.commentList = commentList;
    }
    
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
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
    
    public List<ResourceRespTO> getRelatedVideoList() {
        return relatedVideoList;
    }

    public void setRelatedVideoList(List<ResourceRespTO> relatedVideoList) {
        this.relatedVideoList = relatedVideoList;
    }
    
    
}

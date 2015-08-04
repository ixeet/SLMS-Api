/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

/**
 *
 * @author dell
 */
public class CountsTO {
    private int likeCounts;
    private int shareCounts;
    private int commentCounts;
    private boolean isLiked;


    public boolean isIsLiked() {
        return isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
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
    
    
}

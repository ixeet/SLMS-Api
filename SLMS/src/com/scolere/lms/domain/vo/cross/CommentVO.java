/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo.cross;

import java.util.List;

/**
 *
 * @author dell
 */
public class CommentVO {
    private int commentId;
    private int parentCommentId;
    private String commentBy;
    private int commentById;
    private String commentByImage;
    private String commentTxt;
    private String commentDate;
    private int likeCounts;
    private int shareCounts;
    private int commentCounts;
    private boolean isLiked;

    private List<CommentVO> subCommentList;
    
    
    public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public List<CommentVO> getSubCommentList() {
		return subCommentList;
	}

	public void setSubCommentList(List<CommentVO> subCommentList) {
		this.subCommentList = subCommentList;
	}

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

    
    public String getCommentByImage() {
        return commentByImage;
    }

    public void setCommentByImage(String commentByImage) {
        this.commentByImage = commentByImage;
    }
    
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getCommentTxt() {
        return commentTxt;
    }

    public void setCommentTxt(String commentTxt) {
        this.commentTxt = commentTxt;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

	public int getCommentById() {
		return commentById;
	}

	public void setCommentById(int commentById) {
		this.commentById = commentById;
	}
    
    
}

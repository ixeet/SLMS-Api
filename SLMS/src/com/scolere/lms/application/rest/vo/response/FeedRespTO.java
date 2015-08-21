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
public class FeedRespTO extends CountsTO{
    private int feedId;
    private String feedOn;
    private String feedText;
    private List<KeyValTypeVO> feedTextArray;
    UserResponse user;
    ResourceRespTO resource;
    CommentRespTO comment;
    CourseRespTO course;
    private List<CommentRespTO> feedCommentsList;

    
    public String getFeedOn() {
		return feedOn;
	}

	public void setFeedOn(String feedOn) {
		this.feedOn = feedOn;
	}

	public List<KeyValTypeVO> getFeedTextArray() {
        return feedTextArray;
    }

    public void setFeedTextArray(List<KeyValTypeVO> feedTextArray) {
        this.feedTextArray = feedTextArray;
    }

    public CommentRespTO getComment() {
        return comment;
    }

    public void setComment(CommentRespTO comment) {
        this.comment = comment;
    }

    public CourseRespTO getCourse() {
        return course;
    }

    public void setCourse(CourseRespTO course) {
        this.course = course;
    }

    
    
    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public ResourceRespTO getResource() {
        return resource;
    }

    public void setResource(ResourceRespTO resource) {
        this.resource = resource;
    }

    
    public int getFeedId() {
        return feedId;
    }

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public String getFeedText() {
        return feedText;
    }

    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }

    public List<CommentRespTO> getFeedCommentsList() {
        return feedCommentsList;
    }

    public void setFeedCommentsList(List<CommentRespTO> feedCommentsList) {
        this.feedCommentsList = feedCommentsList;
    }
    
    
}//End of class

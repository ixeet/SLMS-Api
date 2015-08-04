/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

import java.util.List;

/**
 *
 * @author dell
 */
public class FeedRespTO extends CountsTO{
    private String feedText;
    private List<CommentRespTO> feedCommentsList;

    
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
    
    
}

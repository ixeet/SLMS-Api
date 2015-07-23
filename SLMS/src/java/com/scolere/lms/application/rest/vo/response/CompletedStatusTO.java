/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author dell
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_DEFAULT)
public class CompletedStatusTO {
    private String startedOn;
    private String completedOn;
    private String completedStatus;
    private String completedPercentStatus;

    
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

    public String getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(String completedStatus) {
        this.completedStatus = completedStatus;
    }

    public String getCompletedPercentStatus() {
        return completedPercentStatus;
    }

    public void setCompletedPercentStatus(String completedPercentStatus) {
        this.completedPercentStatus = completedPercentStatus;
    }
      
    
}

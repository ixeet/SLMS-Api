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
public class CommonResponse extends CommonRespTO{
    
    private List<SchoolRespTO> schoolList;

    
    //Getter-setters
    public List<SchoolRespTO> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<SchoolRespTO> schoolList) {
        this.schoolList = schoolList;
    }
    
}

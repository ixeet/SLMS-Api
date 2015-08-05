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
public class ModuleRespTO extends CompletedStatusTO{
    private String moduleId;
    private String moduleName;
    List<ResourceRespTO> resourceList;

    
    public ModuleRespTO(String moduleId, String moduleName) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
    }

    public ModuleRespTO() {
    }

    public List<ResourceRespTO> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourceRespTO> resourceList) {
        this.resourceList = resourceList;
    }
   
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    
}

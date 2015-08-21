/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.application.rest.vo.response;

/**
 *
 * @author dell
 */
public class KeyValTypeVO {
    private String key;
    private String value;
    private String type;

    public KeyValTypeVO(String key, String value, String type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    public KeyValTypeVO() {
    }

    
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}

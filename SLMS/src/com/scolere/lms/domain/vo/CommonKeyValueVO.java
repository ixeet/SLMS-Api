/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo;

/**
 *
 * @author dell
 */
public class CommonKeyValueVO {
    
    private String itemCode;
    private String itemName;

    public CommonKeyValueVO(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public CommonKeyValueVO() {
    }

    /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "CommonKeyValueVO{" + "itemCode=" + itemCode + ", itemName=" + itemName + '}';
    }
    
    
    
}

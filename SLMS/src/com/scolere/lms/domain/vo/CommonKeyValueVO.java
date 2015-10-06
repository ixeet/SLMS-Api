/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.vo;

import java.util.List;

/**
 *
 * @author dell
 */
public class CommonKeyValueVO {
    
    private String itemCode;
    private String itemName;

    private List<CommonKeyValueVO> childs;
    
    public CommonKeyValueVO(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public CommonKeyValueVO() {
    }

   
    
    public List<CommonKeyValueVO> getChilds() {
		return childs;
	}

	public void setChilds(List<CommonKeyValueVO> childs) {
		this.childs = childs;
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

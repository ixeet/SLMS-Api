/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.persistance.dao.iface;

import com.scolere.lms.domain.exception.LmsDaoException;
import com.scolere.lms.domain.vo.ResourceTypeMstrVo;
import java.util.List;


/**
 *
 * @author admin
 */
public interface ResourceTypeMstrDao {

    boolean updateResourceTypeMstrDetail(ResourceTypeMstrVo vo) throws LmsDaoException;

    /**
     * This method is used for save user course map
     *
     * @param vo
     */
    void saveResourceTypeMstrDetail(ResourceTypeMstrVo vo) throws LmsDaoException;

    /**
     * This method used for delete user course map
     *
     * @param vo
     * @return true/false
     */
    boolean deleteResourceTypeMstrDetail(ResourceTypeMstrVo vo) throws LmsDaoException;

    /**
     * This method used for get user user course.
     *
     * @param id
     * @return UserCourseMapVo
     */
    ResourceTypeMstrVo getResourceTypeMstrDetail(int id) throws LmsDaoException;

    /**
     * This is used for list.
     *
     * @return
     */
    List< ResourceTypeMstrVo> getResourceTypeMstrVoList() throws LmsDaoException;
    

    int saveResourceProfile(String resourceprofileImgName,
            String resourceAuthor, String resourceImage,
            String lastUserIdCd, String descTxt, String resourceName, String upLoadUrl, String resoUrl) throws LmsDaoException;
}

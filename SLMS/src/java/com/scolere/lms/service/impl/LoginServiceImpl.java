/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.service.impl;

import com.scolere.lms.domain.exception.LmsServiceException;
import com.scolere.lms.domain.vo.LoginSessionVo;
import com.scolere.lms.domain.vo.StudentDetailVo;
import com.scolere.lms.domain.vo.UserClassMapVo;
import com.scolere.lms.domain.vo.UserLoginVo;
import com.scolere.lms.domain.vo.cross.UserVO;
import com.scolere.lms.persistance.dao.iface.LoginSessionDao;
import com.scolere.lms.persistance.dao.iface.StudentDetailDao;
import com.scolere.lms.persistance.dao.iface.UserClassMapDao;
import com.scolere.lms.persistance.dao.iface.UserLoginDao;
import com.scolere.lms.persistance.factory.LmsDaoFactory;
import com.scolere.lms.service.iface.LoginServiceIface;
import java.util.List;

/**
 *
 * @author dell
 */
public class LoginServiceImpl implements LoginServiceIface{

   @Override
    public UserVO getUser(String userName, String userPwd) throws LmsServiceException {
        UserVO userVO = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            userVO = dao.getUser(userName, userPwd);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveUserLoginDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return userVO;
   }
   
   @Override
    public UserVO getUser(String facebookId) throws LmsServiceException {
        UserVO userVO = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            userVO = dao.getUser(facebookId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveUserLoginDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return userVO;
   }   
   
    @Override
    public boolean updateUserFBId(String userName,String userFbId) throws LmsServiceException {
        
        boolean status = false;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            status = dao.updateUserFBId(userName, userFbId);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateUserFBId = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }   
    
    @Override
    public boolean updateUserPwd(String userName,String userPwd,String userNewPwd) throws LmsServiceException {
        
        boolean status = false;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            status = dao.updateUserPwd(userName, userPwd, userNewPwd);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateUserPwd = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }      
    @Override
    public boolean updateUserLoginDetail(UserLoginVo vo) throws LmsServiceException {
        
        boolean status = false;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            status = dao.updateUserLoginDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateUserLoginDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public UserLoginVo saveUserLoginDetail(UserLoginVo vo) throws LmsServiceException {
        UserLoginVo userLoginVo = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            userLoginVo = dao.saveUserLoginDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveUserLoginDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return userLoginVo;
    }

    @Override
    public boolean deleteUserLoginDetail(UserLoginVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            status = dao.deleteUserLoginDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteUserLoginDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public UserLoginVo getUserLoginDetail(int id) throws LmsServiceException {
        UserLoginVo vo = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            vo = dao.getUserLoginDetail(id);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteUserLoginDetail(id) = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;
    }

    @Override
    public UserLoginVo getUserLoginDetail(String userName) throws LmsServiceException {
        UserLoginVo vo = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            vo = dao.getUserLoginDetail(userName);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteUserLoginDetail(name) = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;
    }    


    @Override
    public UserLoginVo getUserLoginDetail(String userName,String userPwd) throws LmsServiceException {
        UserLoginVo vo = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            vo = dao.getUserLoginDetail(userName,userPwd);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteUserLoginDetail(name) = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;
    }      
    
    @Override
    public List<UserLoginVo> getUserLoginVoList() throws LmsServiceException {
        List<UserLoginVo> list = null;
        try {
            UserLoginDao dao = (UserLoginDao) LmsDaoFactory.getDAO(UserLoginDao.class);
            list = dao.getUserLoginVoList();
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteUserLoginDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public boolean updateStudentDetail(StudentDetailVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            StudentDetailDao dao = (StudentDetailDao) LmsDaoFactory.getDAO(StudentDetailDao.class);
            status = dao.updateStudentDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateStudentDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public boolean saveStudentDetail(StudentDetailVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            StudentDetailDao dao = (StudentDetailDao) LmsDaoFactory.getDAO(StudentDetailDao.class);
            status = dao.saveStudentDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveStudentDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public boolean deleteStudentDetail(StudentDetailVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            StudentDetailDao dao = (StudentDetailDao) LmsDaoFactory.getDAO(StudentDetailDao.class);
            status = dao.deleteStudentDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteStudentDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public StudentDetailVo getStudentDetail(int id) throws LmsServiceException {
        StudentDetailVo vo=null;
        try {
            StudentDetailDao dao = (StudentDetailDao) LmsDaoFactory.getDAO(StudentDetailDao.class);
            vo = dao.getStudentDetail(id);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteStudentDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;
    }

    
    
    @Override
    public List<StudentDetailVo> getStudentDetailVoList() throws LmsServiceException {
        List<StudentDetailVo> list=null;
        try {
            StudentDetailDao dao = (StudentDetailDao) LmsDaoFactory.getDAO(StudentDetailDao.class);
            list = dao.getStudentDetailVoList();
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteStudentDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public boolean updateLoginSession(LoginSessionVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            LoginSessionDao dao = (LoginSessionDao) LmsDaoFactory.getDAO(LoginSessionDao.class);
            status = dao.updateLoginSession(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # updateLoginSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }
    

    @Override
    public boolean saveLoginSession(LoginSessionVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            LoginSessionDao dao = (LoginSessionDao) LmsDaoFactory.getDAO(LoginSessionDao.class);
            status = dao.saveLoginSession(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveLoginSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public boolean deleteLoginSession(LoginSessionVo vo) throws LmsServiceException {
        boolean status = false;
        try {
            LoginSessionDao dao = (LoginSessionDao) LmsDaoFactory.getDAO(LoginSessionDao.class);
            status = dao.deleteLoginSession(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteLoginSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return status;
    }

    @Override
    public LoginSessionVo getLoginSession(int id) throws LmsServiceException {
        LoginSessionVo vo = null;
        try {
            LoginSessionDao dao = (LoginSessionDao) LmsDaoFactory.getDAO(LoginSessionDao.class);
            vo = dao.getLoginSession(id);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # deleteLoginSession = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return vo;
    }

    @Override
    public List<LoginSessionVo> getLoginSessionList() throws LmsServiceException {
        List<LoginSessionVo> list = null;
        try {
            LoginSessionDao dao = (LoginSessionDao) LmsDaoFactory.getDAO(LoginSessionDao.class);
            list = dao.getLoginSessionList();
        } catch (Exception ex) {
            System.out.println("LmsServiceException # getLoginSessionList = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public void saveUserClassMapDetail(UserClassMapVo vo) throws LmsServiceException {
        try {
            UserClassMapDao dao = (UserClassMapDao) LmsDaoFactory.getDAO(UserClassMapDao.class);
            dao.saveUserClassMapDetail(vo);
        } catch (Exception ex) {
            System.out.println("LmsServiceException # saveUserClassMapDetail = "+ex);
            throw new LmsServiceException(ex.getMessage());
        }
    
    }

    
    
}//end of class

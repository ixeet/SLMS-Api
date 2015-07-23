/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scolere.lms.domain.exception;

/**
 *
 * @author dell
 */
public class LmsServiceException extends Exception {

    /**
     * Creates a new instance of
     * <code>LmsDaoException</code> without detail message.
     */
    public LmsServiceException() {
    }

    /**
     * Constructs an instance of
     * <code>LmsDaoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public LmsServiceException(String msg) {
        super("Exception occured at service layer : "+msg);
    }
}
